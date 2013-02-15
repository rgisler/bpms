/*
 * Copyright 2008 Roland Gisler, GISLER iNFORMATiK, Switzerland.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * $Id: MulticastReceiver.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageHandler;
import ch.gitik.bpms.common.MessageListener;
import ch.gitik.bpms.common.XMLConverter;

/**
 * 
 * @author Roland Gisler
 * @version $Revision: 214 $
 */
public class MulticastReceiver implements MessageHandler, Runnable {
   
   private boolean active = false;
   
   private MulticastConfig config;
   
   private MulticastSocket socket;

   private byte buffer[] = new byte[8192];

   private DatagramPacket datagram;

   private List<MessageListener> listerners = new ArrayList<MessageListener>();

   /**
    * Konstruktor.
    * @param config Konfiguration.
    */
   public MulticastReceiver(MulticastConfig config) {
      this.config = config;
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#activate()
    */
   public void activate() {
      try {
         this.socket = new MulticastSocket(this.config.getPort());
         this.socket.joinGroup(InetAddress.getByName(this.config.getIp()));
         this.active = true;
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      this.datagram = new DatagramPacket(buffer, buffer.length);
      new Thread(this).start();
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#deactivate()
    */
   public void deactivate()  {
      this.active = false;
      try {
         this.socket.leaveGroup(InetAddress.getByName(this.config.getIp()));
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      this.socket.close();
   }

   /**
    * @see java.lang.Runnable#run()
    */
   public void run() {
      while (this.active) {
         try {
            this.socket.receive(datagram);
            Message message = XMLConverter.getMessage(new String(datagram.getData()));
            this.fireMessageReceived(message);
         } catch (IOException e) {
            if (this.active) {
               System.err.println(e);
            }
         }
      }
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#addMessageListener(ch.gitik.bpms.common.MessageListener)
    */
   public void addMessageListener(MessageListener listener) {
      this.listerners.add(listener);
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#removeMessageListener(ch.gitik.bpms.common.MessageListener)
    */
   public void removeMessageListener(MessageListener listener) {
      this.listerners.remove(listener);
   }

   /**
    * Informiert die Message Listener ueber eine Message.
    * @param msg
    */
   private void fireMessageReceived(Message msg) {
      for (MessageListener listener : this.listerners) {
         listener.messageReceived(msg);
      }
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#isActive()
    */
   public boolean isActive() {
      return this.active;
   }

}

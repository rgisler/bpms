/*
 * Copyright 2013 Roland Gisler, GISLER iNFORMATiK, Switzerland.
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
 */
package ch.gitik.bpms.multicast;

import java.io.IOException;
import java.net.UnknownHostException;

import ch.gitik.bpms.common.AbstractMessageSource;
import ch.gitik.bpms.common.ConfigException;
import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageHandler;
import ch.gitik.bpms.common.MessageListener;

/**
 * @author Roland Gisler
 */
public class MulticastHandler extends AbstractMessageSource implements MessageHandler, MessageListener {

   private MulticastConfig config;

   private MulticastSender sender;

   private MulticastReceiver receiver;

   /**
    * Konstruktor.
    * @param config
    *           Konfiguration.
    */
   public MulticastHandler(MulticastConfig config) {
      this.config = config;
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#activate()
    */
   public void activate() {
      try {
         this.receiver = MulticastReceiverFactory.getReceiver(this.config);
         this.receiver.addMessageListener(this);
         this.receiver.activate();
      } catch (ConfigException e) {
         e.printStackTrace();
      }
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#deactivate()
    */
   public void deactivate() {
      this.receiver.deactivate();
      this.receiver.removeMessageListener(this);
   }

   /**
    * @see ch.gitik.bpms.common.MessageListener#messageReceived(ch.gitik.bpms.common.Message)
    */
   public void messageReceived(Message msg) {
      this.fireMessageReceived(msg);
   }

   /**
    * Sendet eine Message.
    * @param msg
    *           Message.
    * @throws ConfigException
    *            Konfigurationsfehler.
    */
   public void send(Message msg) throws ConfigException {
      this.sender = MulticastSenderFactory.getSender(this.config);
      try {
         this.sender.send(msg);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#isActive()
    */
   public boolean isActive() {
      return this.receiver.isActive();
   }
}

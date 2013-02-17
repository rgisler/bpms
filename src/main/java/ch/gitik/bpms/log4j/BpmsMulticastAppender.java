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
package ch.gitik.bpms.log4j;

import java.io.IOException;
import java.net.UnknownHostException;

import ch.gitik.bpms.common.ConfigException;
import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.multicast.MulticastConfig;
import ch.gitik.bpms.multicast.MulticastSender;
import ch.gitik.bpms.multicast.MulticastSenderFactory;

/**
 * MulticastAppender.
 * @author Roland Gisler
 */
public class BpmsMulticastAppender extends AbstractBpmsAppender {

   private static MulticastSender sender;

   private String multicastip;

   private int port;

   /**
    * Liefert den Sender.
    * @param ip
    *           IP-Adresse.
    * @param port
    *           Portnumer.
    * @return MulticastSender.
    */
   private static MulticastSender getSender(final String ip, final int port) {
      if (sender == null) {
         MulticastConfig config = new MulticastConfig(ip, port);
         try {
            sender = MulticastSenderFactory.getSender(config);
         } catch (ConfigException e) {
            e.printStackTrace();
         }
      }
      return sender;
   }

   /**
    * @see ch.gitik.bpms.log4j.AbstractBpmsAppender#sendMessage(ch.gitik.bpms.common.Message)
    */
   @Override
   public final void sendMessage(final Message msg) {
      try {
         getSender(this.multicastip, this.port).send(msg);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /*
    * @see org.apache.log4j.AppenderSkeleton#close()
    */
   @Override
   public final void close() {
      sender = null;
   }

   /**
    * Liefert die Multicast IP.
    * @return Multicast IP.
    */
   public final String getMulticastip() {
      return multicastip;
   }

   /**
    * Setzt die Mulitcast IP.
    * @param multicastip
    *           Multicast IP.
    */
   public final void setMulticastip(final String multicastip) {
      this.multicastip = multicastip;
   }

   /**
    * Liefert den Port.
    * @return Portnummer.
    */
   public final int getPort() {
      return port;
   }

   /**
    * Setzt den Port.
    * @param port
    *           Port.
    */
   public final void setPort(final int port) {
      this.port = port;
   }
}

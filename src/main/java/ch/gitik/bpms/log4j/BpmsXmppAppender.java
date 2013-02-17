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

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.xmpp.XMPPConfig;
import ch.gitik.bpms.xmpp.XMPPSender;
import ch.gitik.bpms.xmpp.XMPPSenderFactory;

/**
 * XMPP Appender.
 * @author Roland Gisler
 */
public class BpmsXmppAppender extends AbstractBpmsAppender {

   private static XMPPSender sender;

   private String host;

   private int port;

   private String user;

   private String password;

   private String receiver;

   /**
    * Liefert den Receiver.
    * @return Receiver.
    */
   public final String getReceiver() {
      return receiver;
   }

   /**
    * Setzt den Receiver.
    * @param receiver
    *           Receiver.
    */
   public final void setReceiver(final String receiver) {
      this.receiver = receiver;
   }

   /**
    * Liefert den Benutzer.
    * @return Benutzername.
    */
   public final String getUser() {
      return user;
   }

   /**
    * Setzt den Benutzer.
    * @param user
    *           Benutzername.
    */
   public final void setUser(final String user) {
      this.user = user;
   }

   /**
    * Setzt das Passwort.
    * @param password
    *           Passwort.
    */
   public final void setPassword(final String password) {
      this.password = password;
   }

   /**
    * Liefert den Sender.
    * @param host
    *           Hostname.
    * @param port
    *           Portnummer.
    * @param user
    *           Benutzer.
    * @param password
    *           Passwort.
    * @return Sender.
    */
   private static XMPPSender getSender(final String host, final int port, final String user,
         final String password) {
      if (sender == null) {
         XMPPConfig config = new XMPPConfig(host, port, user, password, null, false);
         sender = XMPPSenderFactory.getXMPPSender(config);
      }
      return sender;
   }

   /*
    * @see
    * ch.gitik.bpms.log4j.AbstractBpmsAppender#sendMessage(ch.gitik.bpms.common
    * .Message)
    */
   @Override
   public void sendMessage(final Message msg) {
      getSender(this.host, this.port, this.user, this.password).sendMessage(this.receiver, msg);
   }

   /*
    * @see org.apache.log4j.AppenderSkeleton#close()
    */
   @Override
   public void close() {
      sender = null;
   }

   /**
    * Liefert den Host.
    * @return Hostname.
    */
   public final String getHost() {
      return host;
   }

   /**
    * Setzt den Host.
    * @param multicastip
    *           Multicast IP.
    */
   public final void setHost(final String multicastip) {
      this.host = multicastip;
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
    *           Portnummer.
    */
   public final void setPort(final int port) {
      this.port = port;
   }
}

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
 * $Id: BpmsXmppAppender.java 264 2009-07-17 13:57:06Z rog $
 */
package ch.gitik.bpms.log4j;

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.xmpp.XMPPConfig;
import ch.gitik.bpms.xmpp.XMPPSender;
import ch.gitik.bpms.xmpp.XMPPSenderFactory;

/**
 *
 * @author Roland Gisler
 * @version $Revision: 264 $
 */
public class BpmsXmppAppender extends AbstractBpmsAppender {

   private static XMPPSender sender;

   private String host;

   private int port;

   private String user;

   private String password;

   private String receiver;

   public String getReceiver() {
      return receiver;
   }

   public void setReceiver(String receiver) {
      this.receiver = receiver;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   private static XMPPSender getSender(String host, int port, String user, String password) {
      if (sender == null) {
         XMPPConfig config = new XMPPConfig(host, port, user, password, null, false);
         sender = XMPPSenderFactory.getXMPPSender(config);
      }
      return sender;
   }

   @Override
   public void sendMessage(Message msg) {
      getSender(this.host, this.port, this.user, this.password).sendMessage(this.receiver, msg);
   }

   @Override
   public void close() {
      sender = null;
   }

   public String getHost() {
      return host;
   }

   public void setHost(String multicastip) {
      this.host = multicastip;
   }

   public int getPort() {
      return port;
   }

   public void setPort(int port) {
      this.port = port;
   }

}

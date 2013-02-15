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
 * $Id: XMPPHandler.java 274 2009-07-19 11:11:19Z rog $
 */
package ch.gitik.bpms.xmpp;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;

import ch.gitik.bpms.common.MessageHandler;
import ch.gitik.bpms.common.MessageListener;
import ch.gitik.bpms.common.XMLConverter;

/**
 * Wrapper-Klasse fuer Jabber Instant Messaging. Diese Klasse bietet
 * vereinfachte Funktionen an um mit einem Jabber-Server zu kommunizieren. Sie
 * verwendet die Client API 'smack'.
 *
 * @author Roland Gisler
 * @version $Revision: 274 $ ($Date: 2009-07-17 15:57:06 +0200 (Fr, 17 Jul 2009)
 *          $ / $Author: rog $)
 */
public class XMPPHandler extends XMPPSender implements MessageHandler, PacketListener {

   private boolean active = false;

   private String receiver = null;

   private Chat chat = null;

   private MultiUserChat multiUserChat = null;

   private List<MessageListener> listerners = new ArrayList<MessageListener>();

   /**
    * Konstruktor.
    */
   public XMPPHandler(XMPPConfig config) {
      super(config);
      this.receiver = config.getReceiver();
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#activate()
    */
   public void activate() {
      try {
         this.openConnection();
         this.setReceiver(this.config.getReceiver());
         if (this.config.isConference()) {
            this.multiUserChat = new MultiUserChat(this.connection, this.receiver);
            this.multiUserChat.join(this.config.getUser());
            this.multiUserChat.addMessageListener(this);
         } else {
            this.chat = this.connection.getChatManager().createChat(this.receiver, this);
            this.chat.addMessageListener(this);
         }
         this.active = true;
      } catch (XMPPException e) {
         e.printStackTrace();
      }
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#deactivate()
    */
   public void deactivate() {
      if ((this.multiUserChat != null) && this.multiUserChat.isJoined()) {
         this.multiUserChat.removeMessageListener(this);
         this.multiUserChat.leave();
      }
      if (this.chat != null) {
         this.chat.removeMessageListener(this);
      }
      this.active = false;
   }

   /**
    * @see org.jivesoftware.smack.PacketListener#processPacket(org.jivesoftware.smack.packet.Packet)
    */
   public void processPacket(Packet arg0) {
      Message xmppMsg = (Message) arg0;
      ch.gitik.bpms.common.Message message = XMLConverter.getMessage(xmppMsg.getBody());
      this.fireMessageReceived(message);
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#addMessageListener(ch.gitik.bpms.common.MessageListener)
    */
   public void addMessageListener(MessageListener listener) {
      this.listerners.add(listener);
   }

   /**
    *
    * @param msg
    */
   private void fireMessageReceived(ch.gitik.bpms.common.Message msg) {
      for (MessageListener listener : this.listerners) {
         listener.messageReceived(msg);
      }
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#removeMessageListener(ch.gitik.bpms.common.MessageListener)
    */
   public void removeMessageListener(MessageListener listener) {
      this.listerners.remove(listener);
   }

   /**
    * Liefert den ConsoleReceiver.
    *
    * @return String
    */
   public String getReceiver() {
      return receiver;
   }

   /**
    * Setzt den ConsoleReceiver.
    *
    * @param receiver
    */
   public void setReceiver(String receiver) {
      this.receiver = receiver;
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#isActive()
    */
   public boolean isActive() {
      return this.active;
   }
}

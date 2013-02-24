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
 * @author Roland Gisler
 */
public class XMPPHandler extends XMPPSender implements MessageHandler, PacketListener {

   private boolean active = false;

   private String receiver = null;

   private Chat chat = null;

   private MultiUserChat multiUserChat = null;

   private List<MessageListener> listerners = new ArrayList<MessageListener>();

   /**
    * Konstruktor.
    * @param config
    *           Konfiguration.
    */
   public XMPPHandler(final XMPPConfig config) {
      super(config);
      this.receiver = config.getReceiver();
   }

   /**
    * @see ch.gitik.bpms.common.MessageHandler#activate()
    */
   public final void activate() {
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
   public final void deactivate() {
      if ((this.multiUserChat != null) && this.multiUserChat.isJoined()) {
         this.multiUserChat.removeMessageListener(this);
         this.multiUserChat.leave();
      }
      if (this.chat != null) {
         this.chat.removeMessageListener(this);
      }
      this.active = false;
   }

   /*
    * @see org.jivesoftware.smack.PacketListener#processPacket(org.jivesoftware.smack.packet.Packet)
    */
   public final void processPacket(final Packet arg0) {
      Message xmppMsg = (Message) arg0;
      ch.gitik.bpms.common.Message message = XMLConverter.getMessage(xmppMsg.getBody());
      this.fireMessageReceived(message);
   }

   /*
    * @see ch.gitik.bpms.common.MessageHandler#addMessageListener(ch.gitik.bpms.common.MessageListener)
    */
   public final void addMessageListener(final MessageListener listener) {
      this.listerners.add(listener);
   }

   /**
    * Informiert alle Listener.
    * @param msg
    *           Message.
    */
   private void fireMessageReceived(final ch.gitik.bpms.common.Message msg) {
      for (MessageListener listener : this.listerners) {
         listener.messageReceived(msg);
      }
   }

   /*
    * @see ch.gitik.bpms.common.MessageHandler#removeMessageListener(ch.gitik.bpms.common.MessageListener)
    */
   public final void removeMessageListener(final MessageListener listener) {
      this.listerners.remove(listener);
   }

   /**
    * Liefert den ConsoleReceiver.
    * @return String
    */
   public final String getReceiver() {
      return receiver;
   }

   /**
    * Setzt den ConsoleReceiver.
    * @param receiver
    */
   public final void setReceiver(final String receiver) {
      this.receiver = receiver;
   }

   /*
    * @see ch.gitik.bpms.common.MessageHandler#isActive()
    */
   public final boolean isActive() {
      return this.active;
   }
}

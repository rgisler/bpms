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

import java.util.Collection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.Roster.SubscriptionMode;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

import ch.gitik.bpms.common.XMLConverter;

/**
 * Wrapper-Klasse fuer Jabber Instant Messaging. Diese Klasse bietet
 * vereinfachte Funktionen an um mit einem Jabber-Server zu kommunizieren. Sie
 * verwendet die Client API 'smack'.
 * @author Roland Gisler
 */
public class XMPPSender implements MessageListener {

   protected XMPPConfig config;

   protected XMPPConnection connection = null;

   /**
    * Konstruktor.
    * @param config
    *           Konfiguration.
    */
   public XMPPSender(XMPPConfig config) {
      this.config = config;
   }

   /**
    * Sendet eine Message an die/den Jabber-User. Abhaengig ob ein Listener
    * definiert ist wird die Message entweder nur an diesen, oder aber an alle
    * aktiven Listener verschickt.
    * @param receiver
    *           Empfaenger.
    * @param message
    *           Message.
    */
   public void sendMessage(String receiver, ch.gitik.bpms.common.Message message) {
      this.sendMessage(receiver, XMLConverter.getXML(message));
   }

   /**
    * Sendet eine Message an die/den Jabber-User. Abhaengig ob ein Listener
    * definiert ist wird die Message entweder nur an diesen, oder aber an alle
    * aktiven Listener verschickt.
    * @param receiver
    *           Empfaenger.
    * @param message
    *           Message.
    */
   public void sendMessage(String receiver, String message) {
      XMPPConnection con = null;
      try {
         con = this.openConnection();
         if ((con != null) && (con.isConnected())) {
            if (receiver == null) {
               Roster roster = con.getRoster();
               roster.setSubscriptionMode(SubscriptionMode.accept_all);
               Collection<RosterEntry> entries = roster.getEntries();
               for (RosterEntry entry : entries) {
                  Chat chat = con.getChatManager().createChat(entry.getUser(), this);
                  chat.sendMessage(message);
               }
            } else {
               if (this.config.isConference()) {
                  MultiUserChat multiUserChat = new MultiUserChat(con, receiver);
                  multiUserChat.join(this.config.getUser());
                  multiUserChat.sendMessage(message);
                  multiUserChat.leave();
               } else {
                  Chat chat = con.getChatManager().createChat(receiver, this);
                  chat.sendMessage(message);
               }
            }
         }
      } catch (XMPPException e) {
         e.printStackTrace();
      } finally {
         if (con != null)
            this.closeConnection();
      }

   }

   /**
    * Liefert eine Connection zum Jabber-Server.
    * @return Connection.
    * @throws XMPPException
    */
   protected XMPPConnection openConnection() throws XMPPException {
      if (this.connection == null) {
         this.connection = new XMPPConnection(new ConnectionConfiguration(this.config.getServer(),
               this.config.getPort()));
         this.connection.connect();
         this.connection.login(this.config.getUser(), this.config.getPassword());
      }
      return this.connection;
   }

   /**
    *
    */
   protected void closeConnection() {
      if (this.connection != null) {
         this.connection.disconnect();
      }
      this.connection = null;
   }

   @Override
   public void processMessage(Chat arg0, Message arg1) {
      // Message ignorieren
   }
}

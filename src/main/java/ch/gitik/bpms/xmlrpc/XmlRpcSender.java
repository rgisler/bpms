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
package ch.gitik.bpms.xmlrpc;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;

import ch.gitik.bpms.common.MessageSender;
import ch.gitik.bpms.common.XMLConverter;

/**
 * Wrapper-Klasse fuer Jabber Instant Messaging. Diese Klasse bietet
 * vereinfachte Funktionen an um mit einem Jabber-Server zu kommunizieren. Sie
 * verwendet die Client API 'smack'.
 * @author Roland Gisler
 */
public class XmlRpcSender implements MessageSender {

   protected XmlrpcConfig config;

   protected ClientFactory factory;

   /**
    * Konstruktor.
    * @param config
    *           Konfiguration.
    */
   public XmlRpcSender(final XmlrpcConfig config) {
      this.config = config;
   }

   /**
    * Sendet eine Message. Abhaengig ob ein Listener definiert ist wird die
    * Message entweder nur an diesen, oder aber an alle aktiven Listener
    * verschickt.
    * @param receiver
    *           Empfaenger.
    * @param message
    *           Message.
    */
   public final void sendMessage(final String receiver, final ch.gitik.bpms.common.Message message) {
      this.sendMessage(receiver, XMLConverter.getXML(message));
   }

   /**
    * @see ch.gitik.bpms.common.MessageSender#sendMessage(java.lang.String,
    *      java.lang.String)
    */
   public final void sendMessage(final String receiver, final String message) {
      ClientFactory factory = null;
      try {
         factory = this.openConnection();
         if (factory != null) {
            MessageSender sender = (MessageSender) factory.newInstance(MessageSender.class);
            sender.sendMessage(receiver, message);
         }
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } finally {
         if (factory != null) {
            this.closeConnection();
         }
      }
   }

   /**
    * Liefert eine Connection zum XML-RPC Server.
    * @return ClientFactory.
    * @throws MalformedURLException
    *            Ungülte URL.
    */
   protected final ClientFactory openConnection() throws MalformedURLException {
      if (this.factory == null) {
         XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
         config.setServerURL(new URL("http://" + this.config.getHost() + ":" + this.config.getPort()
               + "/xmlrpc"));
         config.setEnabledForExtensions(true);
         XmlRpcClient client = new XmlRpcClient();
         client.setConfig(config);
         this.factory = new ClientFactory(client);
      }
      return this.factory;
   }

   /**
    *
    */
   protected final void closeConnection() {
      this.factory = null;
   }

}

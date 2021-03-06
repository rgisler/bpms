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
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.XMLConverter;

/**
 * Multicastsender.
 */
public class MulticastSender {

   private MulticastConfig config;

   private MulticastSocket mcastSocket;

   /**
    * Creates an simple multicast server.
    * @param config
    *           Konfiguration.
    */
   public MulticastSender(final MulticastConfig config) {
      this.config = config;
   }

   /**
    * Send an String message to the multicast address.
    * @param s
    *           String message to send.
    * @throws IOException
    *            If an error occures while setting ttl.
    */
   public final void send(final String s) throws IOException {
      byte[] buffer = s.getBytes();
      DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(this.config
            .getIp()), this.config.getPort());
      mcastSocket = new MulticastSocket();
      mcastSocket.send(datagram);
      mcastSocket.close();
   }

   /**
    * Send a Message to the multicast address.
    * @param msg
    *           Message to send.
    * @throws IOException
    *            If an error occures while setting ttl.
    */
   public final void send(final Message msg) throws IOException {
      this.send(XMLConverter.getXML(msg));
   }
}

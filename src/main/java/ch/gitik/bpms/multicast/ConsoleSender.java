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

import ch.gitik.bpms.common.ConfigException;
import ch.gitik.bpms.common.Message;

/**
 * ConsolenSender.
 */
public final class ConsoleSender {

   private static final String IP = "224.168.0.10";

   private static final int PORT = 4000;

   private MulticastSender sender = null;

   /**
    * Creates an multicast client.
    * @throws IOException
    *            If an I/O exception occurs while creating the MulticastSocket.
    */
   public ConsoleSender() throws IOException {
      this(IP, PORT);
   }

   /**
    * Creates an multicast client.
    * @param ip
    *           Multicast address.
    * @param port
    *           Port number.
    * @throws IOException
    *            If an I/O exception occurs while creating the MulticastSocket.
    */
   public ConsoleSender(final String ip, final int port) throws IOException {
      Message message = new Message();
      message.setGroup("Group");
      message.setProject("bpms_multicast");
      message.setTask("test");
      message.setText("Das ist ein Test");

      MulticastConfig config = new MulticastConfig(ip, port);

      try {
         sender = MulticastSenderFactory.getSender(config);
         sender.send(message);
         System.out.println("Message '" + message + "' sent to: " + ip + ":" + port);
      } catch (ConfigException e) {
         e.printStackTrace();
      }
   }

   /**
    * Run a simple multicast client to recive text messages Start: java.
    * MulticastClient MulticastAddress PortNumber.
    * @param argv
    *           Argumente.
    * @throws IOException
    *            IO-Exception.
    */
   public static void main(final String[] argv) throws IOException {
      String groupIP = IP;
      int port = PORT;
      if (argv.length >= 2) {
         groupIP = argv[0];
         port = Integer.parseInt(argv[1]);
      }
      new ConsoleSender(groupIP, port);
   }
}

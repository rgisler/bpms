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
import ch.gitik.bpms.common.MessageHandler;
import ch.gitik.bpms.common.MessageListener;

public class ConsoleReceiver implements MessageListener {

   /** Default IP fuer Multicast-Kommunikation. */
   private static final String IP = "224.168.0.10";

   /** Default Port fuer Multicast-Kommunikation. */
   private static final int PORT = 4000;

   /** Instanz fuer Receiver. */
   private MessageHandler receiver = null;

   /**
    * Konstruktor.
    * @throws IOException
    *            If an I/O exception occurs while creating the MulticastSocket.
    */
   public ConsoleReceiver() throws IOException {
      this(IP, PORT);
   }

   /**
    * Konstruktor.
    * @param ip
    *           Multicast address.
    * @param port
    *           Port number.
    * @throws IOException
    *            If an I/O exception occurs while creating the MulticastSocket.
    */
   public ConsoleReceiver(final String ip, final int port) throws IOException {

      System.out.println("GroupIP: " + ip + ":" + port);
      System.out.println("Starting ConsoleReceiver...");

      MulticastConfig config = new MulticastConfig(ip, port);

      try {
         receiver = MulticastReceiverFactory.getReceiver(config);
         receiver.addMessageListener(this);
         receiver.activate();
         System.out.println("ConsoleReceiver startet. Press ENTER for terminate. Now receiving messages...");
         System.in.read();
         System.out.println("Terminating client...");
         receiver.removeMessageListener(this);
         receiver.deactivate();
         System.out.println("ConsoleReceiver terminated.");
      } catch (ConfigException e) {
         e.printStackTrace();
      }
   }

   /**
    * Run a simple multicast client to recive text messages Start: java
    * [MulticastAddress PortNumber].
    * @param argv
    *           Argumente.
    * @throws IOException
    *            IO-Problem.
    */
   public static void main(final String[] argv) throws IOException {
      String groupIP = IP;
      int port = PORT;
      if (argv.length >= 2) {
         groupIP = argv[0];
         port = Integer.parseInt(argv[1]);
      }
      new ConsoleReceiver(groupIP, port);
   }

   /*
    * @see
    * ch.gitik.bpms.common.MessageListener#messageReceived(ch.gitik.bpms.common
    * .Message)
    */
   public final void messageReceived(final Message msg) {
      System.out.println(msg.toString());

   }
}

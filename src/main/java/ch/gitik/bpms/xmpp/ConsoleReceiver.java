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
 * $Id: ConsoleReceiver.java 281 2009-07-20 09:43:36Z rog $
 */
package ch.gitik.bpms.xmpp;

import java.io.IOException;
import java.net.UnknownHostException;

import ch.gitik.bpms.common.ConfigException;
import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageHandler;
import ch.gitik.bpms.common.MessageListener;

public class ConsoleReceiver implements MessageListener {

   private static final String SERVER = "jabber.gitik.ch";

   private static final int PORT = 5222;

   private static final String USER = "bpmslistener";

   private static final String PWD = "bpmslistener";

   private MessageHandler xmpp = null;

   /**
    * Creates an xmpp client.
    */
   public ConsoleReceiver() throws IOException, UnknownHostException {
      this(SERVER, PORT, USER, PWD);
   }

   /**
    * Creates an xmpp client.
    */
   public ConsoleReceiver(String server, int port, String user, String password) throws IOException,
         UnknownHostException {
      System.out.println("Starting XMPP-ConsoleReceiver...");

      XMPPConfig config = new XMPPConfig(server, port, user, password, "bpmstest@conference.ubuntuserver", true);

      xmpp = XMPPFactory.getXMPPHandler(config);
      xmpp.addMessageListener(this);
      try {
         xmpp.activate();
         System.out.println("XMPP-ConsoleReceiver startet. Press ENTER for terminate. Receiving messages...");
         System.in.read();
         System.out.println("Terminating client...");
         xmpp.removeMessageListener(this);
         xmpp.deactivate();
         System.out.println("xmppReceiver terminated.");
      } catch (ConfigException e) {
         e.printStackTrace();
      }
   }

   /**
    * Run a simple xmpp client.
    * Receive text messages Start: java xmppClient
    * xmppAddress PortNumber
    *
    * @throws IOException
    * @throws UnknownHostException
    */
   public static void main(String argv[]) throws UnknownHostException, IOException {
      String server = SERVER;
      int port = PORT;
      String user = USER;
      String password = PWD;
      if (argv.length >= 4) {
         server = argv[0];
         port = Integer.parseInt(argv[1]);
         user = argv[2];
         password = argv[3];
      }
      new ConsoleReceiver(server, port, user, password);
   }

   /**
    * @see ch.gitik.bpms.common.MessageListener#messageReceived(ch.gitik.bpms.common.Message)
    */
   public void messageReceived(Message msg) {
      System.out.println(msg);
   }
}

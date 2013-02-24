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

import java.io.IOException;
import java.net.UnknownHostException;

import ch.gitik.bpms.common.ConfigException;
import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageHandler;
import ch.gitik.bpms.common.MessageListener;

/**
 * Konsolen-Empfänger.
 */
public class ConsoleReceiver implements MessageListener {

   private static final String SERVER = "jabber.gitik.ch";

   private static final int PORT = 5222;

   private static final String USER = "bpmslistener";

   private static final String PWD = "bpmslistener";

   private MessageHandler xmpp = null;

   /**
    * Konstruktor.
    * @throws IOException
    *            IOException.
    */
   public ConsoleReceiver() throws IOException {
      this(SERVER, PORT, USER, PWD);
   }

   /**
    * Konstruktor.
    * @param server
    *           Servername.
    * @param port
    *           Portnummer.
    * @param user
    *           Benutzer.
    * @param password
    *           Passwort.
    * @throws IOException
    *            IOException.
    */
   public ConsoleReceiver(final String server, final int port, final String user, final String password)
         throws IOException {
      System.out.println("Starting XMPP-ConsoleReceiver...");

      XMPPConfig config = new XMPPConfig(server, port, user, password, "bpmstest@conference.ubuntuserver",
            true);

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
    * Run a simple xmpp client. Receive text messages Start: java xmppClient
    * xmppAddress PortNumber
    * @param argv
    *           Argumente.
    * @throws IOException
    *            IOException.
    */
   public static void main(final String[] argv) throws IOException {
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

   /*
    * @see ch.gitik.bpms.common.MessageListener#messageReceived(ch.gitik.bpms.common.Message)
    */
   public final void messageReceived(final Message msg) {
      System.out.println(msg);
   }
}

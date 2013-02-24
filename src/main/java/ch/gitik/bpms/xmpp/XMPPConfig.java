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

import ch.gitik.bpms.common.AbstractConfig;
import ch.gitik.bpms.common.ConfigException;

/**
 * Konfiguration für XMPP.
 * @author Roland Gisler
 */
public class XMPPConfig extends AbstractConfig {

   public static final String DEFAULT_HOST = "localhost";

   public static final int DEFAULT_PORT = 5222;

   public static final String DEFAULT_USER = "user";

   public static final String DEFAULT_PASSWORD = "password";

   public static final String DEFAULT_RECEIVER = "bpmsreceiver";

   public static final boolean IS_CONFERENCE = false;

   private String server = DEFAULT_HOST;

   private int port = DEFAULT_PORT;

   private String user = DEFAULT_USER;

   private String password = DEFAULT_PASSWORD;

   private String receiver = DEFAULT_RECEIVER;

   private boolean conference = IS_CONFERENCE;

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
    * @param receiver
    *           Empfänger
    * @param conf
    *           Flag für Konfig.
    */
   public XMPPConfig(final String server, final int port, final String user, final String password,
         final String receiver, final boolean conf) {
      this.server = server;
      this.port = port;
      this.user = user;
      this.password = password;
      this.receiver = receiver;
      this.conference = conf;
   }

   /**
    * Liefert Passwort.
    * @return Passwort.
    */
   public final String getPassword() {
      return password;
   }

   /**
    * Setzt das Passwort.
    * @param password
    *           Passwort.
    */
   public final void setPassword(final String password) {
      this.password = password;
   }

   /**
    * Liefert den Port.
    * @return Port.
    */
   public final int getPort() {
      return port;
   }

   /**
    * Setzt den Port.
    * @param port
    *           Portnummer.
    */
   public final void setPort(final int port) {
      this.port = port;
   }

   /**
    * Liefert den Server.
    * @return Servername.
    */
   public final String getServer() {
      return server;
   }

   /**
    * Setzt den Server.
    * @param server
    *           Servername.
    */
   public final void setServer(final String server) {
      this.server = server;
   }

   /**
    * Liefert den Benutzer.
    * @return Username.
    */
   public final String getUser() {
      return user;
   }

   /**
    * Setzt den Benutzer.
    * @param user
    *           Benutzername.
    */
   public final void setUser(final String user) {
      this.user = user;
   }

   /*
    * @see ch.gitik.bpms.common.AbstractConfig#validate()
    */
   @Override
   public boolean validate() throws ConfigException {
      return true;
   }

   /**
    * Liefert den Empfänger.
    * @return Empfänger.
    */
   public final String getReceiver() {
      return receiver;
   }

   /**
    * Setzt den Empfänger.
    * @param receiver
    *           Empfänger.
    */
   public final void setReceiver(final String receiver) {
      this.receiver = receiver;
   }

   /**
    * Liefert Konferenz.
    * @return Konferenz.
    */
   public final boolean isConference() {
      return conference;
   }

   /**
    * Setz Konferenz-Modus.
    * @param conference
    *           Konferenzmodus.
    */
   public final void setConference(final boolean conference) {
      this.conference = conference;
   }
}

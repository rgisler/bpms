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
 * $Id: XMPPConfig.java 264 2009-07-17 13:57:06Z rog $
 */
package ch.gitik.bpms.xmpp;

import ch.gitik.bpms.common.AbstractConfig;
import ch.gitik.bpms.common.ConfigException;

/**
 *
 * @author Roland Gisler
 * @version $Revision: 264 $
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
    * @param server
    * @param port
    * @param user
    * @param password
    * @param receiver
    * @param conf
    */
   public XMPPConfig(String server, int port, String user, String password, String receiver, boolean conf) {
      this.server = server;
      this.port = port;
      this.user = user;
      this.password = password;
      this.receiver = receiver;
      this.conference = conf;
   }

   /**
    *
    * @return Passwort.
    */
   public String getPassword() {
      return password;
   }

   /**
    *
    * @param password
    */
   public void setPassword(String password) {
      this.password = password;
   }

   /**
    *
    * @return Port.
    */
   public int getPort() {
      return port;
   }

   /**
    *
    * @param port
    */
   public void setPort(int port) {
      this.port = port;
   }

   /**
    *
    * @return Server.
    */
   public String getServer() {
      return server;
   }

   /**
    *
    * @param server
    */
   public void setServer(String server) {
      this.server = server;
   }

   /**
    *
    * @return Username.
    */
   public String getUser() {
      return user;
   }

   /**
    *
    * @param user
    */
   public void setUser(String user) {
      this.user = user;
   }

   /**
    * @see ch.gitik.bpms.common.AbstractConfig#validate()
    */
   @Override
   public boolean validate() throws ConfigException {
      return true;
   }

   public String getReceiver() {
      return receiver;
   }

   public void setReceiver(String receiver) {
      this.receiver = receiver;
   }

   public boolean isConference() {
      return conference;
   }

   public void setConference(boolean conference) {
      this.conference = conference;
   }

}

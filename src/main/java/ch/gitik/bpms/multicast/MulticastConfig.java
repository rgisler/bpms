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

import ch.gitik.bpms.common.AbstractConfig;
import ch.gitik.bpms.common.ConfigException;

/**
 * @author Roland Gisler
 */
public class MulticastConfig extends AbstractConfig {

   public static final String DEFAULT_IP = "224.168.0.10";

   public static final int DEFAULT_PORT = 4000;

   private String ip = DEFAULT_IP;

   private int port = DEFAULT_PORT;

   /**
    * Konstruktor.
    */
   public MulticastConfig() {
      this(DEFAULT_IP, DEFAULT_PORT);
   }

   /**
    * Konstruktor.
    * @param ip IP-Adresse.
    */
   public MulticastConfig(final String ip) {
      this(ip, DEFAULT_PORT);
   }

   /**
    * Konstruktor.
    * @param ip IP-Adresse.
    * @param port Portnummer.
    */
   public MulticastConfig(final String ip, final int port) {
      this.ip = ip;
      this.port = port;
   }

   /**
    * Liefert die IP.
    * @return IP-Adresse.
    */
   public final String getIp() {
      return ip;
   }

   /**
    * Setzt die IP.
    * @param ip
    *           IP-Adresse.
    */
   public final void setIp(final String ip) {
      this.ip = ip;
   }

   /**
    * Liefert den Port.
    * @return Portnummer.
    */
   public final int getPort() {
      return port;
   }

   /**
    * Setzt den POrt.
    * @param port
    *           Portnummer.
    */
   public final void setPort(final int port) {
      this.port = port;
   }

   /*
    * @see ch.gitik.bpms.common.AbstractConfig#validate()
    */
   @Override
   public boolean validate() throws ConfigException {
      if ((ip == null) || (port < 1)) {
         throw new ConfigException();
      }
      return true;
   }

}

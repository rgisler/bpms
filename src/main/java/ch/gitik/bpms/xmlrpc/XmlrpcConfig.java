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

import ch.gitik.bpms.common.AbstractConfig;
import ch.gitik.bpms.common.ConfigException;

/**
 * @author Roland Gisler
 */
public class XmlrpcConfig extends AbstractConfig {

   public static final String DEFAULT_HOST = "localhost";

   public static final int DEFAULT_PORT = 6666;

   private String host = DEFAULT_HOST;

   private int port = DEFAULT_PORT;

   /**
    * Konstruktor.
    */
   public XmlrpcConfig() {
      this(DEFAULT_HOST, DEFAULT_PORT);
   }

   /**
    * Konstruktor.
    * @param host
    *           Host.
    */
   public XmlrpcConfig(final String host) {
      this(host, DEFAULT_PORT);
   }

   /**
    * Konstruktor.
    * @param host
    *           Host.
    * @param port
    *           Portnummer.
    */
   public XmlrpcConfig(final String host, final int port) {
      this.host = host;
      this.port = port;
   }

   /**
    * Liefert den Port.
    * @return Portnummer.
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

   /*
    * @see ch.gitik.bpms.common.AbstractConfig#validate()
    */
   @Override
   public final boolean validate() throws ConfigException {
      if ((this.host == null) || (this.port < 1)) {
         throw new ConfigException();
      }
      return true;
   }

   /**
    * Liefert den Host.
    * @return Hostname.
    */
   public final String getHost() {
      return host;
   }

   /**
    * Setzt den Host.
    * @param host Hostname.
    */
   public final void setHost(final String host) {
      this.host = host;
   }

}

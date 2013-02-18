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

import java.util.Hashtable;
import java.util.Map;

/**
 * Factory fuer XMPP-Handler. Liefert einen auf User,Server und Port bezogenen
 * XMPP-Handler zurueck.
 * @author Roland Gisler
 */
public final class XMPPFactory {

   /** Map mit Handlern. */
   private static Map<String, XMPPHandler> xmppMap = new Hashtable<String, XMPPHandler>();

   /**
    * Privater Konstruktor.
    */
   private XMPPFactory() {
   }

   /**
    * Factorymethode fuer XMPP-Handler.
    * @param config
    *           XMPPConfig.
    * @return XMPPHandler.
    */
   public static XMPPHandler getXMPPHandler(final XMPPConfig config) {
      XMPPHandler xmpp = null;
      String key = config.getUser() + "@" + config.getServer() + ":" + config.getPort();
      if (xmppMap.containsKey(key)) {
         xmpp = xmppMap.get(key);
      } else {
         xmpp = new XMPPHandler(config);
         xmppMap.put(key, xmpp);
      }
      return xmpp;
   }
}

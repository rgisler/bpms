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
 * Konkrete Factory fuer XMPP Sender.
 * @author Roland Gisler
 */
public final class XMPPSenderFactory {

   /** Map mit Sendern. */
   private static Map<String, XMPPSender> xmppMap = new Hashtable<String, XMPPSender>();

   /**
    * Privater Konstruktor.
    */
   private XMPPSenderFactory() {
   }

   /**
    * Factory für XMPPSender.
    * @param config
    *           Konfiguration.
    * @return XMPPSender.
    */
   public static XMPPSender getXMPPSender(final XMPPConfig config) {
      XMPPSender sender = null;
      String key = config.getUser() + "@" + config.getServer() + ":" + config.getPort();
      if (xmppMap.containsKey(key)) {
         sender = xmppMap.get(key);
      } else {
         sender = new XMPPSender(config);
         xmppMap.put(key, sender);
      }
      return sender;
   }
}

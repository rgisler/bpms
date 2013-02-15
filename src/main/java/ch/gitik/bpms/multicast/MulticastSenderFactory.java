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

import java.util.Hashtable;
import java.util.Map;

import ch.gitik.bpms.common.ConfigException;

/**
 * Einfache Factory fuer MulticastSender. Erzeugt fuer jeden Empfaenger (IP und
 * Port) einen eigenen Sender.
 * @author Roland Gisler
 */
public class MulticastSenderFactory {

   private static Map<String, MulticastSender> recMap = new Hashtable<String, MulticastSender>();

   public static MulticastSender getSender(MulticastConfig config) throws ConfigException {
      MulticastSender sender = null;

      if (config.validate()) {
         String key = config.getIp() + ":" + config.getPort();
         if (recMap.containsKey(key)) {
            sender = recMap.get(key);
         } else {
            sender = new MulticastSender(config);
            recMap.put(key, sender);
         }
      }
      return sender;
   }
}

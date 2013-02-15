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
 * @author Roland Gisler
 */
public class MulticastReceiverFactory {

   private static Map<String, MulticastReceiver> recMap = new Hashtable<String, MulticastReceiver>();

   public static MulticastReceiver getReceiver(MulticastConfig config) throws ConfigException {
      MulticastReceiver receiver = null;

      if (config.validate()) {

         String key = config.getIp() + ":" + config.getPort();
         if (recMap.containsKey(key)) {
            receiver = recMap.get(key);
         } else {
            receiver = new MulticastReceiver(config);
            recMap.put(key, receiver);
         }
      }
      return receiver;
   }
}

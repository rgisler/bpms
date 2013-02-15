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
 * $Id: TransportType.java 289 2009-07-20 13:53:14Z rog $
 */
package ch.gitik.bpms.common;

/**
 * Enumeration mit moeglichen TransportTypen.
 * @author Roland Gisler
 * @version $Revision: 289 $
 */
public enum TransportType {
   MULTICAST, XMPP, XMLRPC;

   /**
    * Liefert eine Beschreibung des Transporttyps.
    * @param type TransportType.
    * @return String Beschreibung.
    */
   public String getFullType(TransportType type) {
      String result = "unknown message type";
      switch (type) {
      case MULTICAST:
         result = "IP Multicast";
         break;
      case XMPP:
         result = "XMPP (Jabber)";
         break;
      case XMLRPC:
         result = "XML-RPC";
         break;
      }
      return result;
   }
}

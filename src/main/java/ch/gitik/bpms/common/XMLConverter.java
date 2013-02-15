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
 * $Id: XMLConverter.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.common;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;

/**
 * Konvertiert Messages in XML und zurueck.
 * Verwendet XStream um ein Message-Objekt in XML zu transformieren und zurueck.
 * @author Roland Gisler
 * @version $Revision: 214 $
 */
public class XMLConverter {

   private static XStream xstream;

   /**
    * Factory fuer XStream Singleton.
    * @return XStream Singleton von XStream.
    */
   private static XStream getXStream() {
      if (xstream == null) {
         xstream = new XStream();
         xstream.alias("message", ch.gitik.bpms.common.Message.class);
      }
      return xstream;
   }

   /**
    * Liefert ein Message Objekt vom uebergebenen XML zurueck. 
    * @param xml XML-Repraesentation.
    * @return Message Objekt.
    */
   public static Message getMessage(String xml) {
      Message msg = new Message();
      msg.setType(MessageType.RAW);
      if (xml != null) {
         if (xml.contains("<message>")) {
            try {
               msg = (Message) getXStream().fromXML(xml);
            } catch (StreamException e) {
               msg.setText(e.getMessage());
            }
         } else {
            msg.setText(xml);
         }
      } else {
         msg.setText("empty message received");
      }

      return msg;
   }

   /**
    * Liefert das XML eines Message Objektes zurueck.
    * @param msg Message Objekt.
    * @return String XML Repraesentation.
    */
   public static String getXML(Message msg) {
      return getXStream().toXML(msg);
   }
}

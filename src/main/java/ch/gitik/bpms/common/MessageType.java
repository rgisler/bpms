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
package ch.gitik.bpms.common;

/**
 * Enumeration mit moeglichen MessageTypes.
 * @author Roland Gisler
 */
public enum MessageType {

   LOG4J, CC, ANT, RAW;

   /**
    * Liefert eine Beschreibung des MessageType.
    * @param type
    *           MessageType
    * @return String Beschreibung.
    */
   public String getFullType(final MessageType type) {
      String result;
      switch (type) {
      case LOG4J:
         result = "Appache Logging Log4J";
         break;
      case CC:
         result = "CruiseControl Contiuous Integration";
         break;
      case ANT:
         result = "Apache ANT Build Tool";
         break;
      case RAW:
         result = "Raw message";
         break;
      default:
         result = "unkown message Type";
         break;
      }
      return result;
   }
}

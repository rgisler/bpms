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
 * Enumeration mit moeglichen TransportTypen.
 * @author Roland Gisler
 */
public enum BpmsState {
   INIT, STOPPED, STARTING, STARTED, STOPPING, ERROR, RESUMING;

   /**
    * Liefert eine Beschreibung des Status.
    * @param state
    *           BpmsState.
    * @return String Beschreibung.
    */
   public String getFullType(final BpmsState state) {
      String result;
      switch (state) {
      case INIT:
         result = "BPMS wird initialisiert.";
         break;
      case STARTING:
         result = "BPMS wird gestartet...";
         break;
      case STARTED:
         result = "BPMS ist aktiv.";
         break;
      case STOPPING:
         result = "BPMS wird gestoppt... ";
         break;
      case STOPPED:
         result = "BPMS ist inaktiv. ";
         break;
      case ERROR:
         result = "BPMS in undefiniertem Fehlerzustand.";
         break;
      case RESUMING:
         result = "BPMS in Wiederherstellung.";
         break;
      default:
         result = "BPMS undefiniert";
         break;
      }
      return result;
   }
}

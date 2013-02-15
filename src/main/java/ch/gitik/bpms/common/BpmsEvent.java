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
 * $Id: BpmsEvent.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.common;

import java.util.EventObject;

/**
 * Spezialisierter Event fuer BPMS. Enthaelt einen State.
 * @author Roland Gisler
 * @version $Revision: 214 $
 */
public class BpmsEvent extends EventObject {

   private BpmsState state = BpmsState.INIT;

   /**
    * Konstruktor.
    * @param object
    *           Object.
    * @param state
    *           State.
    */
   public BpmsEvent(Object object, BpmsState state) {
      super(object);
      this.state = state;
   }

   /**
    * Liefert den State zurueck.
    * @return State.
    */
   public BpmsState getState() {
      return state;
   }
}

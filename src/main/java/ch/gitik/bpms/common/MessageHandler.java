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
 * $Id: MessageHandler.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.common;


/**
 * Interface fuer MessageHandler.
 * Schnittstelle um Kommunikationsdienst zu aktivieren, deaktiveren und Statusabfrage.
 * Handling der MessageListener-Schnittstelle.
 * @author Roland Gisler
 * @version $Revision: 214 $
 */
public interface MessageHandler {

   /**
    * Liefert zurueck, ob der Handler aktiv ist.
    * @return boolean Aktiv.
    */
   public boolean isActive();

   /**
    * Aktiviert den Handler und den Transport.
    * @throws ConfigException Exception bei ungueltiger Konfiguration.
    */
   public void activate() throws ConfigException;

   /**
    * Deaktiviert den MessageHandler.
    */
   public void deactivate();
   
   /**
    * Registriert einen MessageListener.
    * @param listener MessageListener.
    */
   public void addMessageListener(MessageListener listener);

   /**
    * Deregistriert einen MessageListener.
    * @param listener MessageListener.
    */
   public void removeMessageListener(MessageListener listener);
}

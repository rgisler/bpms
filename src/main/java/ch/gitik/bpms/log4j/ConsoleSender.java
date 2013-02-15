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
 * $Id: ConsoleSender.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.log4j;

import org.apache.log4j.Logger;

/**
 * 
 * @author Roland Gisler
 * @version $Revision: 214 $ 
 */
public class ConsoleSender {

   private static Logger LOG = Logger.getRootLogger();

   /**
    * 
    * @param args
    */
   public static void main(String[] args) {
      LOG.debug("Das ist eine Debug-Message");
      LOG.info("Das ist eine Info-Message");
      LOG.warn("Das ist eine Warn-Message");
      LOG.error("Das ist eine Error-Message");
      LOG.fatal("Das ist eine Fatal-Message");
   }
}

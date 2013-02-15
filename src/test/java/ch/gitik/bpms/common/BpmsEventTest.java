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
 * $Id: BpmsEventTest.java 290 2009-07-20 14:50:42Z rog $
 */
package ch.gitik.bpms.common;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Roland Gisler
 * @version $Revision: 290 $ 
 */
public class BpmsEventTest {

   private BpmsEvent test = null;
   
   @Before
   public void setUp() throws Exception {
      this.test = new BpmsEvent(this,BpmsState.INIT);
   }

   @After
   public void tearDown() throws Exception {
      this.test = null;
   }

   @Test
   public void testGetState() {
      assertEquals(this, this.test.getSource());
      assertEquals(BpmsState.INIT, this.test.getState());
   }

}

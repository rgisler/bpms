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
package ch.gitik.bpms.cruisecontrol;

import net.sourceforge.cruisecontrol.CruiseControlException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Roland Gisler
 */
public class BpmsMulticastPublisherTest {

   private BpmsMulticastPublisher publisher = null;

   @Before
   public void setUp() throws Exception {
      this.publisher = new BpmsMulticastPublisher();
   }

   @After
   public void tearDown() throws Exception {
      this.publisher = null;
   }

   @Test
   public void testValidate() {
      Exception ce = null;
      try {
         this.publisher.validate();
      } catch (CruiseControlException e) {
         ce = e;
      }
      Assert.assertNotNull(ce);
   }
}

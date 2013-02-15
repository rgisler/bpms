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
 * $Id: MulticastFactoryTest.java 290 2009-07-20 14:50:42Z rog $
 */
package ch.gitik.bpms.multicast;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.gitik.bpms.common.ConfigException;

/**
 *
 * @author Roland Gisler
 * @version $Revision: 290 $
 */
public class MulticastFactoryTest {

   private static final int PORT_1000 = 1000;

   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testGetHandler() {
      try {
         MulticastHandler handler1 = MulticastFactory.getMulticastHandler(new MulticastConfig("1.1.1.1", PORT_1000));
         MulticastHandler handler2 = MulticastFactory.getMulticastHandler(new MulticastConfig("2.2.2.2", PORT_1000));
         MulticastHandler handler3 = MulticastFactory.getMulticastHandler(new MulticastConfig("1.1.1.1", PORT_1000));
         MulticastHandler handler4 = MulticastFactory.getMulticastHandler(new MulticastConfig("2.2.2.2", PORT_1000));
         Assert.assertNotNull(handler1);
         Assert.assertNotNull(handler2);
         Assert.assertNotNull(handler3);
         Assert.assertNotNull(handler4);
         Assert.assertNotSame(handler1, handler2);
         Assert.assertEquals(handler1, handler3);
         Assert.assertEquals(handler2, handler4);
      } catch (ConfigException e) {
         Assert.fail(e.toString());
      }
   }
}

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
 * $Id: MulticastReceiverFactoryTest.java 281 2009-07-20 09:43:36Z rog $
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
 * @version $Revision: 281 $
 */
public class MulticastReceiverFactoryTest {

   private static final int PORT_1000 = 1000;

   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testGetReceiver() {
      try {
         MulticastReceiver receiver1 = MulticastReceiverFactory.getReceiver(new MulticastConfig("1.1.1.1",PORT_1000));
         MulticastReceiver receiver2 = MulticastReceiverFactory.getReceiver(new MulticastConfig("2.2.2.2",PORT_1000));
         MulticastReceiver receiver3 = MulticastReceiverFactory.getReceiver(new MulticastConfig("1.1.1.1",PORT_1000));
         MulticastReceiver receiver4 = MulticastReceiverFactory.getReceiver(new MulticastConfig("2.2.2.2",PORT_1000));
         Assert.assertNotNull(receiver1);
         Assert.assertNotNull(receiver2);
         Assert.assertNotNull(receiver3);
         Assert.assertNotNull(receiver4);
         Assert.assertNotSame(receiver1, receiver2);
         Assert.assertEquals(receiver1, receiver3);
         Assert.assertEquals(receiver2, receiver4);
      } catch (ConfigException e) {
         Assert.fail(e.toString());
      }
   }

}

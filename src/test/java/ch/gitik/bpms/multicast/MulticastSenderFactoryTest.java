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
package ch.gitik.bpms.multicast;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.gitik.bpms.common.ConfigException;

/**
 * @author Roland Gisler
 */
public class MulticastSenderFactoryTest {

   private static final int PORT_1000 = 1000;

   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testGetSender() {
      try {
         MulticastSender sender1 = MulticastSenderFactory
               .getSender(new MulticastConfig("1.1.1.1", PORT_1000));
         MulticastSender sender2 = MulticastSenderFactory
               .getSender(new MulticastConfig("2.2.2.2", PORT_1000));
         MulticastSender sender3 = MulticastSenderFactory
               .getSender(new MulticastConfig("1.1.1.1", PORT_1000));
         MulticastSender sender4 = MulticastSenderFactory
               .getSender(new MulticastConfig("2.2.2.2", PORT_1000));
         Assert.assertNotNull(sender1);
         Assert.assertNotNull(sender2);
         Assert.assertNotNull(sender3);
         Assert.assertNotNull(sender4);
         Assert.assertNotSame(sender1, sender2);
         Assert.assertEquals(sender1, sender3);
         Assert.assertEquals(sender2, sender4);
      } catch (ConfigException e) {
         Assert.fail(e.toString());
      }
   }

}

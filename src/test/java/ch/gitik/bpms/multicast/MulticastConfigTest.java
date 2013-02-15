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

/**
 * @author Roland Gisler
 */
public class MulticastConfigTest {

   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testSetGet() throws Exception {
      MulticastConfig config = new MulticastConfig();
      Assert.assertEquals(config.getIp(), MulticastConfig.DEFAULT_IP);
      Assert.assertEquals(config.getPort(), MulticastConfig.DEFAULT_PORT);
      config = new MulticastConfig("1.1.1.1");
      Assert.assertEquals(config.getIp(), "1.1.1.1");
      config.setIp("2.2.2.2");
      config.setPort(2222);
      Assert.assertEquals(config.getIp(), "2.2.2.2");
      Assert.assertEquals(config.getPort(), 2222);
   }
}

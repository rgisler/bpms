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
package ch.gitik.bpms.ant;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Roland Gisler
 */
public class BpmsXmppTaskTest {

   private BpmsXmppTask task = null;

   @Before
   public void setUp() throws Exception {
      this.task = new BpmsXmppTask();
   }

   @After
   public void tearDown() throws Exception {
      this.task = null;
   }

   @Test
   public void testExecute() {
      this.task.setServer("saturn");
      this.task.setUser("builder");
      this.task.setPassword("builder");
      this.task.setReceiver("listener");
      // this.task.execute();
      Assert.assertEquals("saturn", this.task.getServer());
      Assert.assertEquals("builder", this.task.getUser());
      Assert.assertEquals("listener", this.task.getReceiver());
   }
}

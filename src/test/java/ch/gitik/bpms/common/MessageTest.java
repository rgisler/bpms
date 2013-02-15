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
 * $Id: MessageTest.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Roland Gisler
 * @version $Revision: 214 $
 */
public class MessageTest {

   public static final String GROUP = "group";

   public static final String PROJECT = "project";

   public static final String TASK = "task";

   public static final String TEXT = "text";

   public static final String URL = "url";

   private Message message = null;

   @Before
   public void setUp() throws Exception {
      this.message = new Message();
      this.message.setGroup(GROUP);
      this.message.setProject(PROJECT);
      this.message.setTask(TASK);
      this.message.setText(TEXT);
      this.message.setUrl(URL);
      this.message.setType(MessageType.RAW);
   }

   @After
   public void tearDown() throws Exception {
      this.message = null;
   }

   @Test
   public void testGetGroup() {
      Assert.assertEquals(GROUP, this.message.getGroup());
   }

   @Test
   public void testGetProject() {
      Assert.assertEquals(PROJECT, this.message.getProject());
   }

   @Test
   public void testGetTask() {
      Assert.assertEquals(TASK, this.message.getTask());
   }

   @Test
   public void testGetText() {
      Assert.assertEquals(TEXT, this.message.getText());
   }

   @Test
   public void testGetUrl() {
      Assert.assertEquals(URL, this.message.getUrl());
   }

   @Test
   public void testGetHost() {
      try {
         Assert.assertEquals(InetAddress.getLocalHost().getHostName(), this.message.getHost());
      } catch (UnknownHostException e) {
         Assert.fail(e.toString());
      }
   }

   @Test
   public void testGetTimestamp() {
      Assert.assertTrue(this.message.getTimestamp() > 0);
   }
   
   @Test
   public void testMessageType() {
      Assert.assertEquals(MessageType.RAW, this.message.getType());
      Assert.assertTrue(this.message.isRaw());
      Assert.assertFalse(this.message.isAnt());
      Assert.assertFalse(this.message.isLog4j());
      Assert.assertFalse(this.message.isCruiseControl());
   }

   @Test
   public void testToString() {
      String test = this.message.toString();
      Assert.assertNotNull(test);
   }

}

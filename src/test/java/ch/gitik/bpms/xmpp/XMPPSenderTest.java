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
 * $Id: XMPPSenderTest.java 274 2009-07-19 11:11:19Z rog $
 */
package ch.gitik.bpms.xmpp;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Roland Gisler
 * @version $Revision: 274 $
 */
public class XMPPSenderTest {

   private XMPPConfig config = null;

   private XMPPSender sender = null;

   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
      this.sender = null;
      this.config = null;
   }

   @Ignore @Test
   public void testSendMessageAllUser() {
      this.config = new XMPPConfig("jabber.gitik.ch", 5222, "bpmstest", "bpmstest", null, false);
      this.sender = new XMPPSender(this.config);
      this.sender.sendMessage(null, "BPMS Test Message an alle angemeldeten Clienten!");
   }

   @Ignore @Test
   public void testSendMessageDedicatedUser() {
      this.config = new XMPPConfig("jabber.gitik.ch", 5222, "bpmstest", "bpmstest", null, false);
      this.sender = new XMPPSender(this.config);
      this.sender.sendMessage("rog@ubuntuserver", "BPMS Test Message nur an MIMAS.");
   }

   @Ignore @Test
   public void testSendMessageConference() {
      this.config = new XMPPConfig("jabber.gitik.ch", 5222, "bpmstest", "bpmstest", null, true);
      this.sender = new XMPPSender(this.config);
      this.sender.sendMessage("bpmstest@conference.ubuntuserver", "BPMS Test Message nur an bpmstest-Conferenc (Multiuser-Chat).");
   }

}
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
import net.sourceforge.cruisecontrol.util.ValidationHelper;
import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.xmpp.XMPPConfig;
import ch.gitik.bpms.xmpp.XMPPSender;
import ch.gitik.bpms.xmpp.XMPPSenderFactory;

/**
 *
 * @author Roland Gisler
 */
public class BpmsXmppPublisher extends AbstractBpmsPublisher {

   private String host;

   private int port = 5222;

   private String user;
   private String password;

   private String receiver;

   private XMPPSender xmpp = null;

   public void setHost(String host) {
      this.host = host;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public void setUser(String user) {
      this.user = user;
   }

   /**
    * Validate that all the mandatory parameters were specified in order to
    * properly initial the Jabber client service. Note that this is called after
    * the configuration file is read.
    *
    * @throws CruiseControlException
    *            if there was a configuration error.
    */
   public void validate() throws CruiseControlException {
      super.validate();
      ValidationHelper.assertIsSet(this.host, "host", this.getClass());
      ValidationHelper.assertIsSet(this.user, "user", this.getClass());
      ValidationHelper.assertIsSet(this.password, "password", this.getClass());
      ValidationHelper.assertIsSet(this.receiver, "receiver", this.getClass());
   }

   protected void sendMessage(Message msg) {
      XMPPConfig config = new XMPPConfig(this.host, this.port, this.user, this.password, null, false);
      this.xmpp = XMPPSenderFactory.getXMPPSender(config);
      this.xmpp.sendMessage(this.receiver, msg);
   }

   public void setReceiver(String receiver) {
      this.receiver = receiver;
   }

}

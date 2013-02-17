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
 * Publisher fuer XMPP Messages.
 * @author Roland Gisler
 */
public class BpmsXmppPublisher extends AbstractBpmsPublisher {

   /** Defaultport fuer XMPP. */
   private static final int XMPP_DEFAULT_PORT = 5222;

   private String host;

   private int port = XMPP_DEFAULT_PORT;

   private String user;

   private String password;

   private String receiver;

   private XMPPSender xmpp = null;

   /**
    * Setzt den Host.
    * @param host
    *           Host.
    */
   public final void setHost(final String host) {
      this.host = host;
   }

   /**
    * Setzt das Passwort.
    * @param password
    *           Passwort.
    */
   public final void setPassword(final String password) {
      this.password = password;
   }

   /**
    * Setzt den Port.
    * @param port
    *           Port.
    */
   public final void setPort(final int port) {
      this.port = port;
   }

   /**
    * Setzt den User.
    * @param user
    *           User.
    */
   public final void setUser(final String user) {
      this.user = user;
   }

   /**
    * Validate that all the mandatory parameters were specified in order to
    * properly initial the Jabber client service. Note that this is called after
    * the configuration file is read.
    * @throws CruiseControlException
    *            if there was a configuration error.
    */
   public final void validate() throws CruiseControlException {
      super.validate();
      ValidationHelper.assertIsSet(this.host, "host", this.getClass());
      ValidationHelper.assertIsSet(this.user, "user", this.getClass());
      ValidationHelper.assertIsSet(this.password, "password", this.getClass());
      ValidationHelper.assertIsSet(this.receiver, "receiver", this.getClass());
   }

   /*
    * @see
    * ch.gitik.bpms.cruisecontrol.AbstractBpmsPublisher#sendMessage(ch.gitik
    * .bpms.common.Message)
    */
   protected final void sendMessage(final Message msg) {
      XMPPConfig config = new XMPPConfig(this.host, this.port, this.user, this.password, null, false);
      this.xmpp = XMPPSenderFactory.getXMPPSender(config);
      this.xmpp.sendMessage(this.receiver, msg);
   }

   /**
    * Setzt den Empfaenger.
    * @param receiver
    *           Receiver.
    */
   public final void setReceiver(final String receiver) {
      this.receiver = receiver;
   }

}

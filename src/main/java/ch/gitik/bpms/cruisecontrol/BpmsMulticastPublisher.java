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

import java.io.IOException;
import java.net.UnknownHostException;

import net.sourceforge.cruisecontrol.CruiseControlException;
import net.sourceforge.cruisecontrol.util.ValidationHelper;
import ch.gitik.bpms.common.ConfigException;
import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.multicast.MulticastConfig;
import ch.gitik.bpms.multicast.MulticastSender;
import ch.gitik.bpms.multicast.MulticastSenderFactory;

/**
 *
 * @author Roland Gisler
 */
public class BpmsMulticastPublisher extends AbstractBpmsPublisher {

   private static final int DEFAULT_PORT = 5222;

   private String multicastip;

   private int port = DEFAULT_PORT;

   private MulticastSender multicast = null;

   public void setMulticastip(final String host) {
      this.multicastip = host;
   }

   public void setPort(final int port) {
      this.port = port;
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
      ValidationHelper.assertIsSet(multicastip, "multicastip", this.getClass());
   }

   /*
    * @see ch.gitik.bpms.cruisecontrol.AbstractBpmsPublisher#sendMessage(ch.gitik.bpms.common.Message)
    */
   protected void sendMessage(final Message msg) {
      MulticastConfig config = new MulticastConfig(this.multicastip, this.port);
      try {
         this.multicast = MulticastSenderFactory.getSender(config);
         this.multicast.send(msg);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (ConfigException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}

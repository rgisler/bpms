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
 * $Id: AbstractBpmsPublisher.java 214 2008-07-18 17:15:55Z rog $
 */
package ch.gitik.bpms.cruisecontrol;

import java.io.File;

import net.sourceforge.cruisecontrol.CruiseControlException;
import net.sourceforge.cruisecontrol.Publisher;
import net.sourceforge.cruisecontrol.util.ValidationHelper;
import net.sourceforge.cruisecontrol.util.XMLLogHelper;

import org.jdom.Element;

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageFactory;
import ch.gitik.bpms.common.MessageType;

/**
 * 
 * @author Roland Gisler
 * @version $Revision: 214 $
 */
public abstract class AbstractBpmsPublisher implements Publisher {

   private String url;
   private MessageFactory messageFactory = new MessageFactory(MessageType.CC);

   public void setGroup(String group) {
      this.messageFactory.setGroup(group);
   }

   /**
    * Publish the results to the Jabber transport via an instant message.
    * 
    * @param cruisecontrolLog
    * @throws CruiseControlException
    */
   public void publish(Element cruisecontrolLog) throws CruiseControlException {

      // Generate the message to be sent to the recipient
      XMLLogHelper logHelper = new XMLLogHelper(cruisecontrolLog);
      String logFileName = logHelper.getLogFileName();
      String baseLogFileName = logFileName.substring(logFileName.lastIndexOf(File.separator) + 1, logFileName
            .lastIndexOf("."));

      String state = (logHelper.isBuildSuccessful() ? "SUCCESSFUL" : "FAILED");
      
      StringBuffer localUrl = new StringBuffer();
      localUrl.append(url);
      if (url.indexOf("?") == -1) {
         localUrl.append("?");
      } else {
         localUrl.append("&");
      }
      localUrl.append("log=");
      localUrl.append(baseLogFileName);
            
      Message msg = this.messageFactory.createMessage();
      msg.setProject(logHelper.getProjectName());
      msg.setTask("finished");
      msg.setText("BUILD " + state);
      msg.setUrl(localUrl.toString());

      this.sendMessage(msg);
   }

   /**
    * 
    * @param msg
    */
   protected abstract void sendMessage(Message msg);

   public void setURL(String url) {
      this.url = url;
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
      ValidationHelper.assertIsSet(url, "url", this.getClass());
   }

}

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
package ch.gitik.bpms.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageFactory;
import ch.gitik.bpms.common.MessageType;

/**
 * @author Roland Gisler
 */
public abstract class AbstractBpmsAppender extends AppenderSkeleton {

   private static MessageFactory messageFactory = new MessageFactory(MessageType.LOG4J);

   /*
    * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
    */
   protected void append(final LoggingEvent arg0) {
      Message msg = messageFactory.createMessage();
      msg.setProject(arg0.getLoggerName());
      msg.setTask(arg0.getLevel().toString());
      msg.setText(arg0.getRenderedMessage());
      this.sendMessage(msg);
   }

   /*
    * @see org.apache.log4j.AppenderSkeleton#requiresLayout()
    */
   public final boolean requiresLayout() {
      return false;
   }

   /**
    * Sendet eine Message.
    * @param msg
    *           Message.
    */
   public abstract void sendMessage(Message msg);

}

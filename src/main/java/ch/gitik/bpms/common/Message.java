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
 * $Id: Message.java 285 2009-07-20 11:15:50Z rog $
 */
package ch.gitik.bpms.common;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Zentrale Messageklasse des BPMS. Diese Klasse repraesentiert eine BPMS
 * Message. Sie wird als XML serialisiert zwischen den Rechner uebertragen.
 * Timestamp und Host koennen nur vom Ursprung gesetzt werden und sind
 * Read-Only.
 *
 * @author Roland Gisler
 * @version $Revision: 285 $
 */
public class Message implements Serializable {

   private static final String SEPARATOR = " - ";

   private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");

   private long timestamp = 0;

   private String host = "unkown";

   private MessageType type = MessageType.RAW;

   private String group = null;

   private String project = null;

   private String task = null;

   private String text = null;

   private String url = null;

   /**
    * Konstruktor. Setzt den Timestamp und den Hostnamen.
    */
   public Message() {
      this.timestamp = System.currentTimeMillis();
      try {
         this.host = InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
         this.host = "unknown";
      }
   }

   /**
    * Liefert den Messagetext zurueck.
    *
    * @return String
    */
   public String getText() {
      return this.text;
   }

   /**
    * Setzt den Messagetext.
    *
    * @param text
    *           String
    */
   public void setText(String text) {
      this.text = text;
   }

   /**
    * Liefert den Projektnamen zurueck.
    *
    * @return String
    */
   public String getProject() {
      return project;
   }

   /**
    * Setzt den Projektnamen.
    *
    * @param project
    *           String
    */
   public void setProject(String project) {
      this.project = project;
   }

   /**
    * Liefert den Timestamp zurueck.
    *
    * @return long
    */
   public long getTimestamp() {
      return timestamp;
   }

   /**
    * Liefert den Task zurueck.
    *
    * @return String
    */
   public String getTask() {
      return task;
   }

   /**
    * Setzt den Task.
    *
    * @param task
    *           String
    */
   public void setTask(String task) {
      this.task = task;
   }

   /**
    * @see java.lang.Object#toString()
    */
   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(sdf.format(new Date(this.timestamp)));
      buffer.append(SEPARATOR + this.type.toString());
      buffer.append(SEPARATOR + this.host);
      if (this.group != null) {
         buffer.append(SEPARATOR + this.group);
      }
      buffer.append(SEPARATOR + this.project);
      if (this.task != null) {
         buffer.append(SEPARATOR + this.task);
      }
      buffer.append(SEPARATOR + this.text);
      return buffer.toString();
   }

   /**
    * Liefert den Hostnamen zurueck.
    *
    * @return String
    */
   public String getHost() {
      return host;
   }

   /**
    * Liefert die Gruppe zurueck.
    *
    * @return String
    */
   public String getGroup() {
      return group;
   }

   /**
    * Setzt die Gruppe.
    *
    * @param group
    *           String
    */
   public void setGroup(String group) {
      this.group = group;
   }

   /**
    * Liefert die URL zurueck.
    *
    * @return String
    */
   public String getUrl() {
      return url;
   }

   /**
    * Setzt die URL.
    *
    * @param url
    *           String
    */
   public void setUrl(String url) {
      this.url = url;
   }

   /**
    * Liefert den Messagetype zurueck.
    *
    * @return MessageType
    */
   public MessageType getType() {
      return type;
   }

   /**
    * Setzt den MessageType.
    *
    * @param type
    *           MessageType
    */
   public void setType(MessageType type) {
      this.type = type;
   }

   /**
    * Testet auf ANT-Message.
    *
    * @return boolean
    */
   public boolean isAnt() {
      return (this.type == MessageType.ANT);
   }

   /**
    * Testet auf CruiseControl-Message.
    *
    * @return boolean
    */
   public boolean isCruiseControl() {
      return (this.type == MessageType.CC);
   }

   /**
    * Testet auf Log4J-Message.
    *
    * @return boolean
    */
   public boolean isLog4j() {
      return (this.type == MessageType.LOG4J);
   }

   /**
    * Testet auf RAW-Message.
    *
    * @return boolean
    */
   public boolean isRaw() {
      return (this.type == MessageType.RAW);
   }
}

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
package ch.gitik.bpms.common;

/**
 * Einfache Message-Factory. Diese Factory vereinfacht die Erzeugung von
 * Messages mit identischen Attributen.
 * @author Roland Gisler
 */
public class MessageFactory {

   private static final MessageType DEFAULT_TYPE = MessageType.RAW;

   private static final String DEFAULT_GROUP = "bpms";

   private static final String DEFAULT_PROJECT = "bpms";

   private MessageType type = DEFAULT_TYPE;

   private String group = DEFAULT_GROUP;

   private String project = DEFAULT_PROJECT;

   /**
    * Konstruktor.
    * @param type
    *           Default Type der Messages.
    * @param group
    *           Default Gruppenname der Messages.
    * @param project
    *           Default Projektname der Messages.
    */
   public MessageFactory(final MessageType type, final String group, final String project) {
      this.type = type;
      this.group = group;
      this.project = project;
   }

   /**
    * Konstruktor.
    * @param type
    *           Default Type der Messages.
    * @param group
    *           Default Gruppenname der Messages.
    */
   public MessageFactory(final MessageType type, final String group) {
      this(type, group, DEFAULT_PROJECT);
   }

   /**
    * Konstruktor.
    * @param type
    *           Default Type der Messages.
    */
   public MessageFactory(final MessageType type) {
      this(type, DEFAULT_GROUP, DEFAULT_PROJECT);
   }

   /**
    * Erzeugt eine neue Message. Die Attribute Type, Group und Project werden
    * mit den gesetzten Defaults belegt.
    * @return Message
    */
   public final Message createMessage() {
      Message msg = new Message();
      msg.setType(this.type);
      msg.setGroup(this.group);
      msg.setProject(this.project);
      return msg;
   }

   /**
    * Setzt den Gruppennamen.
    * @param group
    *           String Gruppenname.
    */
   public final void setGroup(final String group) {
      this.group = group;
   }

   /**
    * Setzt den Projektnamen.
    * @param project
    *           String Projektname
    */
   public final void setProject(final String project) {
      this.project = project;
   }

   /**
    * Setzt den Message Type.
    * @param type
    *           MessageType.
    */
   public final void setType(final MessageType type) {
      this.type = type;
   }
}

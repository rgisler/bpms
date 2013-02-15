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
 * $Id: AbstractBpmsTask.java 281 2009-07-20 09:43:36Z rog $
 */
package ch.gitik.bpms.ant;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.Task;

import ch.gitik.bpms.common.Message;
import ch.gitik.bpms.common.MessageFactory;
import ch.gitik.bpms.common.MessageType;

/**
 * ANT-Task fuer BPMS (Build Process Messaging System). Dieser ANT-Task kann in
 * ein build.xml eingebunden werden und versendet dann Messages an interessierte
 * Benutzer.
 * @author Roland Gisler
 * @version $Revision: 281 $ ($Date: 2009-07-20 11:43:36 +0200 (Mo, 20 Jul 2009)
 *          $ / $Author: rog $)
 */
public abstract class AbstractBpmsTask extends Task implements BuildListener {

   /** Konstante fuer Fehler. */
   public static final String MSG_MISSING_PARA = "BPMS-Task is missing one or more parameters: ";

   /** Erzeugt eine MessageFactory fuer ANT-Messages. */
   private MessageFactory messageFactory = new MessageFactory(MessageType.ANT);

   /** Default fuer Gruppe. */
   private String group = "bpms";

   /** AutoNotify default nicht aktiviert. */
   private boolean autonotify = false;

   private String lastTarget = "";

   /**
    * Check the input and throw a BuildException when it is null.
    * @throws BuildException
    *            Buildfehler
    * @throws IllegalArgumentException
    *            Fehlendes Attribut
    */
   public abstract void checkAttributes() throws BuildException;

   /**
    * Execute-Methode des Ant-Tasks. Diese Methode prueft ob alle Argemente
    * vorhanden sind und sendet dann eine BPMS-Message.
    * @throws BuildException
    *            on wrong argument or IO error
    */
   public void execute() throws BuildException {

      // Parameter pruefen
      this.checkAttributes();

      // Initialisierung der Kommunikation
      this.initialize();

      // Gruppe setzen
      this.messageFactory.setGroup(this.getGroup());

      // AutoNotify aktivieren wenn gewuenscht
      if (this.autonotify) {
         this.getProject().addBuildListener(this);
      }
   }

   /**
    * @see org.apache.tools.ant.BuildListener#buildStarted(org.apache.tools.ant.BuildEvent)
    *      .
    */
   public void buildStarted(BuildEvent arg0) {
      Message msg = this.messageFactory.createMessage();
      msg.setProject(arg0.getProject().getName());
      msg.setTask("start");
      msg.setText("Build gestartet");
      this.sendMessage(msg);
   }

   /**
    * @see org.apache.tools.ant.BuildListener#buildFinished(org.apache.tools.ant.BuildEvent)
    *      .
    */
   public void buildFinished(BuildEvent arg0) {
      String result = "BUILD ";
      if (arg0.getException() == null) {
         result += "SUCCESSFUL";
      } else {
         result += "FAILED";
      }
      Message msg = this.messageFactory.createMessage();
      msg.setProject(arg0.getProject().getName());
      msg.setTask("finished");
      msg.setText(result);
      this.sendMessage(msg);
   }

   /**
    * @see org.apache.tools.ant.BuildListener#targetStarted(org.apache.tools.ant.BuildEvent)
    *      .
    */
   public void targetStarted(BuildEvent arg0) {
      this.lastTarget = arg0.getTarget().getName();
      this.sendMessage(prepareMessage(arg0.getProject().getName(), "Target wird bearbeitet..."));
   }

   /**
    * @see org.apache.tools.ant.BuildListener#targetFinished(org.apache.tools.ant.BuildEvent)
    *      .
    */
   public void targetFinished(BuildEvent arg0) {
      if (!this.lastTarget.equals(arg0.getTarget().getName())) {
         this.lastTarget = arg0.getTarget().getName();
         this.sendMessage(prepareMessage(arg0.getProject().getName(), "Target wurde bearbeitet."));
      }
   }

   /**
    * Hilfsfunktion, erzeugt eine Message.
    * @param project
    *           Name des Projektes.
    * @param text
    *           Messagetext.
    * @return Message Message.
    */
   private Message prepareMessage(String project, String text) {
      Message msg = this.messageFactory.createMessage();
      msg.setProject(project);
      msg.setTask(this.lastTarget);
      msg.setText(text);
      return msg;
   }

   /**
    * @see org.apache.tools.ant.BuildListener#taskStarted(org.apache.tools.ant.BuildEvent)
    *      .
    */
   public void taskStarted(BuildEvent arg0) {
   }

   /**
    * @see org.apache.tools.ant.BuildListener#taskFinished(org.apache.tools.ant.BuildEvent)
    */
   public void taskFinished(final BuildEvent arg0) {
   }

   /**
    * @see org.apache.tools.ant.BuildListener#messageLogged(org.apache.tools.ant.BuildEvent)
    */
   public void messageLogged(final BuildEvent arg0) {
   }

   /**
    * Versendet eine Message.
    * @param msg
    *           Message.
    */
   public abstract void sendMessage(Message msg);

   /**
    * Initialisiert das Message-System.
    */
   public abstract void initialize();

   /**
    * Liefert AutoNotify zurueck.
    * @return boolean
    */
   public boolean isAutonotify() {
      return autonotify;
   }

   /**
    * Setzt das AutoNotify.
    * @param autonot
    *           boolean
    */
   public void setAutonotify(boolean autonot) {
      this.autonotify = autonot;
   }

   /**
    * Liefert die Gruppe.
    * @return String Gruppe.
    */
   public String getGroup() {
      return group;
   }

   /**
    * Setzt die Gruppe.
    * @param group
    *           String.
    */
   public void setGroup(String group) {
      this.group = group;
   }

   /**
    * Liefert die MessageFactory.
    * @return MessageFactory.
    */
   public MessageFactory getMessageFactory() {
      return messageFactory;
   }
}

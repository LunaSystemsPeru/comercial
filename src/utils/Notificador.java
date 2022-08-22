/*
 * Copyright 2022 luisog.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

/**
 *
 * @author luisog
 */
public class Notificador {

    public Notificador() {
    }

    public void displayTray(String titulo, String texto) throws AWTException, MalformedURLException {
        // Obtener solamente una instancia del objeto SystemTray
        SystemTray tray = SystemTray.getSystemTray();

        // Si quieres crear un icono en la bandeja del sistemas como vista previa
        Image image = Toolkit.getDefaultToolkit().createImage("accept.png");
        // Alternativamente (si el icono está en el directorio de la clase):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
        // Deja que el sistema auto escale si es necesario
        trayIcon.setImageAutoSize(true);
        // Definir texto de tooltip (descripción emergente)
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(titulo, texto, MessageType.INFO);
    }
}

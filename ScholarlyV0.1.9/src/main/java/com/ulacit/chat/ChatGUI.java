/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ulacit.chat;

import javax.swing.*;
/**
 *
 * @author sebas
 */
public class ChatGUI {

    private final AlmacenMensajes almacenmensajes = new AlmacenMensajes();

    public ChatGUI() {
        NewJFrame ventanaChat = new NewJFrame();
        ventanaChat.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new ChatGUI());
    }
}



package com.ulacit.app;

import com.ulacit.login.LoginApp;

import javax.swing.*;


public class Main extends JFrame {

    public Main() {
 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginApp().setVisible(true);
        });
    }

}

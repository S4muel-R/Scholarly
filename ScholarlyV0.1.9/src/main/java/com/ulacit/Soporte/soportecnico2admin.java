/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.Soporte;

/**
 *
 * @author penge
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class soportecnico2admin {

    private static final String ARCHIVO = "C:/soporte/chat_soporte.txt";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Soporte Tecnico-Estudiante");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JTextField inputField = new JTextField();
        JButton responderBtn = new JButton("Enviar");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(responderBtn, BorderLayout.EAST);
        frame.add(panel, BorderLayout.SOUTH);

        responderBtn.addActionListener(e -> enviarMensaje("Soporte", inputField, chatArea));

        Timer timer = new Timer(2000, e -> actualizarChat(chatArea));
        timer.start();

        frame.setVisible(true);
    }

    private static void enviarMensaje(String remitente, JTextField campo, JTextArea area) {
        String mensaje = campo.getText().trim();
        if (!mensaje.isEmpty()) {
            try {
                File carpeta = new File("C:/soporte");
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                FileWriter writer = new FileWriter(ARCHIVO, true);
                writer.write(remitente + ": " + mensaje + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            campo.setText("");
        }
    }

    private static void actualizarChat(JTextArea area) {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        StringBuilder nuevoContenido = new StringBuilder();
        StringBuilder textoVisible = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Estudiante:") && linea.endsWith("[No leido]")) {
                    String lineaLeida = linea.replace("[No leido]", "[Leido]");
                    nuevoContenido.append(lineaLeida).append("\n");
                    textoVisible.append(lineaLeida).append("\n");
                } else {
                    nuevoContenido.append(linea).append("\n");
                    textoVisible.append(linea).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(nuevoContenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        area.setText(textoVisible.toString());
    }
}

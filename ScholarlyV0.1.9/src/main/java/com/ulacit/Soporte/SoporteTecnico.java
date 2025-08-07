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

public class SoporteTecnico {

    private static final String ARCHIVO = "C:/soporte/chat_soporte.txt"; //


    public static void main(String[] args) {
        
        
        JFrame frame = new JFrame("Estudiante-Soporte Técnico");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ← Mejor que EXIT_ON_CLOSE
        

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JTextField inputField = new JTextField();
        JButton enviarBtn = new JButton("Enviar");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(enviarBtn, BorderLayout.EAST);
        frame.add(panel, BorderLayout.SOUTH);

        enviarBtn.addActionListener(e -> enviarMensaje("Estudiante", inputField, chatArea));

        Timer timer = new Timer(2000, e -> actualizarChat(chatArea));
        timer.start();

        frame.setVisible(true);
    }

     private static void enviarMensaje(String remitente, JTextField campo, JTextArea area) {
    String mensaje = campo.getText().trim();

    if (mensaje.isEmpty()) {
        JOptionPane.showMessageDialog(null, "El mensaje no puede estar vacío.");
        return;
    }

    if (mensaje.length() > 255) {
        JOptionPane.showMessageDialog(null, "El mensaje no puede superar los 255 caracteres.");
        return;
    }

    try {
        // Crear carpeta si no existe
        File carpeta = new File("C:/soporte");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Abrir archivo y escribir mensaje
        FileWriter writer = new FileWriter("C:/soporte/chat_soporte.txt", true);

        // Agregar estado NO LEÍDO si el remitente es Estudiante
        if (remitente.equals("Estudiante")) {
            writer.write(remitente + ": " + mensaje + " [NO LEÍDO]\n");
        } else {
            writer.write(remitente + ": " + mensaje + "\n");
        }

        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Limpiar campo de texto
    campo.setText("");
}
    private static void actualizarChat(JTextArea area) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            area.setText("");
            String linea;
            while ((linea = reader.readLine()) != null) {
                area.append(linea + "\n");
            }
        } catch (IOException e) {
           
        }}}


    
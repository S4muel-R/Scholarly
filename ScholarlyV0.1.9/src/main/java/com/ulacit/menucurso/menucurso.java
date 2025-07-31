/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.menucurso;

/**
 *
 * @author penge
 */
import com.ulacit.gestiondegrupos.ModuloGestiondeGrupos;
import com.ulacit.foros.Foro;
import com.ulacit.tareas.Asignacion_Tareas;

import com.ulacit.asistenciav2.AsistenciaManager;
import com.ulacit.asistenciav2.Profesor;
import com.ulacit.asistenciav2.Estudiante;
import com.ulacit.asistenciav2.Curso;

import com.ulacit.calificaciones.VentanaPrincipal;

import java.awt.*;
import javax.swing.*;
import java.util.List;

public class menucurso extends JFrame {

    private JFrame ventanaAnterior;
    private static menucurso instance;

    public menucurso(JFrame ventanaAnterior) {
        this.ventanaAnterior = ventanaAnterior;

        setTitle("MENÚ DEL CURSO");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(81, 0, 87));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        String[] botones = {"Tareas","Grupos", "Foros", "Asistencia", "Calificaciones", "Volver"};

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            boton.setBackground(Color.WHITE);
            boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));

            boton.addActionListener(e -> manejarClick(texto));
            mainPanel.add(Box.createVerticalStrut(20));
            mainPanel.add(boton);
        }

        add(mainPanel, BorderLayout.CENTER);
    }

    public static menucurso getInstance(JFrame ventanaAnterior) {
        if (instance == null || !instance.isDisplayable()) {
            instance = new menucurso(ventanaAnterior);
        }
        return instance;
    }


    private void manejarClick(String opcion) {
        switch (opcion) {
            
            case "Tareas":
                new Asignacion_Tareas().setVisible(true);
                break;
                
            case "Grupos":
                new ModuloGestiondeGrupos().setVisible(true);
                break;

            case "Foros":
                new Foro().setVisible(true);
                break;

            case "Asistencia":
                Profesor prof = new Profesor("Profe Juan");

                Estudiante adrian = new Estudiante("Adrian", "001");
                Estudiante maria = new Estudiante("Maria", "002");
                Curso cursoA = new Curso("Matemática");
                cursoA.agregarEstudiante(adrian);
                cursoA.agregarEstudiante(maria);

                Estudiante laura = new Estudiante("Laura", "003");
                Curso cursoB = new Curso("Historia");
                cursoB.agregarEstudiante(laura);

                List<Curso> cursos = List.of(cursoA, cursoB);
                List<String> semanas = List.of("Semana 1", "Semana 2", "Semana 3");

                AsistenciaManager.mostrarAsistenciaProfesor(prof, cursos, semanas);
                break;

            case "Calificaciones":
                new VentanaPrincipal().setVisible(true);
                break;

            case "Volver":
                this.dispose();
                if (ventanaAnterior != null) ventanaAnterior.setVisible(true);
                break;
        }
    }
}

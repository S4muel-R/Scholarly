
package com.ulacit.calendario;

import com.ulacit.dashboard.moddashboardclass;
import com.ulacit.login.LoginApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class calendario {

    
    static class Evento {
        int dia, mes, anio;
        String nombre, hora, descripcion;
        Evento(int dia, int mes, int anio, String nombre, String hora, String descripcion) {
            this.dia = dia; this.mes = mes; this.anio = anio;
            this.nombre = nombre; this.hora = hora; this.descripcion = descripcion;
        }
    }
    static java.util.List<Evento> eventos = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(calendario::showCalendarioUI);
    }

    public static void showCalendarioUI() {
        // --- Variables principales ---
        JFrame frame = new JFrame("Calendario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 760);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        Color azulFondo = new Color(187, 222, 251);
        String[] nombresMes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Calendar cal = Calendar.getInstance();
        int[] mesAnio = {cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)};
        final int[] diaSeleccionado = {-1};

        // --- Panel principal ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(azulFondo);
        frame.add(mainPanel, BorderLayout.CENTER);

        // --- Panel superior (mes, navegación) ---
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(azulFondo);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(16, 32, 0, 32));

        JButton btnAnterior = new JButton();
        btnAnterior.setPreferredSize(new Dimension(160, 2));
        panelSuperior.add(btnAnterior, BorderLayout.WEST);

        JLabel labelMesAnio = new JLabel("", SwingConstants.CENTER);
        labelMesAnio.setFont(new Font("Arial", Font.BOLD, 32));
        labelMesAnio.setForeground(new Color(41, 99, 156));
        panelSuperior.add(labelMesAnio, BorderLayout.CENTER);

        JButton btnSiguiente = new JButton();
        btnSiguiente.setPreferredSize(new Dimension(160, 2));
        panelSuperior.add(btnSiguiente, BorderLayout.EAST);

        mainPanel.add(panelSuperior);

        // --- Panel central (calendario + eventos) ---
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.X_AXIS));
        panelCentral.setBackground(azulFondo);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(16, 32, 0, 32));

        // Calendario con borde y fondo blanco
        JPanel calendarioWrapper = new JPanel(new BorderLayout());
        calendarioWrapper.setBackground(azulFondo);
        calendarioWrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(41, 99, 156), 3, true),
            BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        JPanel panelCalendario = new JPanel(new GridLayout(7, 7, 2, 2));
        panelCalendario.setBackground(Color.WHITE);
        // Fijar tamaño preferido y mínimo del calendario
        Dimension calDim = new Dimension(340, 340);
        calendarioWrapper.setPreferredSize(calDim);
        calendarioWrapper.setMinimumSize(calDim);
        panelCalendario.setPreferredSize(new Dimension(300, 300));
        panelCalendario.setMinimumSize(new Dimension(300, 300));
        calendarioWrapper.add(panelCalendario, BorderLayout.CENTER);
        panelCentral.add(calendarioWrapper);

        panelCentral.add(Box.createHorizontalStrut(24));

        // Panel de eventos del día con borde y fondo blanco
        JPanel eventosWrapper = new JPanel(new BorderLayout());
        eventosWrapper.setBackground(azulFondo);
        eventosWrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(41, 99, 156), 3, true),
            BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        JPanel panelEventosDia = new JPanel();
        panelEventosDia.setLayout(new BoxLayout(panelEventosDia, BoxLayout.Y_AXIS));
        panelEventosDia.setBackground(Color.WHITE);
        // Fijar tamaño preferido y mínimo del panel de eventos
        Dimension eventosDim = new Dimension(440, 340);
        eventosWrapper.setPreferredSize(eventosDim);
        eventosWrapper.setMinimumSize(eventosDim);
        panelEventosDia.setPreferredSize(new Dimension(400, 300));
        panelEventosDia.setMinimumSize(new Dimension(400, 300));
        JScrollPane scrollEventos = new JScrollPane(panelEventosDia);
        scrollEventos.setBorder(null);
        scrollEventos.getViewport().setBackground(Color.WHITE);
        scrollEventos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEventos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        eventosWrapper.add(scrollEventos, BorderLayout.CENTER);
        panelCentral.add(eventosWrapper);

        mainPanel.add(panelCentral);

        // --- Panel de botones inferior ---
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(16, 32, 16, 32));
        panelBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

        JPanel panelBotonesCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 32, 0));
        panelBotonesCentro.setOpaque(false);
        JButton btnCrear = new JButton("Crear Evento");
        JButton btnModificar = new JButton("Modificar Evento");
        JButton btnVolver = new JButton("Volver");
        JButton btnSalir = new JButton("Cerrar sesión");
        Dimension btnDim = new Dimension(180, 40);
        btnCrear.setPreferredSize(btnDim);
        btnModificar.setPreferredSize(btnDim);
        btnVolver.setPreferredSize(btnDim);
        btnSalir.setPreferredSize(btnDim);
        panelBotonesCentro.add(btnCrear);
        panelBotonesCentro.add(btnModificar);
        panelBotonesCentro.add(btnVolver);
        panelBotonesCentro.add(btnSalir);
        panelBotones.add(panelBotonesCentro, BorderLayout.CENTER);
        mainPanel.add(panelBotones);

        // --- Acción Volver: cerrar ventana y abrir menú principal ---
        btnVolver.addActionListener(e -> {
            frame.dispose(); // Cierra la ventana actual
            
        });



        // --- Mostrar eventos del día ---
        Runnable mostrarInfoEvento = () -> {
            panelEventosDia.removeAll();
            int dSel = diaSeleccionado[0], mSel = mesAnio[0], aSel = mesAnio[1];
            java.util.List<Evento> eventosDelDia = new ArrayList<>();
            for (Evento ev : eventos) {
                if (ev.dia == dSel && ev.mes == mSel && ev.anio == aSel) {
                    eventosDelDia.add(ev);
                }
            }
            if (!eventosDelDia.isEmpty()) {
                for (Evento evento : eventosDelDia) {
                    JPanel eventoPanel = new JPanel(new GridBagLayout());
                    eventoPanel.setBackground(Color.WHITE);
                    eventoPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(144, 202, 249), 2, true),
                        BorderFactory.createEmptyBorder(16, 20, 16, 20)));
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(2, 8, 2, 8);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridy = 0;
                    gbc.gridx = 0;
                    gbc.weightx = 0.45;
                    gbc.weighty = 1.0;
                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                    infoPanel.setOpaque(false);
                    JLabel labelNombre = new JLabel(evento.nombre);
                    labelNombre.setFont(new Font("Arial", Font.BOLD, 20));
                    labelNombre.setForeground(new Color(41, 99, 156));
                    JLabel labelFecha = new JLabel("Fecha: " + String.format("%02d/%02d/%04d", evento.dia, evento.mes+1, evento.anio));
                    labelFecha.setFont(new Font("Arial", Font.PLAIN, 16));
                    labelFecha.setForeground(new Color(41, 99, 156));
                    JLabel labelHora = new JLabel("Hora: " + evento.hora);
                    labelHora.setFont(new Font("Arial", Font.PLAIN, 16));
                    labelHora.setForeground(new Color(41, 99, 156));
                    infoPanel.add(labelNombre);
                    infoPanel.add(labelFecha);
                    infoPanel.add(labelHora);
                    eventoPanel.add(infoPanel, gbc);
                    // Divisor visual
                    gbc.gridx = 1;
                    gbc.weightx = 0.02;
                    JPanel divisor = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            g.setColor(new Color(144, 202, 249));
                            int w = getWidth();
                            int h = getHeight();
                            g.fillRect(w/2 - 1, 0, 2, h);
                        }
                    };
                    divisor.setOpaque(false);
                    eventoPanel.add(divisor, gbc);
                    // Descripción a la derecha
                    gbc.gridx = 2;
                    gbc.weightx = 0.53;
                    JPanel descPanel = new JPanel(new BorderLayout());
                    descPanel.setOpaque(false);
                    JLabel labelDesc = new JLabel("Descripción:");
                    labelDesc.setFont(new Font("Arial", Font.PLAIN, 16));
                    labelDesc.setForeground(new Color(41, 99, 156));
                    JTextArea areaDesc = new JTextArea(evento.descripcion, 2, 24);
                    areaDesc.setFont(new Font("Arial", Font.PLAIN, 16));
                    areaDesc.setForeground(new Color(25, 118, 210));
                    areaDesc.setLineWrap(true);
                    areaDesc.setWrapStyleWord(true);
                    areaDesc.setEditable(false);
                    areaDesc.setOpaque(false);
                    descPanel.add(labelDesc, BorderLayout.NORTH);
                    descPanel.add(areaDesc, BorderLayout.CENTER);
                    eventoPanel.add(descPanel, gbc);
                    panelEventosDia.add(eventoPanel);
                    panelEventosDia.add(Box.createVerticalStrut(12));
                }
            } else if (dSel > 0) {
                JLabel label = new JLabel("No hay eventos para este día.");
                label.setFont(new Font("Arial", Font.BOLD, 20));
                label.setForeground(new Color(41, 99, 156));
                panelEventosDia.add(label);
            }
            panelEventosDia.revalidate();
            panelEventosDia.repaint();
        };

        // --- Crear evento ---
        btnCrear.addActionListener(e -> crearEventoDialog(frame, mesAnio, diaSeleccionado, mostrarInfoEvento));

        // --- Modificar evento ---
        btnModificar.addActionListener(e -> modificarEventoDialog(frame, mesAnio, diaSeleccionado, mostrarInfoEvento));

        // --- Eliminar evento ---
        // (Eliminado, ahora solo desde modificar)

        // --- Cerrar sesión ---
        btnSalir.addActionListener(e -> {
            for (Window window : Window.getWindows()) {
                window.dispose();
               }
            new LoginApp().setVisible(true); // Abre una nueva instancia del login
        });


        // --- Actualizar calendario ---
        final Runnable[] actualizarCalendario = new Runnable[1];
        actualizarCalendario[0] = () -> {
            panelCalendario.removeAll();
            String[] dias = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
            for (String dia : dias) {
                JLabel l = new JLabel(dia, SwingConstants.CENTER);
                l.setFont(new Font("Arial", Font.BOLD, 16));
                l.setForeground(Color.BLACK);
                panelCalendario.add(l);
            }
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, mesAnio[0]); c.set(Calendar.YEAR, mesAnio[1]); c.set(Calendar.DAY_OF_MONTH, 1);
            int primerDiaSemana = c.get(Calendar.DAY_OF_WEEK) - 1;
            int diasEnMes = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            labelMesAnio.setText(nombresMes[mesAnio[0]].toUpperCase() + " " + mesAnio[1]);
            int mesAnt = (mesAnio[0] - 1 + 12) % 12, mesSig = (mesAnio[0] + 1) % 12;
            btnAnterior.setText("< " + nombresMes[mesAnt]);
            btnSiguiente.setText(nombresMes[mesSig] + " >");
            for (int i = 0; i < primerDiaSemana; i++) panelCalendario.add(new JLabel(""));
            for (int dia = 1; dia <= diasEnMes; dia++) {
                JPanel diaPanel = new JPanel(new BorderLayout()) {
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        int d = Integer.parseInt(((JLabel)getComponent(0)).getText());
                        int m = mesAnio[0], y = mesAnio[1];
                        for (Evento ev : eventos) if (ev.dia == d && ev.mes == m && ev.anio == y) {
                            g.setColor(Color.RED);
                            int w = getWidth(), h = getHeight();
                            g.fillOval(w/2 - 3, h - 13, 7, 7); break;
                        }
                    }
                };
                diaPanel.setOpaque(true);
                JLabel l = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
                l.setFont(new Font("Arial", Font.PLAIN, 18));
                l.setForeground(Color.BLACK);
                diaPanel.add(l, BorderLayout.CENTER);
                Color azulSeleccion = new Color(144, 202, 249);
                diaPanel.setBackground(dia == diaSeleccionado[0] ? azulSeleccion : Color.WHITE);
                final int diaActual = dia;
                diaPanel.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,0), 2));
                diaPanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        diaSeleccionado[0] = diaActual;
                        actualizarCalendario[0].run();
                        mostrarInfoEvento.run();
                    }
                });
                panelCalendario.add(diaPanel);
            }
            int totalCeldas = 7 * 6, usados = primerDiaSemana + diasEnMes;
            for (int i = usados; i < totalCeldas; i++) panelCalendario.add(new JLabel(""));
            panelCalendario.revalidate(); panelCalendario.repaint();
        };

        btnAnterior.addActionListener(e -> {
            if (mesAnio[0] == 0) { mesAnio[0] = 11; mesAnio[1]--; } else { mesAnio[0]--; }
            diaSeleccionado[0] = -1; actualizarCalendario[0].run();
        });
        btnSiguiente.addActionListener(e -> {
            if (mesAnio[0] == 11) { mesAnio[0] = 0; mesAnio[1]++; } else { mesAnio[0]++; }
            diaSeleccionado[0] = -1; actualizarCalendario[0].run();
        });

        // Inicializar selección y mostrar
        Calendar calInit = Calendar.getInstance();
        calInit.set(Calendar.MONTH, mesAnio[0]); calInit.set(Calendar.YEAR, mesAnio[1]);
        int diasEnMesInit = calInit.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (diaSeleccionado[0] < 1 && diasEnMesInit > 0) diaSeleccionado[0] = 1;
        actualizarCalendario[0].run();
        mostrarInfoEvento.run();
        frame.setVisible(true);
    }

    // --- Crear Evento Dialog ---
    private static void crearEventoDialog(JFrame frame, int[] mesAnio, int[] diaSeleccionado, Runnable mostrarInfoEvento) {
        if (diaSeleccionado[0] < 1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un día para crear el evento.", "Sin día seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JDialog dialog = new JDialog(frame, "Crear Evento", true);
        dialog.setSize(400, 340);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(187, 222, 251));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        JTextField fieldTitulo = new JTextField(22);
        dialog.add(fieldTitulo, gbc);

        // Hora
        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Hora (HH:mm):"), gbc);
        gbc.gridx = 1;
        JTextField fieldHora = new JTextField(8);
        dialog.add(fieldHora, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        dialog.add(new JLabel("Descripción:"), gbc);
        gbc.gridy = 3;
        JTextArea areaDesc = new JTextArea(4, 22);
        areaDesc.setLineWrap(true);
        areaDesc.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(areaDesc);
        dialog.add(scrollDesc, gbc);

        // Botones
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBtns.add(btnGuardar);
        panelBtns.add(btnCancelar);
        dialog.add(panelBtns, gbc);

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnGuardar.addActionListener(e -> {
            String titulo = fieldTitulo.getText().trim();
            String hora = fieldHora.getText().trim();
            String desc = areaDesc.getText().trim();
            int dia = diaSeleccionado[0], mes = mesAnio[0], anio = mesAnio[1];
            // Validación básica
            if (titulo.isEmpty() || hora.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Título y hora son obligatorios.", "Campos requeridos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!hora.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                JOptionPane.showMessageDialog(dialog, "Hora inválida. Use formato HH:mm.", "Hora inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Validar duplicidad de hora en el mismo día
            for (Evento ev : eventos) {
                if (ev.dia == dia && ev.mes == mes && ev.anio == anio && ev.hora.equals(hora)) {
                    JOptionPane.showMessageDialog(dialog, "Ya existe un evento a esa hora para este día.", "Hora duplicada", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            eventos.add(new Evento(dia, mes, anio, titulo, hora, desc));
            dialog.dispose();
            mostrarInfoEvento.run();
            JOptionPane.showMessageDialog(frame, "Evento creado exitosamente.");
        });

        dialog.setVisible(true);
    }

    // --- Modificar Evento Dialog ---
    private static void modificarEventoDialog(JFrame frame, int[] mesAnio, int[] diaSeleccionado, Runnable mostrarInfoEvento) {
        int dSel = diaSeleccionado[0], mSel = mesAnio[0], aSel = mesAnio[1];
        // Buscar todos los eventos del día seleccionado
        java.util.List<Evento> eventosDelDia = new ArrayList<>();
        for (Evento ev : eventos) {
            if (ev.dia == dSel && ev.mes == mSel && ev.anio == aSel) {
                eventosDelDia.add(ev);
            }
        }
        if (eventosDelDia.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Seleccione un día con evento para modificar.", "Sin evento", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Si hay más de un evento, permitir seleccionar cuál modificar
        Evento[] eventoSel = new Evento[1];
        if (eventosDelDia.size() == 1) {
            eventoSel[0] = eventosDelDia.get(0);
        } else {
            String[] opciones = new String[eventosDelDia.size()];
            for (int i = 0; i < eventosDelDia.size(); i++) {
                Evento ev = eventosDelDia.get(i);
                opciones[i] = String.format("%s (%s)", ev.nombre, ev.hora);
            }
            int idx = JOptionPane.showOptionDialog(frame, "Seleccione el evento a modificar:", "Seleccionar Evento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (idx < 0) return;
            eventoSel[0] = eventosDelDia.get(idx);
        }
        JDialog dialog = new JDialog(frame, "Modificar Evento", true);
        dialog.setSize(400, 340);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(new Color(187, 222, 251));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        JTextField fieldTitulo = new JTextField(eventoSel[0].nombre, 22);
        dialog.add(fieldTitulo, gbc);

        // Hora (solo lectura)
        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Hora (HH:mm):"), gbc);
        gbc.gridx = 1;
        JTextField fieldHora = new JTextField(eventoSel[0].hora, 8);
        fieldHora.setEditable(false);
        dialog.add(fieldHora, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        dialog.add(new JLabel("Descripción:"), gbc);
        gbc.gridy = 3;
        JTextArea areaDesc = new JTextArea(eventoSel[0].descripcion, 4, 22);
        areaDesc.setLineWrap(true);
        areaDesc.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(areaDesc);
        dialog.add(scrollDesc, gbc);

        // Botones
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBtns.add(btnGuardar);
        panelBtns.add(btnEliminar);
        panelBtns.add(btnCancelar);
        dialog.add(panelBtns, gbc);

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnGuardar.addActionListener(e -> {
            String titulo = fieldTitulo.getText().trim();
            String desc = areaDesc.getText().trim();
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "El título es obligatorio.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Si el título y descripción no cambiaron, no hacer nada
            if (titulo.equals(eventoSel[0].nombre) && desc.equals(eventoSel[0].descripcion)) {
                dialog.dispose();
                return;
            }
            eventoSel[0].nombre = titulo;
            eventoSel[0].descripcion = desc;
            dialog.dispose();
            mostrarInfoEvento.run();
            JOptionPane.showMessageDialog(frame, "Evento modificado exitosamente.");
        });

        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(dialog, "¿Está seguro que desea eliminar el evento?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                eventos.remove(eventoSel[0]);
                dialog.dispose();
                mostrarInfoEvento.run();
                JOptionPane.showMessageDialog(frame, "Evento eliminado exitosamente.");
            }
        });

        dialog.setVisible(true);
    }

    // --- Eliminar Evento Dialog ---
    private static void eliminarEventoDialog(JFrame frame, int[] mesAnio, int[] diaSeleccionado, Runnable mostrarInfoEvento) {
        int dSel = diaSeleccionado[0], mSel = mesAnio[0], aSel = mesAnio[1];
        Evento eventoSel = null;
        for (Evento ev : eventos) {
            if (ev.dia == dSel && ev.mes == mSel && ev.anio == aSel) {
                eventoSel = ev;
                break;
            }
        }
        if (eventoSel == null) {
            JOptionPane.showMessageDialog(frame, "Seleccione un día con evento para eliminar.", "Sin evento", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea eliminar el evento?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            eventos.remove(eventoSel);
            mostrarInfoEvento.run();
            JOptionPane.showMessageDialog(frame, "Evento eliminado exitosamente.");
        }
    }
}
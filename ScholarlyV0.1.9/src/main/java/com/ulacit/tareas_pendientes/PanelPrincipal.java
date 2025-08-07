package moodulo.tareas_pendientes;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelPrincipal extends JFrame {
    private Estudiante estudiante;
    private Curso cursoA;
    private Curso cursoB;
    private JPanel panelContenido;

    public PanelPrincipal(Estudiante estudiante, Curso cursoA, Curso cursoB) {
        this.estudiante = estudiante;
        this.cursoA = cursoA;
        this.cursoB = cursoB;

        setTitle("Organizador de Tareas por Curso");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarUI();
    }

    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Panel de botones de curso
        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton btnCursoA = new JButton("Matematica");
        JButton btnCursoB = new JButton("Historia");

        btnCursoA.addActionListener((ActionEvent e) -> mostrarContenidoCurso(cursoA));
        btnCursoB.addActionListener((ActionEvent e) -> mostrarContenidoCurso(cursoB));

        panelBotones.add(btnCursoA);
        panelBotones.add(btnCursoB);
        add(panelBotones, BorderLayout.NORTH);

        // Panel dinámico central
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(new EmptyBorder(20, 30, 20, 30));
        panelContenido.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll, BorderLayout.CENTER);

        // Mostrar Curso A por defecto
        mostrarContenidoCurso(cursoA);
    }

    private void mostrarContenidoCurso(Curso curso) {
        panelContenido.removeAll();

        JLabel titulo = new JLabel("Curso: " + curso.getNombre());
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(titulo);

        panelContenido.add(Box.createVerticalStrut(15));

        if (!estudiante.estaInscritoEn(curso)) {
            JLabel lblNoInscrito = new JLabel("No hay tareas por hacer en este curso.");
            lblNoInscrito.setForeground(Color.RED);
            lblNoInscrito.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenido.add(lblNoInscrito);
        } else {
            List<Tarea> pendientes = GestorTareas.obtenerTareasPendientes(curso, estudiante);

            if (pendientes.isEmpty()) {
                JLabel lblSinTareas = new JLabel("✅ ¡No tienes tareas pendientes activas!");
                lblSinTareas.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelContenido.add(lblSinTareas);
            } else {
                JLabel lblContador = new JLabel("📌 Tareas pendientes: " + pendientes.size());
                lblContador.setFont(new Font("Arial", Font.PLAIN, 16));
                lblContador.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelContenido.add(lblContador);

                panelContenido.add(Box.createVerticalStrut(15));

                JPanel listaPanel = new JPanel();
                listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
                listaPanel.setBackground(Color.WHITE);
                listaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                for (Tarea t : pendientes) {
                    JLabel tareaLabel = new JLabel("• " + t.getNombre() + "  (Entrega: " + t.getFechaLimite() + ")");
                    tareaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    tareaLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
                    listaPanel.add(tareaLabel);
                }

                panelContenido.add(listaPanel);
            }
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }
}

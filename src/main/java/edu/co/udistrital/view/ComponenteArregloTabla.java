/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */
import edu.co.udistrital.model.PasoBusqueda;
import edu.co.udistrital.model.ResultadoBusqueda;
import edu.co.udistrital.model.Nodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ComponenteArregloTabla extends JPanel {

    private JSpinner spinnerTamano;
    private JButton btnCrear;
    private JTextField campoNuevaClave;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private int posicionResaltada = -1;
    private Color colorResaltado = null;

    public ComponenteArregloTabla() {
        setLayout(new BorderLayout(0, 10));

        // Fila superior: crear arreglo de tamaño N
        JPanel panelCrear = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCrear.add(new JLabel("Tamaño del arreglo (N):"));
        spinnerTamano = new JSpinner(new SpinnerNumberModel(5, 1, 50, 1));
        panelCrear.add(spinnerTamano);
        btnCrear = new JButton("Crear arreglo");
        panelCrear.add(btnCrear);

        // Fila de carga de datos: agregar una clave a la vez
        JPanel panelCargar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCargar.add(new JLabel("Nueva clave:"));
        campoNuevaClave = new JTextField(6);
        panelCargar.add(campoNuevaClave);
        btnAgregar = new JButton("Agregar");
        panelCargar.add(btnAgregar);
        btnEliminar = new JButton("Eliminar seleccionado");
        panelCargar.add(btnEliminar);
        btnLimpiar = new JButton("Limpiar tabla");
        panelCargar.add(btnLimpiar);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.add(panelCrear);
        panelSuperior.add(panelCargar);
        add(panelSuperior, BorderLayout.NORTH);

        // Tabla ya NO editable directamente: solo refleja lo que el controller ordena pintar
        modeloTabla = new DefaultTableModel(new Object[]{"Índice", "Clave"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setDefaultRenderer(Object.class, new ResaltadorCeldas());
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    // === Getters de controles para que el subpanel conecte los listeners ===
    public JButton getBotonCrear() { return btnCrear; }
    public JButton getBotonAgregar() { return btnAgregar; }
    public JButton getBotonEliminar() { return btnEliminar; }
    public JButton getBotonLimpiar() { return btnLimpiar; }
    public JTextField getCampoNuevaClave() { return campoNuevaClave; }
    public int getTamanoSeleccionado() { return (int) spinnerTamano.getValue(); }

    // Índice de la fila seleccionada en la tabla, o -1 si no hay selección
    public int getFilaSeleccionada() { return tabla.getSelectedRow(); }

    public void mostrarArregloVacio(int n) {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < n; i++) modeloTabla.addRow(new Object[]{i, ""});
        limpiarResaltado();
    }

    // Pinta una sola celda (usado al agregar una clave)
    public void pintarClaveEnPosicion(int posicion, int clave) {
        modeloTabla.setValueAt(clave, posicion, 1);
    }

    public void limpiarCelda(int posicion) {
        modeloTabla.setValueAt("", posicion, 1);
    }

    public void refrescarValores(Nodo[] elementos, int cantidadLlena) {
        for (int i = 0; i < elementos.length; i++) {
            modeloTabla.setValueAt(i < cantidadLlena ? elementos[i].getClave() : "", i, 1);
        }
    }

    public void limpiarResaltado() {
        posicionResaltada = -1;
        colorResaltado = null;
        tabla.repaint();
    }

    public void animarResultado(ResultadoBusqueda resultado, Runnable alTerminar) {
        Timer timer = new Timer(600, null);
        int[] indice = {0};
        timer.addActionListener(e -> {
            if (indice[0] < resultado.getPasos().size()) {
                PasoBusqueda paso = resultado.getPasos().get(indice[0]);
                posicionResaltada = paso.getPosicionRevisada();
                colorResaltado = paso.isCoincide() ? new Color(144, 238, 144) : new Color(255, 200, 120);
                tabla.repaint();
                indice[0]++;
            } else {
                timer.stop();
                if (alTerminar != null) alTerminar.run();
            }
        });
        timer.start();
    }

    private class ResaltadorCeldas extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == posicionResaltada && colorResaltado != null) {
                c.setBackground(colorResaltado);
            } else {
                c.setBackground(isSelected ? new Color(220, 230, 255) : Color.WHITE);
            }
            return c;
        }
    }
}
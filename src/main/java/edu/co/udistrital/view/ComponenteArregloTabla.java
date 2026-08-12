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
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private int posicionResaltada = -1;
    private Color colorResaltado = null;

    public ComponenteArregloTabla() {
        setLayout(new BorderLayout(0, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Tamaño del arreglo (N):"));
        spinnerTamano = new JSpinner(new SpinnerNumberModel(5, 1, 50, 1));
        panelSuperior.add(spinnerTamano);
        btnCrear = new JButton("Crear arreglo");
        panelSuperior.add(btnCrear);
        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"Índice", "Clave"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return column == 1; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setDefaultRenderer(Object.class, new ResaltadorCeldas());
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    // El controller decide CUÁNDO crear, pero la vista expone el botón para que el subpanel lo conecte
    public JButton getBotonCrear() { return btnCrear; }
    public int getTamanoSeleccionado() { return (int) spinnerTamano.getValue(); }

    public void mostrarArregloVacio(int n) {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < n; i++) modeloTabla.addRow(new Object[]{i, 0});
        limpiarResaltado();
    }

    // Entrega los valores tal cual el usuario los escribió, SIN parsear (eso es tarea del controller)
    public String[] leerValoresCrudos() {
        String[] valores = new String[modeloTabla.getRowCount()];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = modeloTabla.getValueAt(i, 1).toString();
        }
        return valores;
    }

    public void refrescarValores(Nodo[] elementos) {
        for (int i = 0; i < elementos.length; i++) {
            modeloTabla.setValueAt(elementos[i].getClave(), i, 1);
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
            c.setBackground(row == posicionResaltada && colorResaltado != null ? colorResaltado : Color.WHITE);
            return c;
        }
    }
}
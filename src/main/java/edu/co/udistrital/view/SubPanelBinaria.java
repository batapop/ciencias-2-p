/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */
import edu.co.udistrital.controller.BusquedaController;
import edu.co.udistrital.controller.BusquedaBinaria;

import javax.swing.*;
import java.awt.*;

public class SubPanelBinaria extends JPanel {

    private ComponenteArregloCuadros componenteArreglo;
    private JTextField campoClaveBuscada;
    private JLabel labelResultado;
    private BusquedaController controller;

    public SubPanelBinaria() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        componenteArreglo = new ComponenteArregloCuadros();
        add(componenteArreglo, BorderLayout.CENTER);

        controller = new BusquedaController(componenteArreglo, new BusquedaBinaria());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Clave a buscar:"));
        campoClaveBuscada = new JTextField(6);
        panelBusqueda.add(campoClaveBuscada);
        JButton btnBuscar = new JButton("Buscar (Binaria)");
        panelBusqueda.add(btnBuscar);
        labelResultado = new JLabel(" ");
        labelResultado.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelBusqueda.add(labelResultado);
        add(panelBusqueda, BorderLayout.SOUTH);

        componenteArreglo.getBotonCrear().addActionListener(e ->
                controller.crearArreglo(componenteArreglo.getTamanoSeleccionado()));

        componenteArreglo.getBotonAgregar().addActionListener(e -> agregarClave());
        componenteArreglo.getCampoNuevaClave().addActionListener(e -> agregarClave());

        componenteArreglo.getBotonEliminar().addActionListener(e ->
                controller.eliminarClave(componenteArreglo.getIndiceSeleccionado(), this));

        componenteArreglo.getBotonLimpiar().addActionListener(e -> {
            controller.limpiarArreglo();
            labelResultado.setText(" ");
        });

        btnBuscar.addActionListener(e -> {
            labelResultado.setText("Buscando...");
            controller.ejecutarBusqueda(campoClaveBuscada.getText(), this, resultado -> {
                if (resultado.isEncontrado()) {
                    labelResultado.setForeground(new Color(0, 130, 0));
                    labelResultado.setText("Encontrado en la posición " + resultado.getPosicionFinal());
                } else {
                    labelResultado.setForeground(Color.RED);
                    labelResultado.setText("No encontrado");
                }
            });
        });
    }

    private void agregarClave() {
        controller.agregarClave(componenteArreglo.getCampoNuevaClave().getText(), this);
        componenteArreglo.getCampoNuevaClave().setText("");
        componenteArreglo.getCampoNuevaClave().requestFocus();
    }
}
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
import edu.co.udistrital.controller.BusquedaSecuencial;

import javax.swing.*;
import java.awt.*;

public class SubPanelSecuencial extends JPanel {

    private ComponenteArregloTabla componenteTabla;
    private JTextField campoClaveBuscada;
    private JLabel labelResultado;
    private BusquedaController controller;

    public SubPanelSecuencial() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        componenteTabla = new ComponenteArregloTabla();
        add(componenteTabla, BorderLayout.CENTER);

        controller = new BusquedaController(componenteTabla, new BusquedaSecuencial());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Clave a buscar:"));
        campoClaveBuscada = new JTextField(6);
        panelBusqueda.add(campoClaveBuscada);
        JButton btnBuscar = new JButton("Buscar (Secuencial)");
        panelBusqueda.add(btnBuscar);
        labelResultado = new JLabel(" ");
        labelResultado.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelBusqueda.add(labelResultado);
        add(panelBusqueda, BorderLayout.SOUTH);

        componenteTabla.getBotonCrear().addActionListener(e ->
                controller.crearArreglo(componenteTabla.getTamanoSeleccionado()));

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
}
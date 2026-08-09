/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */

import javax.swing.*;
import java.awt.*;

public class PanelBinaria extends JPanel {
    public PanelBinaria() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de entrada (Norte)
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEntrada.add(new JLabel("Arreglo (separado por comas): "));
        JTextField txtArreglo = new JTextField(30);
        panelEntrada.add(txtArreglo);
        panelEntrada.add(new JButton("Ordenar y Buscar"));
        add(panelEntrada, BorderLayout.NORTH);

        // Panel de salida (Centro)
        JTextArea txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        // Panel inferior para análisis de complejidad
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(new JLabel("Complejidad: O(log n) en el peor caso"));
        add(panelInferior, BorderLayout.SOUTH);
    }
}
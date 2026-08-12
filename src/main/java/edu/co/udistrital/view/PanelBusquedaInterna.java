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

public class PanelBusquedaInterna extends JPanel implements Visualiza {

    public PanelBusquedaInterna() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("1.1 Búsquedas internas y externas", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Búsqueda Secuencial", new SubPanelSecuencial());
        /**pestañas.addTab("Búsqueda Binaria", new SubPanelBinaria());
        pestañas.addTab("Transformación de Claves", new SubPanelTransformacion());*/

        add(pestañas, BorderLayout.CENTER);
    }

    @Override
    public JPanel getPanel() { return this; }

    @Override
    public String getIdentificador() { return "busq_internas"; }
}
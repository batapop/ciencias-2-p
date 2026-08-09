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

public class PanelArbolesExpansion extends JPanel implements Visualiza {

    public PanelArbolesExpansion() {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("2.5 Árboles de expansión y caminos mínimos", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);
    }

    @Override
    public JPanel getPanel() { return this; }

    @Override
    public String getIdentificador() { return "arboles_expansion"; } // debe ser IGUAL al idPanel que usaste en ArbolTemasFactory
}
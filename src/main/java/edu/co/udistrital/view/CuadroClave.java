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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CuadroClave extends JPanel {

    private boolean seleccionado = false;
    private Color colorResaltado = null;

    public CuadroClave(int clave, Runnable alHacerClic) {
        setPreferredSize(new Dimension(60, 60));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
        setBackground(Color.WHITE);

        JLabel etiqueta = new JLabel(String.valueOf(clave));
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(etiqueta);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { alHacerClic.run(); }
        });
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
        actualizarColor();
    }

    public void setColorResaltado(Color color) {
        this.colorResaltado = color;
        actualizarColor();
    }

    private void actualizarColor() {
        if (colorResaltado != null) setBackground(colorResaltado);
        else if (seleccionado) setBackground(new Color(200, 220, 255));
        else setBackground(Color.WHITE);
    }
}
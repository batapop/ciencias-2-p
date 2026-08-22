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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComponenteArregloCuadros extends JPanel {

    private JSpinner spinnerTamano;
    private JButton btnCrear;
    private JTextField campoNuevaClave;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JLabel labelOcupacion;

    private JPanel panelCuadros;
    private List<CuadroClave> cuadros = new ArrayList<>();
    private int indiceSeleccionado = -1;

    public ComponenteArregloCuadros() {
        setLayout(new BorderLayout(0, 10));

        JPanel panelCrear = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCrear.add(new JLabel("Tamaño del arreglo (N):"));
        spinnerTamano = new JSpinner(new SpinnerNumberModel(5, 1, 50, 1));
        panelCrear.add(spinnerTamano);
        btnCrear = new JButton("Crear arreglo");
        panelCrear.add(btnCrear);
        labelOcupacion = new JLabel("0/0 posiciones llenas");
        panelCrear.add(labelOcupacion);

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

        panelCuadros = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        panelCuadros.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(panelCuadros);
        scroll.setPreferredSize(new Dimension(0, 250));
        add(scroll, BorderLayout.CENTER);
    }

    public JButton getBotonCrear() { return btnCrear; }
    public JButton getBotonAgregar() { return btnAgregar; }
    public JButton getBotonEliminar() { return btnEliminar; }
    public JButton getBotonLimpiar() { return btnLimpiar; }
    public JTextField getCampoNuevaClave() { return campoNuevaClave; }
    public int getTamanoSeleccionado() { return (int) spinnerTamano.getValue(); }
    public int getIndiceSeleccionado() { return indiceSeleccionado; }

    // Reconstruye todos los cuadros; el controller siempre entrega las claves YA en orden ascendente
    public void redibujar(int[] claves, int tamanoTotal) {
        panelCuadros.removeAll();
        cuadros.clear();
        indiceSeleccionado = -1;

        for (int i = 0; i < claves.length; i++) {
            final int indice = i;
            CuadroClave cuadro = new CuadroClave(claves[i], () -> seleccionar(indice));
            cuadros.add(cuadro);
            panelCuadros.add(cuadro);
        }

        labelOcupacion.setText(claves.length + "/" + tamanoTotal + " posiciones llenas");
        panelCuadros.revalidate();
        panelCuadros.repaint();
    }

    private void seleccionar(int indice) {
        if (indiceSeleccionado >= 0 && indiceSeleccionado < cuadros.size()) {
            cuadros.get(indiceSeleccionado).setSeleccionado(false);
        }
        indiceSeleccionado = indice;
        cuadros.get(indice).setSeleccionado(true);
    }

    public void animarResultado(ResultadoBusqueda resultado, Runnable alTerminar) {
        for (CuadroClave c : cuadros) c.setColorResaltado(null);

        Timer timer = new Timer(600, null);
        int[] indice = {0};
        timer.addActionListener(e -> {
            if (indice[0] < resultado.getPasos().size()) {
                PasoBusqueda paso = resultado.getPasos().get(indice[0]);
                int pos = paso.getPosicionRevisada();
                if (pos >= 0 && pos < cuadros.size()) {
                    cuadros.get(pos).setColorResaltado(
                            paso.isCoincide() ? new Color(144, 238, 144) : new Color(255, 200, 120));
                }
                indice[0]++;
            } else {
                timer.stop();
                if (alTerminar != null) alTerminar.run();
            }
        });
        timer.start();
    }
}

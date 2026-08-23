/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */

import edu.co.udistrital.controller.ResultadoHash;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComponenteTablaHash extends JPanel {

    private JComboBox<Integer> comboTamanoTabla;
    private JSpinner spinnerLongitudClave;
    private JComboBox<String> comboFuncionHash;
    private JComboBox<String> comboResolucionColision;
    private JButton btnConfirmarConfiguracion;

    private JTextField campoClave;
    private JButton btnInsertar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JLabel labelOcupacion;

    private JPanel panelCasillas;
    private List<CuadroClave> casillas = new ArrayList<>();
    private int indiceSeleccionado = -1;

    public ComponenteTablaHash() {
        setLayout(new BorderLayout(0, 10));

        JPanel panelConfig = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelConfig.add(new JLabel("Tamaño de tabla:"));
        comboTamanoTabla = new JComboBox<>(new Integer[]{10, 100, 1000});
        panelConfig.add(comboTamanoTabla);

        panelConfig.add(new JLabel("Longitud de clave (N dígitos):"));
        spinnerLongitudClave = new JSpinner(new SpinnerNumberModel(3, 1, 9, 1));
        panelConfig.add(spinnerLongitudClave);

        panelConfig.add(new JLabel("Función Hash:"));
        comboFuncionHash = new JComboBox<>(new String[]{
                "Hash Módulo", "Hash Cuadrado", "Hash Truncamiento", "Hash Plegamiento"});
        panelConfig.add(comboFuncionHash);

        panelConfig.add(new JLabel("Resolución de colisiones:"));
        comboResolucionColision = new JComboBox<>(new String[]{
                "Prueba Lineal", "Prueba Cuadrática", "Doble Función Hash"});
        panelConfig.add(comboResolucionColision);

        btnConfirmarConfiguracion = new JButton("Confirmar configuración");
        panelConfig.add(btnConfirmarConfiguracion);

        JPanel panelOperacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOperacion.add(new JLabel("Clave:"));
        campoClave = new JTextField(6);
        panelOperacion.add(campoClave);
        btnInsertar = new JButton("Insertar");
        panelOperacion.add(btnInsertar);
        btnBuscar = new JButton("Buscar");
        panelOperacion.add(btnBuscar);
        btnEliminar = new JButton("Eliminar seleccionado");
        panelOperacion.add(btnEliminar);
        btnLimpiar = new JButton("Limpiar tabla");
        panelOperacion.add(btnLimpiar);
        labelOcupacion = new JLabel("0/0 ocupadas");
        panelOperacion.add(labelOcupacion);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.add(panelConfig);
        panelSuperior.add(panelOperacion);
        add(panelSuperior, BorderLayout.NORTH);

        panelCasillas = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        panelCasillas.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(panelCasillas);
        scroll.setPreferredSize(new Dimension(0, 300));
        add(scroll, BorderLayout.CENTER);

        habilitarControlesOperacion(false);
    }

    public int getTamanoTablaSeleccionado() { return (int) comboTamanoTabla.getSelectedItem(); }
    public int getLongitudClaveSeleccionada() { return (int) spinnerLongitudClave.getValue(); }
    public String getFuncionHashSeleccionada() { return (String) comboFuncionHash.getSelectedItem(); }
    public String getResolucionColisionSeleccionada() { return (String) comboResolucionColision.getSelectedItem(); }

    public JButton getBotonConfirmarConfiguracion() { return btnConfirmarConfiguracion; }
    public JButton getBotonInsertar() { return btnInsertar; }
    public JButton getBotonBuscar() { return btnBuscar; }
    public JButton getBotonEliminar() { return btnEliminar; }
    public JButton getBotonLimpiar() { return btnLimpiar; }
    public JTextField getCampoClave() { return campoClave; }
    public int getIndiceSeleccionado() { return indiceSeleccionado; }

    public void bloquearConfiguracion() {
        comboTamanoTabla.setEnabled(false);
        spinnerLongitudClave.setEnabled(false);
        comboFuncionHash.setEnabled(false);
        comboResolucionColision.setEnabled(false);
        btnConfirmarConfiguracion.setEnabled(false);
        habilitarControlesOperacion(true);
    }

    private void habilitarControlesOperacion(boolean habilitar) {
        campoClave.setEnabled(habilitar);
        btnInsertar.setEnabled(habilitar);
        btnBuscar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
        btnLimpiar.setEnabled(habilitar);
    }

    public void redibujar(Integer[] posiciones) {
        panelCasillas.removeAll();
        casillas.clear();
        indiceSeleccionado = -1;

        int ocupadas = 0;
        for (int i = 0; i < posiciones.length; i++) {
            final int indice = i;
            Integer valor = posiciones[i];
            if (valor != null) ocupadas++;
            String texto = (valor != null) ? (i + ":" + valor) : String.valueOf(i);
            CuadroClave cuadro = new CuadroClave(texto, () -> seleccionar(indice));
            if (valor == null) cuadro.marcarVacia();
            casillas.add(cuadro);
            panelCasillas.add(cuadro);
        }

        labelOcupacion.setText(ocupadas + "/" + posiciones.length + " ocupadas");
        panelCasillas.revalidate();
        panelCasillas.repaint();
    }

    private void seleccionar(int indice) {
        if (indiceSeleccionado >= 0 && indiceSeleccionado < casillas.size()) {
            casillas.get(indiceSeleccionado).setSeleccionado(false);
        }
        indiceSeleccionado = indice;
        casillas.get(indice).setSeleccionado(true);
    }

    // Naranja = colisión (sigue probando), verde = éxito final, rojo = no encontrado / tabla llena
    public void animarResultado(ResultadoHash resultado, Runnable alTerminar) {
        for (CuadroClave c : casillas) c.setColorResaltado(null);

        List<Integer> probadas = resultado.getPosicionesProbadas();
        Timer timer = new Timer(500, null);
        int[] indice = {0};

        timer.addActionListener(e -> {
            if (indice[0] < probadas.size()) {
                int pos = probadas.get(indice[0]);
                boolean esUltima = (indice[0] == probadas.size() - 1);
                Color color;
                if (esUltima && resultado.isExito()) color = new Color(144, 238, 144);
                else if (esUltima) color = new Color(255, 120, 120);
                else color = new Color(255, 200, 120);

                if (pos >= 0 && pos < casillas.size()) casillas.get(pos).setColorResaltado(color);
                indice[0]++;
            } else {
                timer.stop();
                if (alTerminar != null) alTerminar.run();
            }
        });
        timer.start();
    }
}
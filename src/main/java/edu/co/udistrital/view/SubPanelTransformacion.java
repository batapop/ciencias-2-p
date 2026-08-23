/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */
import edu.co.udistrital.controller.*;

import javax.swing.*;
import java.awt.*;

public class SubPanelTransformacion extends JPanel {

    private ComponenteTablaHash componenteTabla;
    private TablaHashController controller;
    private JLabel labelResultado;

    public SubPanelTransformacion() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        componenteTabla = new ComponenteTablaHash();
        add(componenteTabla, BorderLayout.CENTER);

        controller = new TablaHashController(componenteTabla);

        labelResultado = new JLabel(" ");
        labelResultado.setFont(new Font("SansSerif", Font.BOLD, 14));
        JPanel panelResultado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelResultado.add(labelResultado);
        add(panelResultado, BorderLayout.SOUTH);

        componenteTabla.getBotonConfirmarConfiguracion().addActionListener(e -> {
            FuncionHash funcionHash = crearFuncionHash(componenteTabla.getFuncionHashSeleccionada());
            ResolucionColision resolucion = crearResolucionColision(componenteTabla.getResolucionColisionSeleccionada());

            boolean ok = controller.confirmarConfiguracion(
                    componenteTabla.getTamanoTablaSeleccionado(),
                    componenteTabla.getLongitudClaveSeleccionada(),
                    funcionHash, resolucion, this);

            if (ok) {
                componenteTabla.bloquearConfiguracion();
                labelResultado.setText("Configuración confirmada. Rango válido: 0 a "
                        + controller.getMaximoClavePermitida());
            }
        });

        componenteTabla.getBotonInsertar().addActionListener(e -> {
            labelResultado.setText("Insertando...");
            controller.insertarClave(componenteTabla.getCampoClave().getText(), this, resultado -> {
                if (resultado.isExito()) {
                    labelResultado.setForeground(new Color(0, 130, 0));
                    labelResultado.setText("Insertado en la posición " + resultado.getPosicionFinal());
                    componenteTabla.getCampoClave().setText("");
                } else if (resultado.isTablaLlena()) {
                    labelResultado.setForeground(Color.RED);
                    labelResultado.setText("Tabla llena: no se pudo insertar.");
                }
            });
        });

        componenteTabla.getBotonBuscar().addActionListener(e -> {
            labelResultado.setText("Buscando...");
            controller.buscarClave(componenteTabla.getCampoClave().getText(), this, resultado -> {
                if (resultado.isExito()) {
                    labelResultado.setForeground(new Color(0, 130, 0));
                    labelResultado.setText("Encontrado en la posición " + resultado.getPosicionFinal());
                } else {
                    labelResultado.setForeground(Color.RED);
                    labelResultado.setText("No encontrado.");
                }
            });
        });

        componenteTabla.getBotonEliminar().addActionListener(e ->
                controller.eliminarClave(componenteTabla.getIndiceSeleccionado(), this));

        componenteTabla.getBotonLimpiar().addActionListener(e -> {
            controller.limpiarTabla();
            labelResultado.setText(" ");
        });
    }

    private FuncionHash crearFuncionHash(String opcion) {
        switch (opcion) {
            case "Hash Módulo": return new HashModulo();
            case "Hash Cuadrado": return new HashCuadrado();
            case "Hash Truncamiento": return new HashTruncamiento();
            case "Hash Plegamiento": return new HashPlegamiento();
            default: return new HashModulo();
        }
    }

    private ResolucionColision crearResolucionColision(String opcion) {
        switch (opcion) {
            case "Prueba Lineal": return new ProbaLineal();
            case "Prueba Cuadrática": return new ProbaCuadratica();
            case "Doble Función Hash": return new DobleHash();
            default: return new ProbaLineal();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */
import edu.co.udistrital.model.ArbolTemasFactory;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final JTree arbolTemas;
    private final JPanel panelContenido;
    private final CardLayout cardLayout;

    public VentanaPrincipal() {
        setTitle("Estructuras de Datos II - Búsqueda y Grafos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLayout(new BorderLayout());

        arbolTemas = new JTree(ArbolTemasFactory.construir());
        arbolTemas.setRootVisible(false);
        JScrollPane scrollArbol = new JScrollPane(arbolTemas);
        scrollArbol.setPreferredSize(new Dimension(280, 0));

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        registrarPaneles(); // aquí agregas cada Visualiza al CardLayout

        add(scrollArbol, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        new edu.co.udistrital.controller.NavegacionController(arbolTemas, cardLayout, panelContenido);

        setLocationRelativeTo(null);
        setVisible(true);
    }

        private void registrarPaneles() {
            agregar(new edu.co.udistrital.view.PanelBusquedaInterna());
            agregar(new edu.co.udistrital.view.PanelFuncionesHash());
            agregar(new edu.co.udistrital.view.PanelColisionesHash());
            agregar(new edu.co.udistrital.view.PanelOtrasBusquedas());
            agregar(new edu.co.udistrital.view.PanelIndicesArchivos());

            agregar(new edu.co.udistrital.view.PanelFundamentosGrafos());
            agregar(new edu.co.udistrital.view.PanelCircuitosEspeciales());
            agregar(new edu.co.udistrital.view.PanelRepresentacionMemoria());
            agregar(new edu.co.udistrital.view.PanelArboles());
            agregar(new edu.co.udistrital.view.PanelArbolesExpansion());
            agregar(new edu.co.udistrital.view.PanelConjuntosCorte());
            agregar(new edu.co.udistrital.view.PanelMatricesGrafos());
            agregar(new edu.co.udistrital.view.PanelEstructurasAdyacencia());
            agregar(new edu.co.udistrital.view.PanelColoreo());
            agregar(new edu.co.udistrital.view.PanelPareamientos());
            agregar(new edu.co.udistrital.view.PanelEnvolventes());
        }
    

    private void agregar(Visualiza panel) {
        panelContenido.add(panel.getPanel(), panel.getIdentificador());
    }
}
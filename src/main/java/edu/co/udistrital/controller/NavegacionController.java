/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */

import edu.co.udistrital.model.NodoTema;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class NavegacionController implements TreeSelectionListener {

    private CardLayout cardLayout;
    private JPanel panelContenido;

    public NavegacionController(JTree arbol, CardLayout cardLayout, JPanel panelContenido) {
        this.cardLayout = cardLayout;
        this.panelContenido = panelContenido;
        arbol.addTreeSelectionListener(this);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        Object userObject = nodo.getUserObject();
        if (userObject instanceof NodoTema) {
            NodoTema tema = (NodoTema) userObject;
            if (tema.getIdPanel() != null) { // ignora los nodos padre (Tema 1 / Tema 2)
                cardLayout.show(panelContenido, tema.getIdPanel());
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
import javax.swing.*;
import edu.co.udistrital.view.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
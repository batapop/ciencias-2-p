/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.view;

/**
 *
 * @author david
 */
import javax.swing.JPanel;

public interface Visualiza {
    JPanel getPanel();
    String getIdentificador(); // debe coincidir con el nombre usado en el CardLayout
}
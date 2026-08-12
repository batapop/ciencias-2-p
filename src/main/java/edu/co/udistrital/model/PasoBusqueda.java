/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */
public class PasoBusqueda {
    private int posicionRevisada;
    private boolean coincide;

    public PasoBusqueda(int posicionRevisada, boolean coincide) {
        this.posicionRevisada = posicionRevisada;
        this.coincide = coincide;
    }
    public int getPosicionRevisada() { return posicionRevisada; }
    public boolean isCoincide() { return coincide; }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */
import java.util.Arrays;
import java.util.Comparator;

public class ArregloBusqueda {
    private Nodo[] elementos;

    public ArregloBusqueda(int n) {
        elementos = new Nodo[n];
        for (int i = 0; i < n; i++) {
            elementos[i] = new Nodo(0);
        }
    }

    public int getTamano() { return elementos.length; }
    public Nodo getNodo(int i) { return elementos[i]; }
    public void setClave(int i, int clave) { elementos[i].setClave(clave); }
    public Nodo[] getElementos() { return elementos; }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */
public class TablaHash {
    private Integer[] posiciones; // null = casilla vacía
    private int tamano;

    public TablaHash(int tamano) {
        this.tamano = tamano;
        this.posiciones = new Integer[tamano];
    }

    public int getTamano() { return tamano; }
    public Integer getClave(int indice) { return posiciones[indice]; }
    public void setClave(int indice, Integer clave) { posiciones[indice] = clave; }
    public boolean estaOcupada(int indice) { return posiciones[indice] != null; }

    public int contarOcupadas() {
        int c = 0;
        for (Integer v : posiciones) if (v != null) c++;
        return c;
    }

    public Integer[] getPosiciones() { return posiciones; }
}
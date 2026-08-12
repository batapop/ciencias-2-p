/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */

public class Nodo {
    private int clave;

    public Nodo(int clave) { this.clave = clave; }
    public int getClave() { return clave; }
    public void setClave(int clave) { this.clave = clave; }

    @Override
    public String toString() { return String.valueOf(clave); }
}
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

    public enum Tipo { RANGO, EVALUACION }

    private Tipo tipo;
    private int inicio;
    private int fin;
    private int posicion;
    private boolean coincide;

    private PasoBusqueda(Tipo tipo, int inicio, int fin, int posicion, boolean coincide) {
        this.tipo = tipo;
        this.inicio = inicio;
        this.fin = fin;
        this.posicion = posicion;
        this.coincide = coincide;
    }

    public static PasoBusqueda deRango(int inicio, int fin) {
        return new PasoBusqueda(Tipo.RANGO, inicio, fin, -1, false);
    }

    public static PasoBusqueda deEvaluacion(int posicion, boolean coincide) {
        return new PasoBusqueda(Tipo.EVALUACION, -1, -1, posicion, coincide);
    }

    public Tipo getTipo() { return tipo; }
    public int getInicio() { return inicio; }
    public int getFin() { return fin; }
    public int getPosicion() { return posicion; }
    public boolean isCoincide() { return coincide; }
}
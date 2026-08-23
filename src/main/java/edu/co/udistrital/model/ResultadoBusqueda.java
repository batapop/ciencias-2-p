/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */
import java.util.ArrayList;
import java.util.List;

public class ResultadoBusqueda {
    private boolean encontrado;
    private int posicionFinal;
    private List<PasoBusqueda> pasos = new ArrayList<>();

    public void agregarPasoEvaluacion(int posicion, boolean coincide) {
        pasos.add(PasoBusqueda.deEvaluacion(posicion, coincide));
    }

    public void agregarPasoRango(int inicio, int fin) {
        pasos.add(PasoBusqueda.deRango(inicio, fin));
    }

    public void setEncontrado(boolean encontrado) { this.encontrado = encontrado; }
    public void setPosicionFinal(int posicionFinal) { this.posicionFinal = posicionFinal; }
    public boolean isEncontrado() { return encontrado; }
    public int getPosicionFinal() { return posicionFinal; }
    public List<PasoBusqueda> getPasos() { return pasos; }
}
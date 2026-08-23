/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
import java.util.ArrayList;
import java.util.List;

public class ResultadoHash {
    private boolean exito;
    private boolean tablaLlena;
    private int posicionFinal = -1;
    private List<Integer> posicionesProbadas = new ArrayList<>();

    public void agregarProbada(int posicion) { posicionesProbadas.add(posicion); }

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }
    public boolean isTablaLlena() { return tablaLlena; }
    public void setTablaLlena(boolean tablaLlena) { this.tablaLlena = tablaLlena; }
    public int getPosicionFinal() { return posicionFinal; }
    public void setPosicionFinal(int posicionFinal) { this.posicionFinal = posicionFinal; }
    public List<Integer> getPosicionesProbadas() { return posicionesProbadas; }
}
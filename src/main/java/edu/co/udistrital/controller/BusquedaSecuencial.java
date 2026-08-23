/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
import edu.co.udistrital.model.Nodo;
import edu.co.udistrital.model.ResultadoBusqueda;

public class BusquedaSecuencial implements AlgoritmoBusqueda {
    @Override
    public ResultadoBusqueda buscar(Nodo[] arreglo, int claveBuscada) {
        ResultadoBusqueda resultado = new ResultadoBusqueda();
        for (int i = 0; i < arreglo.length; i++) {
            boolean coincide = arreglo[i].getClave() == claveBuscada;
            resultado.agregarPasoEvaluacion(i, coincide);
            if (coincide) {
                resultado.setEncontrado(true);
                resultado.setPosicionFinal(i);
                return resultado;
            }
        }
        resultado.setEncontrado(false);
        resultado.setPosicionFinal(-1);
        return resultado;
    }
}
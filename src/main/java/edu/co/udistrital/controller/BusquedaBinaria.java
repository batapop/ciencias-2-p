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

public class BusquedaBinaria implements AlgoritmoBusqueda {
    @Override
    public ResultadoBusqueda buscar(Nodo[] arreglo, int claveBuscada) {
        ResultadoBusqueda resultado = new ResultadoBusqueda();
        int inicio = 0, fin = arreglo.length - 1;

        while (inicio <= fin) {
            resultado.agregarPasoRango(inicio, fin); // muestra el grupo activo antes de evaluar

            int medio = (inicio + fin) / 2;
            boolean coincide = arreglo[medio].getClave() == claveBuscada;
            resultado.agregarPasoEvaluacion(medio, coincide);

            if (coincide) {
                resultado.setEncontrado(true);
                resultado.setPosicionFinal(medio);
                return resultado;
            } else if (arreglo[medio].getClave() < claveBuscada) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        resultado.setEncontrado(false);
        resultado.setPosicionFinal(-1);
        return resultado;
    }
}
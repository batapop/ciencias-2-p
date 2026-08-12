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
import java.util.Arrays;
import java.util.Comparator;

public class BusquedaBinaria implements AlgoritmoBusqueda {
    @Override
    public ResultadoBusqueda buscar(Nodo[] arreglo, int claveBuscada) {
        Arrays.sort(arreglo, Comparator.comparingInt(Nodo::getClave)); // ordena in-place
        ResultadoBusqueda resultado = new ResultadoBusqueda();
        int inicio = 0, fin = arreglo.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            boolean coincide = arreglo[medio].getClave() == claveBuscada;
            resultado.agregarPaso(medio, coincide);
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
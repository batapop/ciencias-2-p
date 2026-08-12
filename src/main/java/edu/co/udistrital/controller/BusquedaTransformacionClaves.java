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

public class BusquedaTransformacionClaves implements AlgoritmoBusqueda {
    // Método módulo: posicion = clave % tamaño del arreglo
    @Override
    public ResultadoBusqueda buscar(Nodo[] arreglo, int claveBuscada) {
        ResultadoBusqueda resultado = new ResultadoBusqueda();
        int posicion = claveBuscada % arreglo.length;

        boolean coincide = arreglo[posicion].getClave() == claveBuscada;
        resultado.agregarPaso(posicion, coincide);

        if (coincide) {
            resultado.setEncontrado(true);
            resultado.setPosicionFinal(posicion);
        } else {
            resultado.setEncontrado(false);
            resultado.setPosicionFinal(-1);
        }
        return resultado;
    }
}
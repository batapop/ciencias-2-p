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

public interface AlgoritmoBusqueda {
    ResultadoBusqueda buscar(Nodo[] arreglo, int claveBuscada);
}

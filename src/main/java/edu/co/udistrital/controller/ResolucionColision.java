/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
public interface ResolucionColision {
    // intento empieza en 1 (primera colisión). Debe devolver la siguiente posición a probar.
    int siguientePosicion(int clave, int posicionOriginal, int intento, int tamanoTabla, int longitudClave);
}
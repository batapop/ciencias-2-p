/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */

public class ProbaCuadratica implements ResolucionColision {
    @Override
    public int siguientePosicion(int clave, int posicionOriginal, int intento, int tamanoTabla, int longitudClave) {
        return (int) ((posicionOriginal + (long) intento * intento) % tamanoTabla);
    }
}
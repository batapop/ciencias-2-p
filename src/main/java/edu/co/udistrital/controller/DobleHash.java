/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */

public class DobleHash implements ResolucionColision {
    @Override
    public int siguientePosicion(int clave, int posicionOriginal, int intento, int tamanoTabla, int longitudClave) {
        // Segunda función hash: genera un "paso" distinto por clave (evita ciclos cortos)
        int divisor = (tamanoTabla - 1 == 0) ? 1 : (tamanoTabla - 1);
        int paso = 1 + (clave % divisor);
        return (int) ((posicionOriginal + (long) intento * paso) % tamanoTabla);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */

public class HashPlegamiento implements FuncionHash {
    @Override
    public int calcular(int clave, int tamanoTabla, int longitudClave) {
        String str = String.format("%0" + longitudClave + "d", clave);
        int suma = 0;
        // Divide en bloques de 2 dígitos de izquierda a derecha y los suma
        for (int i = 0; i < str.length(); i += 2) {
            String bloque = (i + 2 <= str.length()) ? str.substring(i, i + 2) : str.substring(i);
            suma += Integer.parseInt(bloque);
        }
        return suma % tamanoTabla;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
public class HashTruncamiento implements FuncionHash {
    @Override
    public int calcular(int clave, int tamanoTabla, int longitudClave) {
        // Rellena la clave a la longitud N configurada
        String str = String.format("%0" + longitudClave + "d", clave);

        // Extrae dígitos en posiciones pares (2ª, 4ª, 6ª... contando desde la izquierda)
        StringBuilder resultado = new StringBuilder();
        for (int i = 1; i < str.length(); i += 2) {
            resultado.append(str.charAt(i));
        }
        if (resultado.length() == 0) resultado.append("0");

        return Integer.parseInt(resultado.toString()) % tamanoTabla;
    }
}
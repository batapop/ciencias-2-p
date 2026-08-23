/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
public class HashCuadrado implements FuncionHash {
    @Override
    public int calcular(int clave, int tamanoTabla, int longitudClave) {
        long cuadrado = (long) clave * clave;
        String str = String.valueOf(cuadrado);

        // Cuántos dígitos necesito según el tamaño de tabla (10->1, 100->2, 1000->3)
        int digitosNecesarios = Integer.toString(tamanoTabla).length() - 1;

        // Si el cuadrado quedó corto, se rellena con ceros a la izquierda
        while (str.length() < digitosNecesarios) {
            str = "0" + str;
        }

        int inicio = (str.length() - digitosNecesarios) / 2;
        String centro = str.substring(inicio, inicio + digitosNecesarios);
        return Integer.parseInt(centro) % tamanoTabla;
    }
}
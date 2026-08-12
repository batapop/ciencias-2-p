/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
import edu.co.udistrital.model.ArregloBusqueda;
import edu.co.udistrital.model.ResultadoBusqueda;
import edu.co.udistrital.view.ComponenteArregloTabla;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.function.Consumer;

public class BusquedaController {

    private ComponenteArregloTabla vistaTabla;
    private AlgoritmoBusqueda algoritmo;
    private ArregloBusqueda arreglo;

    public BusquedaController(ComponenteArregloTabla vistaTabla, AlgoritmoBusqueda algoritmo) {
        this.vistaTabla = vistaTabla;
        this.algoritmo = algoritmo;
    }

    public void crearArreglo(int n) {
        arreglo = new ArregloBusqueda(n);
        vistaTabla.mostrarArregloVacio(n);
    }

    public void ejecutarBusqueda(String textoClaveBuscada, Component parentParaError,
                                  Consumer<ResultadoBusqueda> alTerminar) {
        if (arreglo == null) {
            JOptionPane.showMessageDialog(parentParaError, "Primero crea el arreglo.");
            return;
        }

        try {
            String[] valoresTabla = vistaTabla.leerValoresCrudos();
            for (int i = 0; i < valoresTabla.length; i++) {
                arreglo.setClave(i, Integer.parseInt(valoresTabla[i].trim()));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentParaError, "Todas las celdas del arreglo deben ser números enteros.");
            return;
        }

        int claveBuscada;
        try {
            claveBuscada = Integer.parseInt(textoClaveBuscada.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentParaError, "La clave a buscar debe ser un número entero.");
            return;
        }

        ResultadoBusqueda resultado = algoritmo.buscar(arreglo.getElementos(), claveBuscada);
        vistaTabla.refrescarValores(arreglo.getElementos());
        vistaTabla.animarResultado(resultado, () -> alTerminar.accept(resultado));
    }

    public ArregloBusqueda getArreglo() { return arreglo; }
}
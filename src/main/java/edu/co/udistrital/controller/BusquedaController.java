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
import edu.co.udistrital.model.Nodo;
import edu.co.udistrital.model.ResultadoBusqueda;
import edu.co.udistrital.view.ComponenteArregloCuadros;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.function.Consumer;

public class BusquedaController {

    private ComponenteArregloCuadros vista;
    private AlgoritmoBusqueda algoritmo;
    private ArregloBusqueda arreglo;
    private int cantidadLlena = 0;

    public BusquedaController(ComponenteArregloCuadros vista, AlgoritmoBusqueda algoritmo) {
        this.vista = vista;
        this.algoritmo = algoritmo;
    }

    public void crearArreglo(int n) {
        arreglo = new ArregloBusqueda(n);
        cantidadLlena = 0;
        redibujar();
    }

    public void agregarClave(String textoClave, Component parentParaError) {
        if (arreglo == null) {
            JOptionPane.showMessageDialog(parentParaError, "Primero crea el arreglo.");
            return;
        }
        if (cantidadLlena >= arreglo.getTamano()) {
            JOptionPane.showMessageDialog(parentParaError, "El arreglo ya está lleno.");
            return;
        }
        int clave;
        try {
            clave = Integer.parseInt(textoClave.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentParaError, "La clave debe ser un número entero.");
            return;
        }

        // Validación de duplicados
        for (int i = 0; i < cantidadLlena; i++) {
            if (arreglo.getNodo(i).getClave() == clave) {
                JOptionPane.showMessageDialog(parentParaError, "Esa clave ya existe. No se permiten claves repetidas.");
                return;
            }
        }

        // Encuentra la posición ordenada donde debe insertarse
        int posicion = 0;
        while (posicion < cantidadLlena && arreglo.getNodo(posicion).getClave() < clave) {
            posicion++;
        }
        // Recorre los mayores un puesto a la derecha para abrir espacio
        for (int i = cantidadLlena; i > posicion; i--) {
            arreglo.setClave(i, arreglo.getNodo(i - 1).getClave());
        }
        arreglo.setClave(posicion, clave);
        cantidadLlena++;
        redibujar();
    }

    public void eliminarClave(int indiceSeleccionado, Component parentParaError) {
        if (arreglo == null || cantidadLlena == 0) {
            JOptionPane.showMessageDialog(parentParaError, "No hay datos para eliminar.");
            return;
        }
        if (indiceSeleccionado < 0 || indiceSeleccionado >= cantidadLlena) {
            JOptionPane.showMessageDialog(parentParaError, "Selecciona un cuadro (haz clic sobre él) para eliminar.");
            return;
        }
        for (int i = indiceSeleccionado; i < cantidadLlena - 1; i++) {
            arreglo.setClave(i, arreglo.getNodo(i + 1).getClave());
        }
        cantidadLlena--;
        redibujar();
    }

    public void limpiarArreglo() {
        if (arreglo == null) return;
        cantidadLlena = 0;
        redibujar();
    }

    public void ejecutarBusqueda(String textoClaveBuscada, Component parentParaError,
                                  Consumer<ResultadoBusqueda> alTerminar) {
        if (arreglo == null || cantidadLlena == 0) {
            JOptionPane.showMessageDialog(parentParaError, "Primero agrega al menos una clave.");
            return;
        }
        int claveBuscada;
        try {
            claveBuscada = Integer.parseInt(textoClaveBuscada.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentParaError, "La clave a buscar debe ser un número entero.");
            return;
        }

        Nodo[] subArreglo = new Nodo[cantidadLlena];
        System.arraycopy(arreglo.getElementos(), 0, subArreglo, 0, cantidadLlena);

        ResultadoBusqueda resultado = algoritmo.buscar(subArreglo, claveBuscada);
        vista.animarResultado(resultado, () -> alTerminar.accept(resultado));
    }

    private void redibujar() {
        int[] claves = new int[cantidadLlena];
        for (int i = 0; i < cantidadLlena; i++) claves[i] = arreglo.getNodo(i).getClave();
        vista.redibujar(claves, arreglo.getTamano());
    }

    public ArregloBusqueda getArreglo() { return arreglo; }
    public int getCantidadLlena() { return cantidadLlena; }
}
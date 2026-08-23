/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.controller;

/**
 *
 * @author david
 */
import edu.co.udistrital.model.TablaHash;
import edu.co.udistrital.view.ComponenteTablaHash;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.function.Consumer;

public class TablaHashController {

    private ComponenteTablaHash vista;
    private TablaHash tabla;
    private FuncionHash funcionHash;
    private ResolucionColision resolucionColision;
    private int longitudClave;
    private boolean configuracionBloqueada = false;

    public TablaHashController(ComponenteTablaHash vista) {
        this.vista = vista;
    }

    /** Crea la tabla y bloquea la configuración. Devuelve false si algo es inválido. */
    public boolean confirmarConfiguracion(int tamanoTabla, int longitudClave,
                                           FuncionHash funcionHash, ResolucionColision resolucionColision,
                                           Component parentParaError) {
        if (tamanoTabla <= 0) {
            JOptionPane.showMessageDialog(parentParaError, "El tamaño de tabla debe ser mayor a 0.");
            return false;
        }
        this.tabla = new TablaHash(tamanoTabla);
        this.funcionHash = funcionHash;
        this.resolucionColision = resolucionColision;
        this.longitudClave = longitudClave;
        this.configuracionBloqueada = true;
        vista.redibujar(tabla.getPosiciones());
        return true;
    }

    public int getMaximoClavePermitida() { return (int) Math.pow(10, longitudClave) - 1; }

    /** Inserta con manejo de colisiones según la estrategia configurada. */
    public void insertarClave(String textoClave, Component parentParaError, Consumer<ResultadoHash> alTerminar) {
        if (!configuracionBloqueada) {
            JOptionPane.showMessageDialog(parentParaError, "Primero confirma la configuración de la tabla.");
            return;
        }
        Integer clave = validarClave(textoClave, parentParaError);
        if (clave == null) return;

        for (Integer v : tabla.getPosiciones()) {
            if (v != null && v.equals(clave)) {
                JOptionPane.showMessageDialog(parentParaError, "Esa clave ya existe en la tabla.");
                return;
            }
        }

        if (tabla.contarOcupadas() >= tabla.getTamano()) {
            JOptionPane.showMessageDialog(parentParaError,
                    "Error: La tabla Hash está llena. No se pueden ingresar más elementos.");
            return;
        }

        ResultadoHash resultado = new ResultadoHash();
        int posicionOriginal = funcionHash.calcular(clave, tabla.getTamano(), longitudClave);
        int posicionActual = posicionOriginal;
        int intento = 0;

        // Recorre colisiones hasta hallar espacio libre o agotar todas las posiciones posibles
        while (tabla.estaOcupada(posicionActual) && intento < tabla.getTamano()) {
            resultado.agregarProbada(posicionActual);
            intento++;
            posicionActual = resolucionColision.siguientePosicion(clave, posicionOriginal, intento, tabla.getTamano(), longitudClave);
        }

        if (tabla.estaOcupada(posicionActual)) {
            resultado.setExito(false);
            resultado.setTablaLlena(true);
            JOptionPane.showMessageDialog(parentParaError,
                    "Error: La tabla Hash está llena. No se pueden ingresar más elementos.");
            alTerminar.accept(resultado);
            return;
        }

        resultado.agregarProbada(posicionActual);
        tabla.setClave(posicionActual, clave);
        resultado.setExito(true);
        resultado.setPosicionFinal(posicionActual);

        vista.redibujar(tabla.getPosiciones());
        vista.animarResultado(resultado, () -> alTerminar.accept(resultado));
    }

    /** Busca siguiendo la misma secuencia de colisiones que usaría la inserción. */
    public void buscarClave(String textoClave, Component parentParaError, Consumer<ResultadoHash> alTerminar) {
        if (!configuracionBloqueada) {
            JOptionPane.showMessageDialog(parentParaError, "Primero confirma la configuración de la tabla.");
            return;
        }
        Integer clave = validarClave(textoClave, parentParaError);
        if (clave == null) return;

        ResultadoHash resultado = new ResultadoHash();
        int posicionOriginal = funcionHash.calcular(clave, tabla.getTamano(), longitudClave);
        int posicionActual = posicionOriginal;
        int intento = 0;

        while (intento < tabla.getTamano()) {
            resultado.agregarProbada(posicionActual);
            Integer valorActual = tabla.getClave(posicionActual);

            if (valorActual != null && valorActual.equals(clave)) {
                resultado.setExito(true);
                resultado.setPosicionFinal(posicionActual);
                vista.animarResultado(resultado, () -> alTerminar.accept(resultado));
                return;
            }
            if (valorActual == null) break; // casilla vacía en direccionamiento abierto = no está

            intento++;
            posicionActual = resolucionColision.siguientePosicion(clave, posicionOriginal, intento, tabla.getTamano(), longitudClave);
        }

        resultado.setExito(false);
        resultado.setPosicionFinal(-1);
        vista.animarResultado(resultado, () -> alTerminar.accept(resultado));
    }

    public void eliminarClave(int indiceSeleccionado, Component parentParaError) {
        if (tabla == null || indiceSeleccionado < 0 || !tabla.estaOcupada(indiceSeleccionado)) {
            JOptionPane.showMessageDialog(parentParaError, "Selecciona una casilla ocupada para eliminar.");
            return;
        }
        tabla.setClave(indiceSeleccionado, null);
        vista.redibujar(tabla.getPosiciones());
    }

    public void limpiarTabla() {
        if (tabla == null) return;
        for (int i = 0; i < tabla.getTamano(); i++) tabla.setClave(i, null);
        vista.redibujar(tabla.getPosiciones());
    }

    private Integer validarClave(String texto, Component parentParaError) {
        int clave;
        try {
            clave = Integer.parseInt(texto.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentParaError, "La clave debe ser un número entero.");
            return null;
        }
        if (clave < 0 || clave > getMaximoClavePermitida()) {
            JOptionPane.showMessageDialog(parentParaError,
                    "La clave debe estar entre 0 y " + getMaximoClavePermitida()
                            + " (según la longitud de clave configurada).");
            return null;
        }
        return clave;
    }
}
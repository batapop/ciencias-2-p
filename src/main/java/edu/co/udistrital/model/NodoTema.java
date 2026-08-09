/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */
import java.util.ArrayList;
import java.util.List;

public class NodoTema {
    private String titulo;
    private String idPanel; // null si es un nodo "padre" (Tema 1, Tema 2)
    private List<NodoTema> hijos = new ArrayList<>();

    public NodoTema(String titulo, String idPanel) {
        this.titulo = titulo;
        this.idPanel = idPanel;
    }

    public void agregarHijo(NodoTema hijo) { hijos.add(hijo); }
    public String getTitulo() { return titulo; }
    public String getIdPanel() { return idPanel; }
    public List<NodoTema> getHijos() { return hijos; }

    @Override
    public String toString() { return titulo; } // esto es lo que pinta el JTree
}
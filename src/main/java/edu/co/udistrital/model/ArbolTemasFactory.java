/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.co.udistrital.model;

/**
 *
 * @author david
 */
import javax.swing.tree.DefaultMutableTreeNode;

public class ArbolTemasFactory {

    public static DefaultMutableTreeNode construir() {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Contenido");

        DefaultMutableTreeNode tema1 = new DefaultMutableTreeNode(
                new NodoTema("Tema 1: Algoritmos de Búsqueda", null));
        tema1.add(new DefaultMutableTreeNode(new NodoTema("1.1 Búsquedas internas y externas", "busq_internas")));
        tema1.add(new DefaultMutableTreeNode(new NodoTema("1.2 Funciones Hash", "funciones_hash")));
        tema1.add(new DefaultMutableTreeNode(new NodoTema("1.3 Colisiones en Tablas Hash", "colisiones_hash")));
        tema1.add(new DefaultMutableTreeNode(new NodoTema("1.4 Otras búsquedas internas", "otras_busquedas")));
        tema1.add(new DefaultMutableTreeNode(new NodoTema("1.5 Índices para archivos", "indices_archivos")));

        DefaultMutableTreeNode tema2 = new DefaultMutableTreeNode(
                new NodoTema("Tema 2: Teoría de Grafos", null));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.1 Definiciones básicas", "fundamentos_grafos")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.2 Circuitos especiales", "circuitos_especiales")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.3 Representación en memoria", "repres_memoria")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.4 Árboles", "arboles")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.5 Árboles de expansión y caminos mínimos", "arboles_expansion")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.6 Conjuntos de corte", "conjuntos_corte")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.7 Representación mediante matrices", "matrices_grafos")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.8 Estructuras de adyacencia", "estruct_adyacencia")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.9 Coloreo y particionamiento", "coloreo")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.10 Pareamientos", "pareamientos")));
        tema2.add(new DefaultMutableTreeNode(new NodoTema("2.11 Envolventes (Cliques)", "envolventes")));

        raiz.add(tema1);
        raiz.add(tema2);
        return raiz;
    }
}
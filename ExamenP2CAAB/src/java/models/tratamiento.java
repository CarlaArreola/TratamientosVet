/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author carla
 */
public class tratamiento {

    private int id;
    private String nombre;
    private double costo;

    public tratamiento(int id, String nombre, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }
    
}

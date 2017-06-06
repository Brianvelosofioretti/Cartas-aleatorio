/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exposicion.objetos;

import java.util.ArrayList;

/**
 *
 * @author acomesanavila
 */
public class Jugador {

    private String nombre;
    private ArrayList mano;

    public Jugador() {
    }

    public Jugador(String nombre, ArrayList mano) {
        this.nombre = nombre;
        this.mano = mano;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList getMano() {
        return mano;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMano(ArrayList mano) {
        this.mano = mano;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", mano=" + mano + '}';
    }

}

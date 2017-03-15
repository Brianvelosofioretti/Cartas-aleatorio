/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exposicion;

import exposicion.objetos.Carta;
import exposicion.objetos.Jugador;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import operaciones.calculo;

/**
 *
 * @author acomesanavila
 */
public class Metodos {

    ArrayList<Carta> baraja = new ArrayList();//llenar con todas las cartas
    ArrayList<Carta> jugador1;
    ArrayList<Carta> jugador2;
    Scanner sc;
    Jugador ju1, ju2;

    public void amosar() {
        for (int i = 0; i < baraja.size(); i++) {
            System.out.println(baraja.get(i));//Comprobacion del orden de la baraja
        }
    }

    public void amosarManoJugador(ArrayList<Carta> a) {

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));//Mano del jugador 1
        }

    }

    public void cargarArray() {//se llenan los array de cada jugador con una porcion del array baraja
        Collections.shuffle(baraja);//Desordenamos la baraja 
        JOptionPane.showMessageDialog(null, "Introduce Nombre Jugador");
        ju1 = new Jugador(calculo.pedirString(), jugador1 = new ArrayList(baraja.subList(0, 9)));// 0= posicion inicial y 9 posicion final en el array 
        ju2 = new Jugador(calculo.pedirString(), jugador2 = new ArrayList(baraja.subList(9, 21)));// 9= posicion inicial y 21 posicion final en el array 

    }

    public void pares(List<Carta> jugador) {

        Carta carta = new Carta();
        for (int j = 0; j < jugador.size(); j++) {//carta a comparar            

            for (int i = 0; i < jugador.size(); i++) {//comparacion con el resto de la mano
                if (jugador.get(j).getNumero() == jugador.get(i).getNumero()) {
                    if (jugador.get(j).equals(jugador.get(i)) == false) {
                        jugador.get(j).setSuerte(true);
                        jugador.get(i).setSuerte(true);
                    }
                }

            }
        }

    }

    public void eliminar(List<Carta> jugador) {
        Iterator<Carta> g = jugador.listIterator();
        while (g.hasNext()) {
            if (g.next().isSuerte() == true) {
                g.remove();
            }

        }
    }

    public void robar(ArrayList<Carta> lista, ArrayList<Carta> lista2) {
        System.out.println("Introduce carta a robar");
        int num = calculo.pedirInt();// importado de mi libreria
        try {
            lista.add(lista2.get(num - 1));
        } catch (Exception ex) {
            System.out.println("Robar debe ser < " + lista.size());
        }
        lista2.remove(num - 1);
        pares(lista);
        eliminar(lista);
    }

    public void ganador() {
        if (jugador1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Ha ganado " + ju1.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "Ha ganado " + ju2.getNombre());
        }
    }

    public void turnos() {

        pares(jugador1);
        pares(jugador2);
        eliminar(jugador2);
        eliminar(jugador1);
        do {
            JOptionPane.showMessageDialog(null, "Turno jugador 1");
            robar(jugador1, jugador2);
            if (jugador1.isEmpty() == false) {

                JOptionPane.showMessageDialog(null, "Turno jugador 2");
                robar(jugador2, jugador1);
            }

        } while (jugador1.isEmpty() == false && jugador2.isEmpty() == false);

    }

    public void llenarBaraja(String nomFich) {
        String[] aux;
        try {
            sc = new Scanner(new File(nomFich));
            while (sc.hasNextLine()) {
                aux = sc.nextLine().split(",");
                Carta cart = new Carta(Integer.parseInt(aux[0]), aux[1]);
                baraja.add(cart);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            sc.close();
        }

    }

}

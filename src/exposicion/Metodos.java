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
        //Collections.shuffle(baraja);
        JOptionPane.showMessageDialog(null, "Introduce Nombre Jugador");
        ju1 = new Jugador(calculo.pedirString(), jugador1 = new ArrayList(baraja.subList(0, 9)));// 0= posicion inicial y final en el array 
        ju2 = new Jugador(calculo.pedirString(), jugador2 = new ArrayList(baraja.subList(9, 21)));
        amosarManoJugador(jugador2);
    }

    public void pares(List<Carta> jugador) {

        Carta carta = new Carta();
        for (int j = 0; j < jugador.size(); j++) {//carta a comparar            
            carta = jugador.get(j);//*
            for (int i = 0; i < jugador.size(); i++) {//comparacion con el resto de la mano
                if (carta.getNumero() == jugador.get(i).getNumero() )
                        if(carta.equals(jugador.get(i)) == false) {
                    jugador.get(j).setSuerte(true);
                    jugador.get(i).setSuerte(true);
                        }
                
            }
        }

    }

    public void eliminar(List<Carta> jugador) {
        System.out.println(jugador.size());
        for (int y = 0; y < jugador.size(); y++) {           
            if (jugador.get(y).isSuerte() == true) {
                System.out.println("********"+jugador.get(y));
                
                jugador.remove(y);
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
        lista.remove(num - 1);
        pares(lista);
        eliminar(lista);
    }

    public void ganador() {
        if (jugador1.isEmpty() == true) {
            System.out.println("Ha ganado " + ju1.getNombre());
        } else {
            System.out.println("Ha ganado " + ju2.getNombre());
        }
    }

    public void turnos() {
        amosarManoJugador(jugador1);
        pares(jugador1);
        pares(jugador2);
        eliminar(jugador2);
        eliminar(jugador1);
        do {
            JOptionPane.showMessageDialog(null, "Turno jugador 1");
            robar(jugador1, jugador2);
            amosarManoJugador(jugador1);
            JOptionPane.showMessageDialog(null, "Turno jugador 2");
            robar(jugador2, jugador1);
            amosarManoJugador(jugador2);
        } while (jugador1.isEmpty() == false || jugador2.isEmpty() == false);

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

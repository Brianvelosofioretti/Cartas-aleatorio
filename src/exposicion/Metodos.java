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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import operaciones.operaciones;

/**
 *
 * @author acomesanavila
 */
public class Metodos {

    public static int turno = 0;

    public static String nombre = null;
    private Statement stmt = null;

    private Connection c = null;

    ArrayList<Carta> baraja = new ArrayList();//llenar con todas las cartas
    public static ArrayList<Carta> jugador1;
    public static ArrayList<Carta> jugador2;
    Scanner sc;
    public static Jugador ju1, ju2;

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
        ju1 = new Jugador(operaciones.pedirString(), jugador1 = new ArrayList(baraja.subList(0, 9)));// 0= posicion inicial y 9 posicion final en el array 
        ju2 = new Jugador(operaciones.pedirString(), jugador2 = new ArrayList(baraja.subList(9, 21)));// 9= posicion inicial y 21 posicion final en el array 

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
        eliminar(jugador);

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
        int num = operaciones.pedirInt();// importado de mi libreria
        try {
            lista.add(lista2.get(num - 1));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Robar debe ser < " + lista.size());
        }
        lista2.remove(num - 1);
        pares(lista);
        eliminar(lista);
    }

    public void ganador() {
        if (jugador1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ha ganado " + ju1.getNombre());
            nombre = ju1.getNombre();
        } else if (jugador2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ha ganado " + ju2.getNombre());
            nombre = ju2.getNombre();
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

    public void conectaBase() {

        try {

            c = DriverManager.getConnection("jdbc:sqlite:BaseCartas.db");//nombre y driver de la base de datos
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        JOptionPane.showMessageDialog(null, "Base de datos conectada");
    }

    public void creaTabla() {
        try {
            stmt = c.createStatement();

            String sql = "CREATE TABLE Ganadores  "
                    + "(Nombre TEXT PRIMARY KEY     NOT NULL,"
                    + " Victorias            INT     NOT NULL )";

            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        JOptionPane.showMessageDialog(null, "Tabla creada correctamente");
    }

    public void insertar() {

        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:BaseCartas.db");
            stmt = c.createStatement();
            c.setAutoCommit(false);
            String sql = "INSERT INTO Ganadores (Nombre,Victorias)"
                    + "VALUES("
                    + "'" + nombre + "'"
                    + ","
                    + 1
                    + ");";

            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();//guardamos cambios

        } catch (Exception e) {
            int victorias = 0;
            try {

                ResultSet rs = stmt.executeQuery("select Victorias from Ganadores where nombre='" + nombre + "';");
                while (rs.next()) {
                    victorias = rs.getInt("Victorias");

                }
                rs.close();
                stmt = c.createStatement();
                c.setAutoCommit(false);

                String sql = "UPDATE Ganadores set Victorias"
                        + " = " + (victorias + 1)
                        + " where Nombre ='"
                        + nombre + "';";
                stmt.executeUpdate(sql);
                c.commit();
                stmt.close();

            } catch (SQLException ex) {
                System.out.println("fallo");
            }

        }

    }

    public void cambioVentana(ArrayList ju1, ArrayList ju2, JTextArea area, JTextArea area2) {
        String algo = "";
        String oculta = "";
        if (turno == 0) {
            for (int i = 0; i < Metodos.jugador1.size(); i++) {
                algo = algo + "\n" + Metodos.jugador1.get(i).toString();
                area.setText(algo);

            }
            algo = "";
            for (int i = 0; i < Metodos.jugador2.size(); i++) {
                oculta = oculta + "\n" + "*****";
                area2.setText(oculta);

            }
            oculta = "";

        } else {

            for (int i = 0; i < Metodos.jugador2.size(); i++) {
                algo = algo + "\n" + Metodos.jugador2.get(i).toString();
                area.setText(algo);

            }
            algo = "";
            for (int i = 0; i < Metodos.jugador1.size(); i++) {
                oculta = oculta + "\n" + "*****";
                area2.setText(oculta);

            }
            oculta = "";
        }

    }
}

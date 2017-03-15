/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exposicion.objetos;

import java.util.Objects;

/**
 *
 * @author acomesanavila
 */
public class Carta {

    int numero;
    String palo;
    boolean suerte = false;

    public Carta() {
    }

    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public boolean isSuerte() {
        return suerte;
    }

    public void setSuerte(boolean suerte) {
        this.suerte = suerte;
    }

    public int getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        return numero + "," + palo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.palo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean aux = false;
        if (this == obj) {
            aux = true;
        }
        if (obj == null) {
            aux = false;
        }
        if (getClass() != obj.getClass()) {
            aux = false;
        }
        final Carta other = (Carta) obj;
        if (!Objects.equals(this.palo, other.palo)) {
            aux = false;
        }

        return aux;
    }

}

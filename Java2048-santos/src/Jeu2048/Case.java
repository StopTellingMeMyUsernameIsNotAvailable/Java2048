/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu2048;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Solene
 */
public class Case implements Parametres {

    private int x, y;
    private int valeur;
    private Grille grille;
    private int which;

    public Case(int a, int o, int v, int w) {
        this.x = a;
        this.y = o;
        this.valeur = v;
        this.which = w;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getValeur() {
        return this.valeur;
    }

    public int getWhich() {
        return this.which;
    }

    public void setX(int a) {
        this.x = a;
    }

    public void setY(int o) {
        this.y = o;
    }

    public void setValeur(int v) {
        this.valeur = v;
    }

    public void setGrille(Grille g) {
        this.grille = g;
    }

    public void setWhich(int w) {
        this.which = w;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + "," + this.which + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Case) {
            Case verif = (Case) obj;
            if ((verif.x == this.x) && (verif.y == this.y) && (verif.which == this.which)) {
                return true;
            }
        }
        return false;
    }

    public boolean valeurEgale(Case c) {
        if (c != null) {
            return this.valeur == c.valeur;
        }
        return false;
    }

    public Case voisin(int dir, HashSet<Case> g) {

        if (!g.isEmpty()) {
            switch (dir) {
                case HAUT:
                    if (!grille.bordsGrille(HAUT).contains(this)) {
                        for (Case c : g) {
                            if ((this.y == c.y) && (this.x == c.x + 1) && (this.which == c.which)) {
                                //System.out.println(this.toString()+" - "+c.toString());
                                return c;
                            }
                        }
                    }
                    break;
                case BAS:
                    if (!grille.bordsGrille(BAS).contains(this)) {
                        for (Case c : g) {
                            if ((this.y == c.y) && (this.x == c.x - 1) && (this.which == c.which)) {
                                //System.out.println(this.toString()+" - "+c.toString());
                                return c;
                            }
                        }
                    }
                case GAUCHE:
                    if (!grille.bordsGrille(GAUCHE).contains(this)) {
                        for (Case c : g) {
                            if ((this.x == c.x) && (this.y == c.y + 1) && (this.which == c.which)) {
                                //System.out.println(this.toString()+" - "+c.toString());
                                return c;
                            }
                        }
                    }
                case DROITE:
                    if (!grille.bordsGrille(DROITE).contains(this)) {
                        for (Case c : g) {
                            if ((this.x == c.x) && (this.y == c.y - 1) && (this.which == c.which)) {
                                //System.out.println(this.toString()+" - "+c.toString());
                                return c;
                            }
                        }
                    }
                default:
                    break;
            }
        }
        return null;
    }

}

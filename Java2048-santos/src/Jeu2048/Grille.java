/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Solene
 */
public class Grille implements Parametres {

    //BON ON RECOMMENCE DEPUIS LE DEBUT
    private HashSet<Case> grilleUne;
    private HashSet<Case> grilleDeux;
    private HashSet<Case> grilleTrois;
    private int valeurMax1;
    private int valeurMax2;
    private int valeurMax3;

    public Grille() {
        this.grilleUne = new HashSet<>();
        this.grilleDeux = new HashSet<>();
        this.grilleTrois = new HashSet<>();
        this.valeurMax1 = 0;
        this.valeurMax2 = 0;
        this.valeurMax3 = 0;
    }

    public HashSet<Case> getGrilleUne() {
        return this.grilleUne;
    }

    public HashSet<Case> getGrilleDeux() {
        return this.grilleDeux;
    }

    public HashSet<Case> getGrilleTrois() {
        return this.grilleTrois;
    }

    public int getValeurMax1() {
        return this.valeurMax1;
    }

    public int getValeurMax2() {
        return this.valeurMax2;
    }

    public int getValeurMax3() {
        return this.valeurMax3;
    }

    @Override
    public String toString() {
        int[][] tab1 = new int[TAILLE][TAILLE];
        int[][] tab2 = new int[TAILLE][TAILLE];
        int[][] tab3 = new int[TAILLE][TAILLE];

        for (Case a : this.grilleUne) {
            tab1[a.getX()][a.getY()] = a.getValeur();
        }

        for (Case b : this.grilleDeux) {
            tab2[b.getX()][b.getY()] = b.getValeur();
        }
        for (Case c : this.grilleTrois) {
            tab3[c.getX()][c.getY()] = c.getValeur();
        }

        String res = "";
        for (int i = 0; i < TAILLE; i++) {
            res += "[";
            for (int j = 0; j < TAILLE; j++) {
                res += " " + tab1[i][j] + " ";
            }
            res += "]   [";
            for (int k = 0; k < TAILLE; k++) {
                res += " " + tab2[i][k] + " ";
            }
            res += "]   [";
            for (int l = 0; l < TAILLE; l++) {
                res += " " + tab3[i][l] + " ";
            }
            res += "]\n";
        }

        return res;
    }

    public ArrayList listeCasesVides() {
        if (this.grilleUne.size() + this.grilleDeux.size() + this.grilleTrois.size() < TAILLE * TAILLE * 3) {
            ArrayList<Case> listCaseVide = new ArrayList<>();

            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case compare = new Case(x, y, 0, 1);
                    if (!this.grilleUne.contains(compare)) {
                        listCaseVide.add(compare);
                    }
                }
            }

            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case compare = new Case(x, y, 0, 2);
                    if (!this.grilleDeux.contains(compare)) {
                        listCaseVide.add(compare);
                    }
                }
            }

            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case compare = new Case(x, y, 0, 3);
                    if (!this.grilleTrois.contains(compare)) {
                        listCaseVide.add(compare);
                    }
                }
            }
            return listCaseVide;
        }
        return null;
    }

    public void genererCaseRandom() {
        ArrayList<Case> listCaseVide = listeCasesVides();

        Case nouvCase = listCaseVide.get((int) (Math.random() * listCaseVide.size()));

        nouvCase.setValeur(2);

        nouvCase.setGrille(this);

        switch (nouvCase.getWhich()) {
            case 1:
                this.grilleUne.add(nouvCase);
                break;
            case 2:
                this.grilleDeux.add(nouvCase);
                break;
            case 3:
                this.grilleTrois.add(nouvCase);
                break;
            default:
                break;
        }
    }

    public void fusion(Case c, int dir) {

        //grille 1
        Case ex1 = c.voisin(dir, grilleUne);
        if (ex1 != null) {
            //System.out.println(c.toString() + " - " + ex1.toString());
            if (ex1.valeurEgale(c)) {
                switch (dir) {
                    case HAUT:
                        if (ex1.getX() < c.getX()) {
                            ex1.setValeur(ex1.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax1 < ex1.getValeur()) {
                                this.valeurMax1 = ex1.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex1.setValeur(0);
                            if (this.valeurMax1 < c.getValeur()) {
                                this.valeurMax1 = c.getValeur();
                            }
                        }
                        break;
                    case BAS:
                        if (ex1.getX() > c.getX()) {
                            ex1.setValeur(ex1.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax1 < ex1.getValeur()) {
                                this.valeurMax1 = ex1.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex1.setValeur(0);
                            if (this.valeurMax1 < c.getValeur()) {
                                this.valeurMax1 = c.getValeur();
                            }
                        }
                        break;
                    case GAUCHE:
                        if (ex1.getY() < c.getY()) {
                            ex1.setValeur(ex1.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax1 < ex1.getValeur()) {
                                this.valeurMax1 = ex1.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex1.setValeur(0);
                            if (this.valeurMax1 < c.getValeur()) {
                                this.valeurMax1 = c.getValeur();
                            }
                        }
                        break;
                    case DROITE:
                        if (ex1.getY() > c.getY()) {
                            ex1.setValeur(ex1.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax1 < ex1.getValeur()) {
                                this.valeurMax1 = ex1.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex1.setValeur(0);
                            if (this.valeurMax1 < c.getValeur()) {
                                this.valeurMax1 = c.getValeur();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        //grille 2
        Case ex2 = c.voisin(dir, grilleDeux);
        if (ex2 != null) {
            //System.out.println(c.toString() + " - " + ex2.toString());
            if (ex2.valeurEgale(c)) {
                switch (dir) {
                    case HAUT:
                        if (ex2.getX() < c.getX()) {
                            ex2.setValeur(ex2.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax2 < ex2.getValeur()) {
                                this.valeurMax2 = ex2.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex2.setValeur(0);
                            if (this.valeurMax2 < c.getValeur()) {
                                this.valeurMax2 = c.getValeur();
                            }
                        }
                        break;
                    case BAS:
                        if (ex2.getX() > c.getX()) {
                            ex2.setValeur(ex2.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax2 < ex2.getValeur()) {
                                this.valeurMax2 = ex2.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex2.setValeur(0);
                            if (this.valeurMax2 < c.getValeur()) {
                                this.valeurMax2 = c.getValeur();
                            }
                        }
                        break;
                    case GAUCHE:
                        if (ex2.getY() < c.getY()) {
                            ex2.setValeur(ex2.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax2 < ex2.getValeur()) {
                                this.valeurMax2 = ex2.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex2.setValeur(0);
                            if (this.valeurMax2 < c.getValeur()) {
                                this.valeurMax2 = c.getValeur();
                            }
                        }
                        break;
                    case DROITE:
                        if (ex2.getY() > c.getY()) {
                            ex2.setValeur(ex2.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax2 < ex2.getValeur()) {
                                this.valeurMax2 = ex2.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex2.setValeur(0);
                            if (this.valeurMax2 < c.getValeur()) {
                                this.valeurMax2 = c.getValeur();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        Case ex3 = c.voisin(dir, grilleTrois);
        if (ex3 != null) {
            //System.out.println(c.toString() + " - " + ex.toString());
            if (ex3.valeurEgale(c)) {
                switch (dir) {
                    case HAUT:
                        if (ex3.getX() < c.getX()) {
                            ex3.setValeur(ex3.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax3 < ex3.getValeur()) {
                                this.valeurMax3 = ex3.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex3.setValeur(0);
                            if (this.valeurMax3 < c.getValeur()) {
                                this.valeurMax3 = c.getValeur();
                            }
                        }
                        break;
                    case BAS:
                        if (ex3.getX() > c.getX()) {
                            ex3.setValeur(ex3.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax3 < ex3.getValeur()) {
                                this.valeurMax3 = ex3.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex3.setValeur(0);
                            if (this.valeurMax3 < c.getValeur()) {
                                this.valeurMax3 = c.getValeur();
                            }
                        }
                        break;
                    case GAUCHE:
                        if (ex3.getY() < c.getY()) {
                            ex3.setValeur(ex3.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax3 < ex3.getValeur()) {
                                this.valeurMax3 = ex3.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex3.setValeur(0);
                            if (this.valeurMax3 < c.getValeur()) {
                                this.valeurMax3 = c.getValeur();
                            }
                        }
                        break;
                    case DROITE:
                        if (ex3.getY() > c.getY()) {
                            ex3.setValeur(ex3.getValeur() * 2);
                            c.setValeur(0);
                            if (this.valeurMax3 < ex3.getValeur()) {
                                this.valeurMax3 = ex3.getValeur();
                            }
                        } else {
                            c.setValeur(c.getValeur() * 2);
                            ex3.setValeur(0);
                            if (this.valeurMax3 < c.getValeur()) {
                                this.valeurMax3 = c.getValeur();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }

    public ArrayList bordsGrille(int dir) {
        ArrayList<Case> listeBords = new ArrayList<>();

        //grille 1
        if (!grilleUne.isEmpty()) {
            switch (dir) {
                case HAUT:
                    for (Case a : grilleUne) {
                        if (a.getX() == 0) {
                            listeBords.add(a);
                        }
                    }
                    break;
                case BAS:
                    for (Case a : grilleUne) {
                        if (a.getX() == TAILLE - 1) {
                            listeBords.add(a);
                        }
                    }
                    break;
                case GAUCHE:
                    for (Case a : grilleUne) {
                        if (a.getY() == 0) {
                            listeBords.add(a);
                        }
                    }
                    break;
                default:
                    for (Case a : grilleUne) {
                        if (a.getY() == TAILLE - 1) {
                            listeBords.add(a);
                        }
                    }
                    break;
            }
        }

        //grille 2
        if (!grilleDeux.isEmpty()) {
            switch (dir) {
                case HAUT:
                    for (Case b : grilleDeux) {
                        if (b.getX() == 0) {
                            listeBords.add(b);
                        }
                    }
                    break;
                case BAS:
                    for (Case b : grilleDeux) {
                        if (b.getX() == TAILLE - 1) {
                            listeBords.add(b);
                        }
                    }
                    break;
                case GAUCHE:
                    for (Case b : grilleDeux) {
                        if (b.getY() == 0) {
                            listeBords.add(b);
                        }
                    }
                    break;
                default:
                    for (Case b : grilleDeux) {
                        if (b.getY() == TAILLE - 1) {
                            listeBords.add(b);
                        }
                    }
                    break;
            }
        }

        //grille 3
        if (!grilleTrois.isEmpty()) {
            switch (dir) {
                case HAUT:
                    for (Case c : grilleTrois) {
                        if (c.getX() == 0) {
                            listeBords.add(c);
                        }
                    }
                    break;
                case BAS:
                    for (Case c : grilleTrois) {
                        if (c.getX() == TAILLE - 1) {
                            listeBords.add(c);
                        }
                    }
                    break;
                case GAUCHE:
                    for (Case c : grilleTrois) {
                        if (c.getY() == 0) {
                            listeBords.add(c);
                        }
                    }
                    break;
                default:
                    for (Case c : grilleTrois) {
                        if (c.getY() == TAILLE - 1) {
                            listeBords.add(c);
                        }
                    }
                    break;
            }
        }
        return listeBords;
    }

    public void onMove(int dir) {
        switch (dir) {
            case HAUT:

                //grille 1
                for (Case toutes : grilleUne) {
                    if (!bordsGrille(HAUT).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX() - 1, toutes.getY(), 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setX(toutes.getX() - 1);
                            compare.setX(toutes.getX() - 1);
                        }
                        fusion(toutes, HAUT);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 2
                for (Case toutes : grilleDeux) {
                    if (!bordsGrille(HAUT).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX() - 1, toutes.getY(), 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setX(toutes.getX() - 1);
                            compare.setX(toutes.getX() - 1);
                        }
                        fusion(toutes, HAUT);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 3
                for (Case toutes : grilleTrois) {
                    if (!bordsGrille(HAUT).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX() - 1, toutes.getY(), 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setX(toutes.getX() - 1);
                            compare.setX(toutes.getX() - 1);
                        }
                        fusion(toutes, HAUT);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }
                break;

            case BAS:

                //grille 1
                for (Case toutes : grilleUne) {
                    if (!bordsGrille(BAS).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX() + 1, toutes.getY(), 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setX(toutes.getX() + 1);
                            compare.setX(toutes.getX() + 1);
                        }
                        fusion(toutes, BAS);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 2
                for (Case toutes : grilleDeux) {
                    if (!bordsGrille(BAS).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX() + 1, toutes.getY(), 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setX(toutes.getX() + 1);
                            compare.setX(toutes.getX() + 1);
                        }
                        fusion(toutes, BAS);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 3
                for (Case toutes : grilleTrois) {
                    if (!bordsGrille(BAS).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX() + 1, toutes.getY(), 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setX(toutes.getX() + 1);
                            compare.setX(toutes.getX() + 1);
                        }
                        fusion(toutes, BAS);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }
                break;
            case GAUCHE:

                //grille 1
                for (Case toutes : grilleUne) {
                    if (!bordsGrille(GAUCHE).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX(), toutes.getY() - 1, 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setY(toutes.getY() - 1);
                            compare.setY(toutes.getY() - 1);
                        }
                        fusion(toutes, GAUCHE);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 2
                for (Case toutes : grilleDeux) {
                    if (!bordsGrille(GAUCHE).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX(), toutes.getY() - 1, 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setY(toutes.getY() - 1);
                            compare.setY(toutes.getY() - 1);
                        }
                        fusion(toutes, GAUCHE);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 3
                for (Case toutes : grilleTrois) {
                    if (!bordsGrille(GAUCHE).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX(), toutes.getY() - 1, 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setY(toutes.getY() - 1);
                            compare.setY(toutes.getY() - 1);
                        }
                        fusion(toutes, GAUCHE);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }
                break;
            case DROITE:

                //grille 1
                for (Case toutes : grilleUne) {
                    if (!bordsGrille(DROITE).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX(), toutes.getY() + 1, 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setY(toutes.getY() + 1);
                            compare.setY(toutes.getY() + 1);
                        }
                        fusion(toutes, DROITE);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 2
                for (Case toutes : grilleDeux) {
                    if (!bordsGrille(DROITE).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX(), toutes.getY() + 1, 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setY(toutes.getY() + 1);
                            compare.setY(toutes.getY() + 1);
                        }
                        fusion(toutes, DROITE);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }

                //grille 3
                for (Case toutes : grilleTrois) {
                    if (!bordsGrille(DROITE).contains(toutes)) {
                        //System.out.println(toutes.toString());
                        Case compare = new Case(toutes.getX(), toutes.getY() + 1, 0, 1);
                        //System.out.println(compare.toString());
                        while (listeCasesVides().contains(compare)) {
                            toutes.setY(toutes.getY() + 1);
                            compare.setY(toutes.getY() + 1);
                        }
                        fusion(toutes, DROITE);
                        /*if(!listeCasesVides().isEmpty()){
                            genererCaseRandom();
                        }*/
                    }
                }
                break;
            default:
                break;
        }
    }

    public boolean victoire() {
        if ((this.valeurMax1 == 2048) || (this.valeurMax2 == 2048) || (this.valeurMax3 == 2048)) {
            System.out.println("Victoire ! Score max atteint :)");
            return false;
        }
        return true;
    }

    public boolean defaite() {
        if (listeCasesVides().isEmpty()) {
            System.out.println("Perdu ! Plus de cases libres :(");
            return false;
        }
        return true;
    }

    public void partie() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            genererCaseRandom();
        }
        System.out.println(this.toString());

        while ((defaite()) || (victoire())) {
            System.out.println("\nDéplacement demandé : \n");
            System.out.println("z : HAUT");
            System.out.println("s : BAS");
            System.out.println("q : GAUCHE");
            System.out.println("d : DROITE\n");

            String nextMove = sc.nextLine();
            if ((nextMove.equals("z")) || (nextMove.equals("Z")) || (nextMove.equals("h")) || (nextMove.equals("H")) || (nextMove.equals("haut")) || (nextMove.equals("HAUT"))) {
                onMove(HAUT);
                genererCaseRandom();
                System.out.println(this.toString());
            } else if ((nextMove.equals("s")) || (nextMove.equals("S")) || (nextMove.equals("b")) || (nextMove.equals("B")) || (nextMove.equals("bas")) || (nextMove.equals("BAS"))) {
                onMove(BAS);
                genererCaseRandom();
                System.out.println(this.toString());
            } else if ((nextMove.equals("q")) || (nextMove.equals("Q")) || (nextMove.equals("g")) || (nextMove.equals("G")) || (nextMove.equals("gauche")) || (nextMove.equals("GAUCHE"))) {
                onMove(GAUCHE);
                genererCaseRandom();
                System.out.println(this.toString());
            } else if ((nextMove.equals("d")) || (nextMove.equals("D")) || (nextMove.equals("droite")) || (nextMove.equals("DROITE"))) {
                onMove(DROITE);
                genererCaseRandom();
                System.out.println(this.toString());
            } else {
                System.out.println("\nJe n'ai pas compris votre demande...\n");
            }

        }
    }
}

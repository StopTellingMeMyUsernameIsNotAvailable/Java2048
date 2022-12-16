/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
    
    public ArrayList listeCasesVides(){
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
    
    public void fUUUSIOOON(Case c){
        
        //grille 1
        for(Case toutes : grilleUne){
            if((toutes.equals(c)) && (toutes.valeurEgale(c))){
                c.setValeur(toutes.getValeur() * 2);
            }
        }
        
        //grille 2
        for(Case toutes : grilleDeux){
            if(toutes.equals(c) && toutes.valeurEgale(c)){
                c.setValeur(toutes.getValeur() * 2);
                toutes.setValeur(0);
            }
        }
        
        //grille 3
        for(Case toutes : grilleTrois){
            if(toutes.equals(c) && toutes.valeurEgale(c)){
                c.setValeur(toutes.getValeur() * 2);
                toutes.setValeur(0);
            }
        }
    }
    
    public ArrayList bordsGrille(int dir){
        ArrayList<Case> listeBords = new ArrayList<>();
        
        //grille 1
        if(grilleUne.isEmpty()){
            System.out.println("non1");
        }else{
            switch(dir){
                case HAUT:
                    for(Case a : grilleUne){
                        if(a.getX() == 0){
                            listeBords.add(a);
                        }
                    }
                    break;
                case BAS:
                    for(Case a : grilleUne){
                        if(a.getX() == TAILLE - 1){
                            listeBords.add(a);
                        }
                    }
                    break;
                case GAUCHE:
                    for(Case a : grilleUne){
                        if(a.getY() == 0){
                            listeBords.add(a);
                        }
                    }
                    break;
                default:
                    for(Case a : grilleUne){
                        if(a.getY() == TAILLE - 1){
                            listeBords.add(a);
                        }
                    }
                    break;
            }
        }
        
        //grille 2
        if(grilleDeux.isEmpty()){
            System.out.println("non2");
        }else{
            switch(dir){
                case HAUT:
                    for(Case b : grilleDeux){
                        if(b.getX() == 0){
                            listeBords.add(b);
                        }
                    }
                    break;
                case BAS:
                    for(Case b : grilleDeux){
                        if(b.getX() == TAILLE - 1){
                            listeBords.add(b);
                        }
                    }
                    break;
                case GAUCHE:
                    for(Case b : grilleDeux){
                        if(b.getY() == 0){
                            listeBords.add(b);
                        }
                    }
                    break;
                default:
                    for(Case b : grilleDeux){
                        if(b.getY() == TAILLE - 1){
                            listeBords.add(b);
                        }
                    }
                    break;
            }
        }
        
        //grille 3
        if(grilleTrois.isEmpty()){
            System.out.println("non3");
        }else{
            switch(dir){
                case HAUT:
                    for(Case c : grilleTrois){
                        if(c.getX() == 0){
                            listeBords.add(c);
                        }
                    }
                    break;
                case BAS:
                    for(Case c : grilleTrois){
                        if(c.getX() == TAILLE - 1){
                            listeBords.add(c);
                        }
                    }
                    break;
                case GAUCHE:
                    for(Case c : grilleTrois){
                        if(c.getY() == 0){
                            listeBords.add(c);
                        }
                    }
                    break;
                default:
                    for(Case c : grilleTrois){
                        if(c.getY() == TAILLE - 1){
                            listeBords.add(c);
                        }
                    }
                    break;
            }
        }
        
        /*for(Case d : listeBords){
            System.out.println(d.toString());
        }*/
        return listeBords;
    }
    
    public void onMove(int dir){
        switch(dir){
            case HAUT:
                for(Case toutes : grilleUne){
                    if(!bordsGrille(HAUT).contains(toutes)){
                        System.out.println(toutes.toString());
                        for(Case onTente : grilleUne){
                            if(onTente.getY() == toutes.getY()){
                                toutes.setX(onTente.getX() + 1);
                            }
                        }
                        toutes.setX(0);
                        System.out.println(toutes.toString());
                        fUUUSIOOON(toutes);
                    }
                }
                break;
            case BAS:
                for(Case toutes : grilleUne){
                    if(!bordsGrille(BAS).contains(toutes)){
                        System.out.println(toutes.toString()+"\n");
                        toutes.setX(3);
                        System.out.println(toutes.toString());
                        fUUUSIOOON(toutes);
                    }
                }
                break;
            case GAUCHE:
                for(Case toutes : grilleUne){
                    if(!bordsGrille(GAUCHE).contains(toutes)){
                        System.out.println(toutes.toString()+"\n");
                        toutes.setY(0);
                        System.out.println(toutes.toString());
                        fUUUSIOOON(toutes);
                    }
                }
                break;
            case DROITE:
                for(Case toutes : grilleUne){
                    if(!bordsGrille(DROITE).contains(toutes)){
                        System.out.println(toutes.toString()+"\n");
                        toutes.setY(3);
                        System.out.println(toutes.toString());
                        fUUUSIOOON(toutes);
                    }
                }
                break;
            default:
                break;
        }
    }
}

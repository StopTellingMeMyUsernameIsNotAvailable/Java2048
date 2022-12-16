/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu2048;

/**
 *
 * @author Solene
 */
public class Main implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Case c = new Case(0,0,2,1);
        //System.out.println(c.toString());
        //System.out.println(g.toString());
        /*g.genererCaseRandom();
        System.out.println(g.toString());*/
        /*g.bordsGrille(HAUT);
        System.out.println("\n");
        g.bordsGrille(BAS);
        System.out.println("\n");
        g.bordsGrille(GAUCHE);
        System.out.println("\n");
        g.bordsGrille(DROITE);*/

        /*g.onMove(HAUT);
        System.out.println(g.toString());
        g.onMove(BAS);
        System.out.println(g.toString());
        g.onMove(GAUCHE);
        System.out.println(g.toString());
        g.onMove(DROITE);
        System.out.println(g.toString());*/
        /*for(Case a : g.getGrilleDeux()){
            System.out.println(a.toString());
            a.voisin(HAUT, g.getGrilleDeux());
        }*/
        /*g.onMove(HAUT);
        System.out.println(g.toString());
        g.onMove(BAS);
        System.out.println(g.toString());
        g.onMove(GAUCHE);
        System.out.println(g.toString());
        g.onMove(DROITE);
        System.out.println(g.toString());*/
        
        
        Grille g = new Grille();
        
        g.partie();

    }

}

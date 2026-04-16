package com.example.java_natif;

import android.graphics.Color;

public class Personnage {
    // Static
//    static String nom;
//    static int vita;
//    static int force;
//    static int mana;

    // Public
//    public String nom;
//    public int vita;
//    public int force;
//    public int mana;

    // Private
    private String nom;
    private int vita;
    private int force;
    private int mana;
    public Personnage() {
        nom = "toto";
        vita = 100;
        force = 87;
        mana = 78;
    }
//    public Personnage(String nom, int vita, int force, int mana) {
//        nom = nom;
//        vita = vita;
//        force = force;
//        mana = mana;
//    }

    public Personnage(String nom, int vita, int force, int mana) {
        this.nom = nom;
        this.vita = vita;
        this.force = force;
        this.mana = mana;
    }

    public Personnage(String nom) {
        this.nom = nom;
        this.vita = 80;
        this.force = 50;
        this.mana = 50;
    }

    public void pertePv() { this.vita -= 10; }
    public void pertePv(int damage) { this.vita -= damage; }
    public void pertePv(MegaStats ms) {
        this.vita -= 10;
        ms.augmenteForce(1000);
        ms.augmenteMana(1000);
        ms.augmenteVie(1000);
        ms.changeNom("Mega");
        ms.changeCouleur(Color.YELLOW);
    }

    public String getNom() { return nom;  }
    public int getVita() { return vita; }
    public int getForce() { return force; }
    public int getMana() { return mana; }
    public void setVita(int vita) { this.vita = vita; }
    public void setNom(String nom) { this.nom = nom; }
    public void setForce(int force) { this.force = force; }
    public void setMana(int mana) { this.mana = mana; }


    /*
    Personnage
    magicien


    list action:
    - attaquer
    - regen

     */

    /*

    class DB

    db.select(...)
    db.update(...)

     */

    /*

        interface show {
            print(String s)
        }

        class Console implements show {
            @override
            print(String s) {
                // my specific print ex java print or Log on android
            }
        }

        var custom_print = new Console()
        custom_print.print("arstarst")

        // grace a ca, peut importe l'implementation, le reste de mon code n'est pas impacter,
        on peut remplacer Console par une autre methode

     */

    /*



    windows:
    - home
    - products
    - account

    public changerWindow(IFenetre fenetre_actuel, IFenetre fenetre_cible) {
        switch (f.type) {
            case "home" -> fenetre_actuel.goTo(fenetre_cible);
            case "products" -> fenetre_actuel.goTo(fenetre_cible);
            case "accounts" -> fenetre_actuel.goTo(fenetre_cible);
        }
    }

    interface IFenetre {
        void goto();
    }

    // ca peut fonctionner avec une class aussi

     */

    public interface MegaStats {
        public void augmenteForce(int f);
        public void augmenteVie(int v);
        public void augmenteMana(int m);
        public void changeNom(String s);
        public void changeCouleur(int c);
    }
}

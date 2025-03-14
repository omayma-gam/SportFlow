package com.example.sportflow.Model;

import java.security.Timestamp;
import java.util.Date;

public class Membre {
    private int id;
    private String nom;
    private String SportPratique;
    private Date naissance;

    public Membre(int id, String nom, String sportPratique, Date naissance) {
        this.id = id;
        this.nom = nom;
        SportPratique = sportPratique;
        this.naissance = naissance;
    }

    public Membre(String nom,  Date naissance,String sportPratique) {
        this.nom = nom;
        this.SportPratique = sportPratique;
        this.naissance = naissance;
    }



    public Membre(int id, String nom, Date naissance, String sportpratique) {
        this.id = id;
        this.nom = nom;
        this.naissance = naissance;
        this.SportPratique = sportpratique;


    }


    public Membre(int id, String nom, Timestamp naissance, String sportpratique) {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSportPratique() {
        return SportPratique;
    }

    public void setSportPratique(String sportPratique) {
        SportPratique = sportPratique;
    }

    public java.sql.Date getNaissance() {
        return (java.sql.Date) naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
}

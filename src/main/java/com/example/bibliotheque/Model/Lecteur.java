package com.example.bibliotheque.Model;

import java.sql.Date;

public class Lecteur {
    int numero,pretActuel,nombrePret;
    String nom;
    String prenom;
    String email;
    String telephone;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    String adresse;
    Date naissance;

    public Lecteur(int numero, String nom, String prenom, Date naissance, String adresse, String email, String telephone) {
        this.numero = numero;
        this.pretActuel = 0;
        this.nombrePret = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.naissance = naissance;
        this.adresse = adresse;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPretActuel() {
        return pretActuel;
    }

    public void setPretActuel(int pretActuel) {
        this.pretActuel = pretActuel;
    }

    public int getNombrePret() {
        return nombrePret;
    }

    public void setNombrePret(int nombrePret) {
        this.nombrePret = nombrePret;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
}

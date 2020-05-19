/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Asus
 */
public class Sprint {
    private int id;
    private  String desc;
    private  String date1;
    private  String date2;
    private  int nbr;
    private  String projet;
    private  String equipe;
    private  int id_bs;

    public Sprint() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public int getId_bs() {
        return id_bs;
    }

    public void setId_bs(int id_bs) {
        this.id_bs = id_bs;
    }

    @Override
    public String toString() {
        return "Sprint{" + "id=" + id + ", desc=" + desc + ", date1=" + date1 + ", date2=" + date2 + ", nbr=" + nbr + ", projet=" + projet + ", equipe=" + equipe + ", id_bs=" + id_bs + '}';
    }

    public Sprint(int id, String desc, String date1, String date2, int nbr, String projet, String equipe, int id_bs) {
        this.id = id;
        this.desc = desc;
        this.date1 = date1;
        this.date2 = date2;
        this.nbr = nbr;
        this.projet = projet;
        this.equipe = equipe;
        this.id_bs = id_bs;
    }
    
    
    
    
    
}

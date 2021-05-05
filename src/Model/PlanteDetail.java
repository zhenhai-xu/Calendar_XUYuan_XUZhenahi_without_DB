package Model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class PlanteDetail implements Serializable {

    private String image = null;
    private String nom;
    private Map<String,String> date;
    private String note;
    private ArrayList<String> imagesList = new ArrayList<String>(100);
    private ArrayList<Double> ph = new ArrayList<Double>(100);
    private ArrayList<Double> hauteur = new ArrayList<Double>(100);
    private ArrayList<String> notes = new ArrayList<String>(100);
    private ArrayList<String> dateSuivi = new ArrayList<String>(100);
    private int index=0;
    public PlanteDetail(String nom, Map date, String note){
        this.nom=nom;
        this.date =date;
        this.note=note;
    }

    public void setImage(String image) {
        this.image=image;
    }


    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public Map getDate() {
        return date;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Map<String, String> date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<String> getImagesList() {
        return imagesList;
    }

    public void addImagesList(String image) {
        this.imagesList.add(image);
    }

    public ArrayList<Double> getPh() {
        return ph;
    }

    public void addPh(double ph) {
        this.ph.add(ph);
    }

    public ArrayList<Double> getHauteur() {
        return hauteur;
    }

    public void addHauteur(double hauteur) {
        this.hauteur.add(hauteur);
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void addNotes(String notes) {
        this.notes.add(notes);
    }

    public ArrayList<String> getDateSuivi() {
        return dateSuivi;
    }

    public void addDateSuivi(String dateSuivi) {
        this.dateSuivi.add(dateSuivi);
    }
    public int getIndex(){
        return this.index;
    }
    public void addIndex(){
        this.index++;
    }
    public void setHauteur(int i,double h){
        this.hauteur.set(i,h);
    }
    public void setImagesList(int i,String image){
        this.imagesList.set(i,image);
    }
    public void setDateSuivi(int i,String d){
        this.dateSuivi.set(i,d);
    }
    public void setPh(int i,double p){
        this.ph.set(i,p);
    }
    public void setNotes(int i,String n){
        this.notes.set(i,n);
    }



}

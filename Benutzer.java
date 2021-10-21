package com.company;

import java.util.List;

public class Benutzer {
    private String vorName;
    private String nachName;
    private List<Sport> sport;

    /**
     * Vorname Setter
     * @param vorName Vorname
     */
    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    /**
     * Nachname Setter
     * @param nachName Nachname
     */
    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    /**
     * Sportliste Setter
     * @param sport Sportliste
     */
    public void setSport(List<Sport> sport) {
        this.sport = sport;
    }

    /**
     * Vorname Getter
     * @return Vorname
     */
    public String getVorName() {
        return vorName;
    }

    /**
     * Nachname Getter
     * @return Nachname
     */
    public String getNachName() {
        return nachName;
    }

    /**
     * Sportliste Getter
     * @return Sportliste
     */
    public List<Sport> getSport() {
        return sport;
    }

    /**
     * Konstruktor
     * @param vorName Vorname
     * @param nachName Nachname
     * @param sport Sportliste
     */
    public Benutzer(String vorName, String nachName,List<Sport> sport) {
        this.vorName = vorName;
        this.nachName = nachName;
        this.sport = sport;
    }

    /**
     * Berechnet die Zeit die man braucht, um alle Sporte zu treiben
     * @return Summe der Zeiten aller Sporte
     */
    public double kalkuliereZeit(){
        double summe=0;
        for (Sport i: sport){
            summe+=i.kalkuliereZeit();
        }
        return summe;
    }

    /**
     * Gibt die zeit eines Sportes zurruck
     * @param sport Sport
     * @return benotigte Zeit des Sportes
     */
    public double kalkuliereZeit(Sport sport){
        return sport.kalkuliereZeit();
    }

    /**
     * Berchnet die durchnittliche Zeit aller Sporte
     * @return durchnittliche Zeit
     */
    public double kalkuliereDurchschnittszeit(){
        return kalkuliereZeit()/sport.size();
    }
}

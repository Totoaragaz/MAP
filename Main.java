package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Sport basketball=new Basketball();
        Sport fussball=new Fussball();
        Sport hindernislauf=new Hindernislauf();
        Sport hochsprung=new Hochsprung();
        List<Sport> sportList1=new ArrayList<Sport>();
        sportList1.add(basketball);
        sportList1.add(fussball);
        sportList1.add(hindernislauf);
        sportList1.add(hochsprung);
        Benutzer benutzer=new Benutzer("Dwayne","Johnson",sportList1);
        System.out.println(benutzer.getVorName() + " " + benutzer.getNachName());
        System.out.println("Alle Sporte: " + benutzer.kalkuliereZeit());
        System.out.println("Basketball: " + benutzer.kalkuliereZeit(basketball));
        System.out.println("Fussball: " + benutzer.kalkuliereZeit(fussball));
        System.out.println("Hindernislauf: " + benutzer.kalkuliereZeit(hindernislauf));
        System.out.println("Hochsprung: " + benutzer.kalkuliereZeit(hochsprung));
        System.out.println("Durchschnitt: " + benutzer.kalkuliereDurchschnittszeit());
        System.out.println();

        benutzer.setVorName("The");
        benutzer.setNachName("Rock");
        List<Sport> sportList2=new ArrayList<Sport>();
        sportList2.add(basketball);
        sportList2.add(hochsprung);
        benutzer.setSport(sportList2);
        System.out.println(benutzer.getVorName() + " " + benutzer.getNachName());
        System.out.println("Alle Sporte: " + benutzer.kalkuliereZeit());
        System.out.println("Durchschnitt: " + benutzer.kalkuliereDurchschnittszeit());
    }
}

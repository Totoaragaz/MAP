package com.company;

public class Aufgabe1 {
    private int[] Noten;

    public void setNoten(int[] noten) {
        Noten = noten;
    }

    public int[] getNoten() {
        return Noten;
    }

    public Aufgabe1(int[] noten) {
        Noten = noten;
    }

    /**
     * Gibt alle nicht ausreichende Noten zurruck
     * @return Array mit nicht ausreichende Noten
     */
    public int[] nicht_ausreichend(){
        int[] nicht_ausreichend=new int[Noten.length];
        int j=0;
        for (int i: this.Noten) {
            if (i<40){
                nicht_ausreichend[j]=i;
                j++;
            }
        }
        return nicht_ausreichend;
    }

    /**
     * Berechnet den Durchschnitt aller Noten
     * @return  Durchschnitt der Noten
     */
    public double durchschnitt(){
        double summe=0;
        for (int i: this.Noten) {
            summe+=i;
        }
        return summe/Noten.length;
    }

    /**
     * Rundet die Noten ab
     * @return Ein array mit abgerundete Noten
     */
    public int[] abgerundete_Noten(){
        int[] neue_Noten=new int[Noten.length];
        for (int i=0;i<Noten.length;i++) {
            int nachste_Note=5;
            while(nachste_Note<Noten[i])
                nachste_Note+=10;
            if (Noten[i]+3>nachste_Note) {
                neue_Noten[i]=nachste_Note;
            }
            else neue_Noten[i]=Noten[i];
        }
        return neue_Noten;
    }

    /**
     *Rundet die Noten ab und gibt zurruck die grosste abgerundete Note
     * @return grosste abgerundete Note
     */
    public int maximale_abgerundete_Note(){
        int maximale_abgerundete_Note=0;
        for (int i=0;i<Noten.length;i++) {
            int nachste_Note=5;
            while(nachste_Note<Noten[i])
                nachste_Note+=10;
            if (Noten[i]+3>nachste_Note && maximale_abgerundete_Note<nachste_Note) maximale_abgerundete_Note=nachste_Note;
        }
        return maximale_abgerundete_Note;
    }
}

package com.company;

public class Aufgabe2 {
    private int[] array;

    /**
     * Findet die grosste Zahl im array
     * @return die grosste Zahl im array
     */
    public int maximale_Zahl(){
        int maximale_Zahl=array[0];
        for (int i:this.array){
            if (maximale_Zahl<i) maximale_Zahl=i;
        }
        return maximale_Zahl;
    }

    /**
     * Findet die kleinste Zahl im array
     * @return die kleinste Zahl im array
     */
    public int minimale_Zahl(){
        int minimale_Zahl=array[0];
        for (int i:this.array){
            if (minimale_Zahl>i) minimale_Zahl=i;
        }
        return minimale_Zahl;
    }

    /**
     * Berechnet die maximale Summe von n-1 Zahlen
     * @return die maximale Summe von n-1 Zahlen
     */
    public int maximale_Summe(){
        int s=0;
        for (int i: this.array){
            s+=i;
        }
        return s-this.minimale_Zahl();
    }

    public void setA(int[] array) {
        this.array = array;
    }

    public int[] getA() {
        return array;
    }

    public Aufgabe2(int[] array) {
        this.array = array;
    }

    /**
     * Berechnet die minimale Summe von n-1 Zahlen
     * @return die minimale Summe von n-1 Zahlen
     */
    public int minimale_Summe(){
        int s=0;
        for (int i: this.array){
            s+=i;
        }
        return s-this.maximale_Zahl();
    }
}

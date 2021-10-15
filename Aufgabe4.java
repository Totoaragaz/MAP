package com.company;

public class Aufgabe4 {
    private int[] tastatur;
    private int[] usb;
    private int budget;

    public void setTastatur(int[] tastatur) {
        this.tastatur = tastatur;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void setUsb(int[] usb) {
        this.usb = usb;
    }

    public int[] getTastatur() {
        return tastatur;
    }

    public int[] getUsb() {
        return usb;
    }

    public Aufgabe4(int[] tastatur, int[] usb,int budget) {
        this.tastatur = tastatur;
        this.usb = usb;
        this.budget= budget;
    }

    /**
     * Findet die billigste Tastatur
     * @return die billigste Tastatur
     */
    public int billigste_Tastatur(){
        int billigste=tastatur[0];
        for (int i:this.tastatur){
            if (billigste>i) billigste=i;
        }
        return billigste;
    }

    /**
     * Findet den teuersten Gegenstand
     * @return den teuersten Gegenstand
     */
    public int teuersten_Gegenstand(){
        int teuerste=tastatur[0];
        for (int i:this.tastatur){
            if (teuerste<i) teuerste=i;
        }
        for (int i:this.usb){
            if (teuerste<i) teuerste=i;
        }
        return teuerste;
    }

    /**
     * Findet das teuerste USB Laufwerk, das Markus kaufen kann
     * @return das teureste Usb Laufwerk, das Markus kaufen kann
     */
    public int teureste_USB_in_budget(){
        int teuerste=0;
        for (int i:this.usb){
            if (teuerste<i && i<=this.budget) teuerste=i;
        }
        return teuerste;
    }

    /**
     * Findet den maximalen Geldbetrag, den Markus spenden kann.
     * @return maximaler Geldbetrag
     */
    public int maximaler_Geldbetrag(){
        int maximaler_Geldbetrag=-1;
        for (int i:this.tastatur){
            for (int j:this.usb){
                if (i+j>maximaler_Geldbetrag && i+j<=this.budget) maximaler_Geldbetrag=i+j;
            }
        }
        return maximaler_Geldbetrag;
    }
}

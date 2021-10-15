package com.company;

public class Aufgabe3 {
    private int[] array1;
    private int[] array2;

    public Aufgabe3(int[] array1, int[] array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    public int[] getArray1() {
        return array1;
    }

    public int[] getArray2() {
        return array2;
    }

    public void setArray1(int[] array1) {
        this.array1 = array1;
    }

    public void setArray2(int[] array2) {
        this.array2 = array2;
    }

    /**
     * Berechnet die Summe der zwei Arrays
     * @return Summe der zwei Arrays
     */
    public int[] Summe(){
        int[] summe=new int[array1.length+1];
        int carry=0;
        for (int i=0;i< array1.length;i++) summe[i]=0;
        for (int i= array1.length-1;i>=0;i--){
            summe[i+1]= array1[i]+array2[i]+carry;
            carry=0;
            if (summe[i+1]>9){
                summe[i+1]-=10;
                carry=1;
            }
        }
        if (carry==1) summe[0]=1;
        if (summe[0]==0){
            int[] neue_summe=new int[array1.length];
            for (int i=0;i< array1.length;i++){
                neue_summe[i]=summe[i+1];
            }
            return neue_summe;
        }
        else return summe;
    }

    /**
     * Andert den Array sodass man die Differenz rechnen kann
     * @param array Array
     * @param carry_position die position im array, wo man den carry braucht
     * @return
     */
    public int[] carry(int[] array,int carry_position){
        array[carry_position]+=10;
        carry_position--;
        while (array[carry_position]==0){
            array[carry_position]+=9;
            carry_position--;
        }
        array[carry_position]--;
        return array;
    }

    /**
     * Berechnet die Differenz der zwei Arrays
     * @return die Differenz der zwei Arrays
     */
    public int[] Differenz(){
        int[] differenz=new int[array1.length];
        for (int i= array1.length-1;i>=0;i--) {
            if (array1[i]<array2[i])
                array1=carry(array1,i);
            differenz[i] = array1[i] - array2[i];
        }
        int i;
        for (i=0;i< array1.length;i++){
            if (differenz[i]!=0) break;
        }
        if (i>0) {
            int[] neue_differenz = new int[array1.length - i];
            for (int j=0;i< array1.length-i;j++){
                neue_differenz[j]=differenz[j+i];
            }
            return neue_differenz;
        }
        else return differenz;
    }

    /**
     * Berechnet den Produkt des erstes Array mit eine Ziffer
     * @param ziffer Ziffer
     * @return den Produkt des erstes Array mit eine Ziffer
     */
    public int[] Produkt(int ziffer){
        int[] produkt=new int[array1.length+1];
        int carry=0;
        for (int i= array1.length-1;i>=0;i--) {
            produkt[i+1]=array1[i]*ziffer+carry;
            carry=0;
            if (produkt[i+1]>9) {
                for (int j = 1; j < 10; j++) {
                    produkt[i+1] -= 10;
                    carry++;
                    if (produkt[i+1] < 10) break;
                }
            }
        }
        produkt[0]=carry;
        if (produkt[0]==0){
            int[] neue_produkt=new int[array1.length];
            for (int i=0;i< array1.length;i++){
                neue_produkt[i]=produkt[i+1];
            }
            return neue_produkt;
        }
        else return produkt;
    }

    /**
     * Berechnet der Quotient des Divisions des Arrays mit eine Ziffer
     * @param ziffer Ziffer
     * @return der Quotient des Arrays mit eine Ziffer
     */
    public int[] Quotient(int ziffer){
        int[] quotient=new int[array1.length];
        int carry=0;
        for (int i=0;i< array1.length;i++) {
            if (array1[i]+10*carry>=ziffer){
                quotient[i]=(array1[i]+10*carry)/ziffer;
                carry=(array1[i]+10*carry)%ziffer;
            }
            else carry=carry*10+array1[i];
        }
        if (quotient[0]==0){
            int[] newq=new int[array1.length-1];
            for (int i=0;i< array1.length-1;i++){
                newq[i]=quotient[i+1];
            }
            return newq;
        }
        else return quotient;
    }
}

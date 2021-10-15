package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Aufgabe 1");
        System.out.println();
        int[] Noten={13,80,43,67,39,87,10,84,29,100};
        Aufgabe1 a1=new Aufgabe1(Noten);
        int[] nicht_ausreichnde_Noten=a1.nicht_ausreichend();
        for (int i: nicht_ausreichnde_Noten) {
            if (i!=0) System.out.println(i);
        }

        System.out.println();

        System.out.println(a1.durchschnitt());

        System.out.println();

        int[] abgerundete_Noten=a1.abgerundete_Noten();
        for (int i: abgerundete_Noten) {
            System.out.println(i);
        }

        System.out.println();
        System.out.println(a1.maximale_abgerundete_Note());
        System.out.println();

        System.out.println("Aufgabe 2");
        System.out.println();

        int[] array1={4,8,3,10,17};
        Aufgabe2 a2=new Aufgabe2(array1);
        System.out.println(a2.maximale_Zahl());
        System.out.println();

        System.out.println(a2.minimale_Zahl());
        System.out.println();

        System.out.println(a2.maximale_Summe());
        System.out.println();

        System.out.println(a2.minimale_Summe());
        System.out.println();

        System.out.println("Aufgabe 3");
        System.out.println();

        int[] x1={1,3,0,0,0,0,0,0,0};
        int[] x2={8,7,0,0,0,0,0,0,0};
        Aufgabe3 a3=new Aufgabe3(x1,x2);
        int[] Summe=a3.Summe();
        for (int i: Summe) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println();

        int[] x3={8,3,0,0,0,0,0,0,0};
        int[] x4={5,4,0,0,0,0,0,0,0};
        a3.setArray1(x3);
        a3.setArray2(x4);
        int[] Differenz=a3.Differenz();
        for (int i: Differenz) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println();

        int[] x5={2,0,0,0,0,0,0,0,0};
        a3.setArray1(x5);
        int[] Produkt=a3.Produkt(9);
        for (int i: Produkt) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println();

        int[] Quotient=a3.Quotient(2);
        for (int i: Quotient) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println();

        System.out.println("Aufgabe 4");
        System.out.println();

        int[] tastaturen1={40,35,70,15,15};
        int[] usb1={20,15,40,15};
        Aufgabe4 a4=new Aufgabe4(tastaturen1,usb1,30);
        System.out.println(a4.billigste_Tastatur());
        System.out.println();

        int[] tastaturen2={15,20,10,35};
        a4.setTastatur(tastaturen2);
        System.out.println(a4.teuersten_Gegenstand());
        System.out.println();

        int[] usb2={15,45,20};
        a4.setUsb(usb2);
        System.out.println(a4.teureste_USB_in_budget());
        System.out.println();

        a4.setBudget(60);
        int[] tastaturen3={60};
        int[] usb3={8,12};
        a4.setTastatur(tastaturen3);
        a4.setUsb(usb3);
        System.out.println(a4.maximaler_Geldbetrag());
    }
}

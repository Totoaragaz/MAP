package com.company;
import repository.Kontroller;

import java.util.Scanner;

/**
 * UI class
 */
public class UI {

    private Scanner keyboard = new Scanner(System.in);

    public UI() {
    }

    /**
     * clears the console screen
     */
    public void clearScreen(){
        for (int i=1;i<=10;i++)
        System.out.println();
    }

    /**
     * waits for user input
     */
    public void waitForInput(){
        System.out.println("Press Enter to continue.");
        keyboard.nextLine();
        keyboard.nextLine();
    }

    /**
     * Main Menu Screen
     * gives commands to the controller
     */
    public void mainMenu (){
        Kontroller kontroller=new Kontroller();
        kontroller.getData();
        while(true){
            clearScreen();
            System.out.println("Bitte geben Sie eine Ziffer ein");
            System.out.println("1.Alle Studenten anzeigen");
            System.out.println("2.Alle Lehrer anzeigen");
            System.out.println("3.Alle Kursen anzeigen");
            System.out.println("4.Neuer Student einfuhren");
            System.out.println("5.Neuer Lehrer einfuhren");
            System.out.println("6.Neuer Kurs einfuhren");
            System.out.println("7.Student loschen");
            System.out.println("8.Lehrer loschen");
            System.out.println("9.Kurs loschen");
            System.out.println("10.Alle Kursen mit freien Platzen");
            System.out.println("11.Student fur Kurs anmelden");
            System.out.println("12.Studenten die an einem Kurs angemeldet sind");
            System.out.println("13.Studenten nach Kreditanzahl sortieren");
            System.out.println("14.Kursen nach Kreditanzahl sortieren");
            System.out.println("15.Studenten mit mindestens 60 Kredite anzeigen");
            System.out.println("16.Kursen mit mindestens 6 Kredite anzeigen");
            System.out.println("0.Exit");
            int input=keyboard.nextInt();
            clearScreen();
            if (input==1){
                kontroller.printAllStudents();
            }
            else if (input==2){
                kontroller.printAllTeachers();
            }
            else if (input==3){
                kontroller.printAllCourses();
            }
            else if (input==4){
                kontroller.addStudent();
            }
            else if (input==5){
                kontroller.addTeacher();
            }
            else if (input==6){
                kontroller.addCourse();
            }
            else if (input==7){
                kontroller.deleteStudent();
            }
            else if (input==8){
                kontroller.deleteTeacher();
            }
            else if (input==9){
                kontroller.deleteCourse();
            }
            else if (input==10){
                kontroller.coursesWithFreePlaces();
            }
            else if (input==11){
                kontroller.registerStudent();
            }
            else if (input==12){
                kontroller.courseStudents();
            }
            else if (input==13){
                kontroller.sortStudents();
            }
            else if (input==14){
                kontroller.sortCourses();
            }
            else if (input==15){
                kontroller.filterStudents();
            }
            else if (input==16){
                kontroller.filterCourses();
            }
            else if (input==0) {
                System.out.println("Save Changes? [Y/N]");
                keyboard.nextLine();
                String text=keyboard.nextLine();
                if (text.equals("Y") || text.equals("y")) {
                    kontroller.saveChanges();
                    System.out.println("Changes saved.");
                }
                break;
            }
           if (input>0 && input<17) waitForInput();
        }
    }
}

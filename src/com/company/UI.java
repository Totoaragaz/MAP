package com.company;
import repository.Kontroller;

import java.util.Scanner;

/**
 * UI class
 */
public class UI {

    private final Scanner keyboard = new Scanner(System.in);

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
            System.out.println("4.Neuer Student einfugen");
            System.out.println("5.Neuer Lehrer einfugen");
            System.out.println("6.Neuer Kurs einfugen");
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
                System.out.println("Vorname:");
                String vorname= keyboard.nextLine();
                System.out.println("Nachname:");
                String nachname= keyboard.nextLine();
                System.out.println("StudentId:");
                long id= keyboard.nextLong();
                System.out.println("Kredite:");
                int credits= keyboard.nextInt();
                System.out.println("Kursen:");
                keyboard.nextLine();
                String kursListe= keyboard.nextLine();
                kontroller.addStudent(vorname,nachname,id,credits,kursListe);
            }
            else if (input==5){
                System.out.println("Vorname:");
                String vorname= keyboard.nextLine();
                System.out.println("Nachname:");
                String nachname= keyboard.nextLine();
                System.out.println("TeacherId:");
                long id= keyboard.nextLong();
                System.out.println("Kursen:");
                keyboard.nextLine();
                String kursListe= keyboard.nextLine();
                kontroller.addTeacher(vorname,nachname,id,kursListe);
            }
            else if (input==6){
                System.out.println("Name:");
                String name= keyboard.nextLine();
                System.out.println("Teacher (ID):");
                long teacherid=keyboard.nextLong();
                System.out.println("Max Enrollment:");
                int maxEnrollment= keyboard.nextInt();
                System.out.println("Kredite:");
                int credits= keyboard.nextInt();
                System.out.println("Enrolled Students (ID):");
                keyboard.nextLine();
                String studentListe= keyboard.nextLine();
                kontroller.addCourse(name,teacherid,maxEnrollment,credits,studentListe);
            }
            else if (input==7){
                System.out.println("StudentId:");
                long id= keyboard.nextLong();
                kontroller.deleteStudent(id);
            }
            else if (input==8){
                System.out.println("TeacherId:");
                long id= keyboard.nextLong();
                kontroller.deleteTeacher(id);
            }
            else if (input==9){
                System.out.println("Kurs Name:");
                String name= keyboard.nextLine();
                kontroller.deleteCourse(name);
            }
            else if (input==10){
                kontroller.coursesWithFreePlaces();
            }
            else if (input==11){
                System.out.println("Kurs Name:");
                String kursName= keyboard.nextLine();
                System.out.println("StudentId:");
                long id= keyboard.nextLong();
                kontroller.registerStudent(kursName,id);
            }
            else if (input==12){
                System.out.println("Kurs Name:");
                String kursName= keyboard.nextLine();
                kontroller.courseStudents(kursName);
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

package UserInterface;
import kontroller.Kontroller;

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
        while(true){
            clearScreen();
            System.out.println("Bitte geben Sie eine Ziffer ein");
            System.out.println("1.Alle Studenten anzeigen");
            System.out.println("2.Alle Lehrer anzeigen");
            System.out.println("3.Alle Kursen anzeigen");
            System.out.println("4.Neuer Student einfügen");
            System.out.println("5.Neuer Lehrer einfügen");
            System.out.println("6.Neuer Kurs einfügen");
            System.out.println("7.Student löschen");
            System.out.println("8.Lehrer löschen");
            System.out.println("9.Kurs löschen");
            System.out.println("10.Alle Kursen mit freien Plätzen");
            System.out.println("11.Student fur Kurs anmelden");
            System.out.println("12.Studenten die an einem Kurs angemeldet sind");
            System.out.println("13.Studenten nach Kreditanzahl sortieren");
            System.out.println("14.Kursen nach Kreditanzahl sortieren");
            System.out.println("15.Studenten mit mindestens 20 Kredite anzeigen");
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
                keyboard.nextLine();
                String vorname= keyboard.nextLine();
                System.out.println("Nachname:");
                String nachname= keyboard.nextLine();
                System.out.println("StudentId:");
                long id= keyboard.nextLong();
                System.out.println("Kredite:");
                int credits= keyboard.nextInt();
                kontroller.addStudent(vorname,nachname,id,credits);
            }
            else if (input==5){
                System.out.println("Vorname:");
                keyboard.nextLine();
                String vorname= keyboard.nextLine();
                System.out.println("Nachname:");
                String nachname= keyboard.nextLine();
                System.out.println("TeacherId:");
                long id= keyboard.nextLong();
                kontroller.addTeacher(vorname,nachname,id);
            }
            else if (input==6){
                System.out.println("Name:");
                keyboard.nextLine();
                String name= keyboard.nextLine();
                System.out.println("KursId:");
                long courseId=keyboard.nextLong();
                System.out.println("TeacherId:");
                long teacherId=keyboard.nextLong();
                System.out.println("Max Enrollment:");
                int maxEnrollment= keyboard.nextInt();
                System.out.println("Kredite:");
                int credits= keyboard.nextInt();
                kontroller.addCourse(name,courseId,teacherId,maxEnrollment,credits);
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
                System.out.println("KursId:");
                long id= keyboard.nextLong();
                kontroller.deleteCourse(id);
            }
            else if (input==10){
                kontroller.coursesWithFreePlaces();
            }
            else if (input==11){
                System.out.println("StudentId:");
                long studentId= keyboard.nextLong();
                System.out.println("KursId:");
                long courseId= keyboard.nextLong();
                kontroller.registerStudent(studentId,courseId);
            }
            else if (input==12){
                System.out.println("KursId:");
                long courseId= keyboard.nextLong();
                kontroller.courseStudents(courseId);
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
                break;
            }
           if (input>0 && input<17) waitForInput();
        }
    }
}

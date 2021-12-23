package com.example.imgoinginsanehelp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kontroller.Kontroller;
import model.Course;
import model.Student;

import java.io.IOException;
import java.util.List;

public class StudentUIKontroller {

    private final JavaFXUIKontroller javaFXUIKontroller=new JavaFXUIKontroller();
    private final Kontroller kontroller=new Kontroller();

    private String firstName;
    private String lastName;
    private Student student;

    @FXML
    private ChoiceBox<Course> courseChoiceBox;

    @FXML
    private Label creditLabel;

    public StudentUIKontroller(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void updateCredits(){
        creditLabel.setText(""+student.getTotalCredits());
    }

    @FXML
    void initialize(){
        studentNameLabel.setText(firstName + " " + lastName);
        if (kontroller.findStudentByName(firstName,lastName)==null){
            long id= kontroller.firstFreeStudentId();
            kontroller.addStudent(firstName,lastName, id, 0);
            student= kontroller.findStudentById(id);
            updateCredits();
            try{
                showAlertBox();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            student=kontroller.findStudentByName(firstName,lastName);
            updateCredits();
        }
        List<Course> courseList=kontroller.getAllCourses();
        for (Course course:courseList){
            courseChoiceBox.getItems().add(course);
        }
    }

    @FXML
    private Label studentNameLabel;

    @FXML
    private Button studentSignOutButton;

    @FXML
    private Label enrollLabel;

    @FXML
    public void closeStudentWindow (){
        Stage stage=(Stage) studentSignOutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void showAlertBox () throws IOException {
        Stage stage=new Stage();
        AlertBoxKontroller alertBoxKontroller = new AlertBoxKontroller("Student was not found. A new account was created.");
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("AlertBox.fxml"));
        fxmlLoader.setController(alertBoxKontroller);
        Scene scene = new Scene(fxmlLoader.load(), 314,84);
        stage.setTitle("University Application");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @FXML
    public void showChoiceBoxOptions(){
        courseChoiceBox.getItems();
    }

    @FXML
    public void enrollForCourse(){
        if (courseChoiceBox.getSelectionModel().getSelectedItem()!=null) {
            int enrollValue = kontroller.registerStudent(student.getStudentId(), courseChoiceBox.getSelectionModel().getSelectedItem().getCourseId());
            if (enrollValue == 0) {
                enrollLabel.setText("An error occured.");
            } else if (enrollValue == 1) {
                enrollLabel.setText("You successfully enrolled for course.");
            } else if (enrollValue == 2) {
                enrollLabel.setText("Course is full.");
            } else if (enrollValue == 3) {
                enrollLabel.setText("You have too many credits.");
            } else {
                enrollLabel.setText("You are already enrolled for that course.");
            }
            student.setTotalCredits(student.getTotalCredits() + courseChoiceBox.getSelectionModel().getSelectedItem().getCredits());
            updateCredits();
        }
    }
}

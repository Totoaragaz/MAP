package com.example.imgoinginsanehelp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import kontroller.Kontroller;
import model.Course;
import model.Student;
import model.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherUIKontroller {

    private final Kontroller kontroller=new Kontroller();

    private String firstName;
    private String lastName;
    private Teacher teacher;
    private List<Course> courseList;

    public TeacherUIKontroller(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * initializes the window
     */

    @FXML
    void initialize(){
        teacherNameLabel.setText(firstName + " " + lastName);
        if (kontroller.findTeacherByName(firstName,lastName)==null){
            try{
                showAlertBox();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            teacher=kontroller.findTeacherByName(firstName,lastName);
            courseList=new ArrayList<>();
            for (long courseId: teacher.getCourses()){
                courseList.add(kontroller.findCourseById(courseId));
            }
            for (Course course:courseList){
                courseChoiceBox.getItems().add(course.getName());
            }
        }
    }

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private Label teacherNameLabel;

    @FXML
    private Button teacherSignOutButton;

    /**
     * closes the window
     * @param event
     */

    @FXML
    void closeTeacherWindow(ActionEvent event) {
        Stage stage=(Stage) teacherSignOutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * shows an alertbox
     * @throws IOException
     */

    @FXML
    public void showAlertBox () throws IOException {
        Stage stage=new Stage();
        AlertBoxKontroller alertBoxKontroller = new AlertBoxKontroller("Teacher not found.");
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("AlertBox.fxml"));
        fxmlLoader.setController(alertBoxKontroller);
        Scene scene = new Scene(fxmlLoader.load(), 314,84);
        stage.setTitle("University Application");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    /**
     * shows the students for the selected course
     */

    @FXML
    public void showStudents(){
        studentListView.getItems().removeAll(studentListView.getItems());
        if (courseChoiceBox.getSelectionModel().getSelectedItem()!=null) {
            Course selectedCourse=new Course();
            for (Course course: courseList){
                if (course.getName().equals(courseChoiceBox.getSelectionModel().getSelectedItem())) {
                    selectedCourse=course;
                    break;
                }
            }
            List<Student> studentList=kontroller.courseStudents(selectedCourse.getCourseId());
            for (Student student: studentList){
                studentListView.getItems().add(student);
            }
        }

    }
}

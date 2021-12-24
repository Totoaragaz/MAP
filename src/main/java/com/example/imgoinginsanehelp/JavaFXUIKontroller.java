package com.example.imgoinginsanehelp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kontroller.Kontroller;

import java.io.IOException;

public class JavaFXUIKontroller {

    @FXML
    private Button studentButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button logInBackButton;

    /**
     * shows the main menu
     * @throws IOException
     */

    @FXML
    public void showMainMenu () throws IOException {
        Stage stage=(Stage) logInBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("University Application");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showLogIn () throws IOException {
        Stage stage=(Stage) studentButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("LogIn.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            stage.setTitle("University Application");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    /**
     * opens the student log in screen
     */

    public void studentLogIn (){
        Stage stage=(Stage) studentButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("StudentLogIn.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            stage.setTitle("University Application");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * opens the teacher log in screen
     */

    public void teacherLogIn (){
        Stage stage=(Stage) studentButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("TeacherLogIn.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            stage.setTitle("University Application");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * signs in as a student
     * @throws IOException
     */

    @FXML
    public void signInStudent () throws IOException {
        Stage stage=new Stage();
        StudentUIKontroller studentUIKontroller=new StudentUIKontroller(firstNameTextField.getText(),lastNameTextField.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("StudentLogInScreen.fxml"));
        fxmlLoader.setController(studentUIKontroller);
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("University Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * signs in as a teacher
     * @throws IOException
     */

    @FXML
    public void signInTeacher () throws IOException {
        Stage stage=new Stage();
        Kontroller kontroller= new Kontroller();
        if (kontroller.findTeacherByName(firstNameTextField.getText(),lastNameTextField.getText())!=null){
            TeacherUIKontroller teacherUIKontroller=new TeacherUIKontroller(firstNameTextField.getText(),lastNameTextField.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(JavaFXUI.class.getResource("TeacherLogInScreen.fxml"));
            fxmlLoader.setController(teacherUIKontroller);
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            stage.setTitle("University Application");
            stage.setScene(scene);
            stage.show();
        }
        else{
            try{
                showAlertBox();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * shows an alertbox if the teacher does not exist
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
     * goes back to the main menu
     */

    public void backToMainMenu (){
        try{
            showMainMenu();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


}


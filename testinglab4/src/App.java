//package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import static java.awt.Font.*;
import static javafx.scene.text.Font.font;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        primaryStage.setTitle("Lab 04 Solution");

//        Creating layout gridpane
        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.CENTER);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new Insets(25, 25, 25, 25));



//        Create login UI controls

// -- labels
        Label lbUserName = new Label("Username:");
        Label lbPassword = new Label("Password:");
        Label lbFullName = new Label("Full Name:");
        Label lbEmail = new Label("Email:");
        Label lbPhoneNum = new Label("Phone #:");
        Label lbDate = new Label("Date:");
// -- inputs
        TextField txUserName = new TextField();
        PasswordField psPassword = new PasswordField();
        TextField txFullName =  new TextField();
        TextField txEmail = new TextField();
        TextField txPhoneNum= new TextField("Phone Number");
        DatePicker date = new DatePicker();
        
//        -- Button
        Button btn = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
//        -- link
        final Text actionTarget = new Text();

        

//        Add the components onto the myGrid pane
        myGrid.add(lbDate, 0, 6);
        myGrid.add(date, 1, 6);
        myGrid.add(lbPhoneNum, 0, 5);
        myGrid.add(txPhoneNum, 1, 5);
        myGrid.add(lbEmail, 0, 4);
        myGrid.add(txEmail, 1, 4);
        myGrid.add(txFullName, 1, 3);
        myGrid.add(lbFullName, 0, 3);
        myGrid.add(lbUserName, 0,1);
        myGrid.add(txUserName, 1,1);
        myGrid.add(lbPassword, 0,2);
        myGrid.add(psPassword, 1,2);

        myGrid.add(hbBtn, 1, 7);
        myGrid.add(actionTarget, 1, 8);

//        Setting the btn event
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(txUserName.getText());
                System.out.println(txFullName.getText());
                System.out.println(txEmail.getText());
                System.out.println(txPhoneNum.getText());
            }
        });


//        Creating myScene with custom layout
        Scene myScene = new Scene(myGrid, 600, 600);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
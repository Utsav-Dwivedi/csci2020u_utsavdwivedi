package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn midterm;
    @FXML
    private TableColumn assignment;
    @FXML
    private TableColumn exam;
    @FXML
    private TableColumn finalGrade;
    @FXML
    private TableColumn letterGrade;


    private TableView<StudentRecord> people;

    @FXML
    public void initialize() {
        ID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midtermGrade"));
        assignment.setCellValueFactory(new PropertyValueFactory<>("assignGrade"));
        exam.setCellValueFactory(new PropertyValueFactory<>("examGrade"));
        finalGrade.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        tableView.setItems(DataSource.getAllMarks());
    }


}

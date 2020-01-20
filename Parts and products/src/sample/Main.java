package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class Main extends Application {
    Stage mainStage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("outlines/MainWindow.fxml"));
        mainStage.setTitle("");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

}

package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class NoPartController {
    Stage stage;

    public void OkButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

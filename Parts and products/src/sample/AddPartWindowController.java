package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPartWindowController implements Initializable {

    public TextField PartIdField;
    public Label partTypeLabel;
    public TextField partTypeField;
    public TextField partMinField;
    public TextField partMaxField;
    public TextField partPriceField;
    public TextField partStockField;
    public TextField partNameField;
    public boolean outsourced = true;
    public static int partId = 0;
    public static String name = "";
    public static double price = 0;
    public static int stock = 0;
    public static int min = 0;
    public static int max = 0;
    public static String companyName = "";
    public static int machineId = 0;


    public Stage stage;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartIdField.setDisable(true);
        PartIdField.setPromptText("Auto Gen - Disabled");
        partNameField.setPromptText("Part Name");
        partStockField.setPromptText("Inv");
        partPriceField.setPromptText("Price/Cost");
        partMaxField.setPromptText("Max");
        partMinField.setPromptText("Min");
        partTypeField.setPromptText("Company A");



    }
    public void inHouseSelected(ActionEvent actionEvent) {
        partTypeLabel.setText("Machine ID");
        partTypeField.setPromptText("Mach ID");
        outsourced = false;
    }
    public void OutsourcedSelected(ActionEvent actionEvent) {
        partTypeLabel.setText(("Company Name"));
        partTypeField.setPromptText("Company A");
        outsourced = true;
    }
    public void saveButtonClicked(ActionEvent actionEvent) {
        try {
            //Incrementing part ID
            partId++;
            name = partNameField.getText();
            price = Double.parseDouble(partPriceField.getText());
            stock = Integer.parseInt(partStockField.getText());
            min = Integer.parseInt(partMinField.getText());
            max = Integer.parseInt(partMaxField.getText());
            companyName = partTypeField.getText();
            //converting string inputs into proper types
            if (outsourced) {
                Inventory.addPart(new Outsourced(partId, name, price, stock, min, max, companyName));
            } else {
                machineId = Integer.parseInt(partTypeField.getText());
                Inventory.addPart(new InHouse(partId, name, price, stock, min, max, machineId));
            }
            //get stage object and close stage
            Node source = (Node) actionEvent.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/BadInputWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            }catch (Exception e1){
                System.out.println("The warning window didnt load");
            }
        }
    }
    public void cancelButtonClicked(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}

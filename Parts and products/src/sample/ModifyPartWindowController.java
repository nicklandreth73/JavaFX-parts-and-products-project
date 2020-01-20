package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartWindowController implements Initializable {

    public TextField PartIdField;
    public Label partTypeLabel;
    public TextField partTypeField;
    public TextField partMinField;
    public TextField partMaxField;
    public TextField partPriceField;
    public TextField partStockField;
    public TextField partNameField;
    public boolean outsourced = true;
    public static String name = "";
    public static double price = 0;
    public static int stock = 0;
    public static int min = 0;
    public static int max = 0;
    public static String companyName = "";
    public static int machineId = 0;
    public Stage stage;
    public static Part receivedPart;
    public RadioButton InHouseRadio;
    public RadioButton OutsourcedRadio;
    public ToggleGroup partType;


    public static void acceptPart(Part passedPart){
        receivedPart = passedPart;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Checking part type and setting the default text based on the unique argument of inHouse and Outsourced types
        if(receivedPart instanceof InHouse){
            partTypeLabel.setText("Machine ID");
            partType.selectToggle(InHouseRadio);
            partTypeField.setText(Integer.toString(((InHouse) receivedPart).getMachineId()));
        }else {
            partTypeField.setText(((Outsourced) receivedPart).getCompanyName());
        }
        //Setting text for other fields based on the part object received from the main controller
        PartIdField.setDisable(true);
        PartIdField.setPromptText(Integer.toString(receivedPart.getId()));
        partNameField.setText(receivedPart.getName());
        partStockField.setText(Integer.toString(receivedPart.getStock()));
        partPriceField.setText(Double.toString(receivedPart.getPrice()));
        partMaxField.setText(Integer.toString(receivedPart.getMax()));
        partMinField.setText(Integer.toString(receivedPart.getMin()));
    }
    public void inHouseSelected(ActionEvent actionEvent) {
        partTypeLabel.setText("Machine ID");
        partTypeField.clear();
        outsourced = false;
    }
    public void OutsourcedSelected(ActionEvent actionEvent) {
        partTypeLabel.setText(("Company Name"));
        partTypeField.clear();
        outsourced = true;
    }
    public void saveButtonClicked(ActionEvent actionEvent) {
        try {
            //converting string inputs into proper types
            name = partNameField.getText();
            price = Double.parseDouble(partPriceField.getText());
            stock = Integer.parseInt(partStockField.getText());
            min = Integer.parseInt(partMinField.getText());
            max = Integer.parseInt(partMaxField.getText());
            companyName = partTypeField.getText();
            //adding part as either oustsourced  based on which radio button is selected
            if (outsourced) {
               Inventory.updatePart(receivedPart.getId(),receivedPart);
               Inventory.addPart(new Outsourced(receivedPart.getId(), name, price, stock, min, max, companyName));
            } else {
                machineId = Integer.parseInt(partTypeField.getText());
               Inventory.updatePart(receivedPart.getId(),receivedPart);
               Inventory.addPart(new InHouse(receivedPart.getId(), name, price, stock, min, max, machineId));
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

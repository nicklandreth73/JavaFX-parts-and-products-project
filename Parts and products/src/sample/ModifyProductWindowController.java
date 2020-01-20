package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductWindowController implements Initializable {

    public TextField PartIdField;
    public TextField partMinField;
    public TextField partMaxField;
    public TextField partPriceField;
    public TextField partStockField;
    public TextField partNameField;
    public static String name = "";
    public static double price = 0;
    public static int stock = 0;
    public static int min = 0;
    public static int max = 0;
    public static Product receivedProduct;


    public Stage stage;

    public TableView<Part> PartTableFixed;
    public TableColumn<Part, Integer> partIDColumnFixed;
    public TableColumn<Part, String> partNameColumnFixed;
    public TableColumn<Part, Integer> partInventoryColumnFixed;
    public TableColumn<Part, Double> partPriceColumnFixed;

    public TableView<Part> PartTableFluid;
    public TableColumn<Part, Integer> partIDColumnFluid;
    public TableColumn<Part, String> partNameColumnFluid;
    public TableColumn<Part, Integer> partInventoryColumnFluid;
    public TableColumn<Part, Double> partPriceColumnFluid;
    public TextField partSearch;

    private ObservableList<Product> tempProducts = FXCollections.observableArrayList();


    public static void acceptProduct(Product passedProduct) {
        receivedProduct = passedProduct;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        PartIdField.setDisable(true);
        PartIdField.setText(Integer.toString(receivedProduct.getId()));
        partNameField.setText(receivedProduct.getName());
        partStockField.setText(Integer.toString(receivedProduct.getStock()));
        partPriceField.setText(Double.toString(receivedProduct.getPrice()));
        partMaxField.setText(Integer.toString(receivedProduct.getMax()));
        partMinField.setText(Integer.toString(receivedProduct.getMin()));


        partIDColumnFluid.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumnFluid.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumnFluid.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryColumnFluid.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartTableFluid.setItems(receivedProduct.getAllAssociatedParts());

        partIDColumnFixed.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumnFixed.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumnFixed.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryColumnFixed.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartTableFixed.setItems(Inventory.getAllParts());

    }
    private void hasPart() throws Exception {
        if(tempProducts.size()!= 0) {
            if (tempProducts.get(0).getAllAssociatedParts().isEmpty()) {
                throw new Exception("No part Exception");
            }
        }else if (receivedProduct.getAllAssociatedParts().isEmpty()){
            throw new Exception("No part Exception");
        }
    }
    public void saveButtonClicked(ActionEvent actionEvent) {
        //Check to ensure product has at least one part.
        try {
            hasPart();
            try {
                //deletes old product and replaces it with new updated product if there has been a change
                if (!tempProducts.isEmpty()) {
                    Inventory.addProduct(tempProducts.get(0));
                    Inventory.deleteProduct(receivedProduct);
                }
                //get stage object and close stage
                Node source = (Node) actionEvent.getSource();
                stage = (Stage) source.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/BadInputWindow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e1) {
                    System.out.println("The warning window didnt load");
                }
            }
        }catch(Exception e) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/NoPartWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e1) {
                System.out.println("The warning window didn't load");
            }
        }
    }

    public void addTempProduct(Product tempProduct) {
        tempProducts.add(tempProduct);
    }

    public ObservableList<Product> getTempProducts() {
        return tempProducts;
    }

    // closes stage
    public void cancelButtonClicked(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void partSearch(ActionEvent actionEvent) {
        if (partSearch.getText().trim().isEmpty()) {
        } else {
            String searchText = partSearch.getText().toLowerCase();
            for (Part p : PartTableFixed.getItems()) {
                if (p.getName().toLowerCase().equals(searchText) || Integer.toString(p.getId()).contains(searchText)) {
                    PartTableFixed.getSelectionModel().select(p);
                }
            }
        }
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        // adds only one item to temporary part
        try {
            if (tempProducts.isEmpty()) {
                name = partNameField.getText();
                price = Double.parseDouble(partPriceField.getText());
                stock = Integer.parseInt(partStockField.getText());
                min = Integer.parseInt(partMinField.getText());
                max = Integer.parseInt(partMaxField.getText());
                addTempProduct(new Product(receivedProduct.getId(), name, price, stock, min, max));
                //adding parts from associated partlist from passed product to temporary product associated list checking once again to be sure their is at least one part
                if (!receivedProduct.getAllAssociatedParts().isEmpty()) {
                    for (int i = 0; i < receivedProduct.getAllAssociatedParts().size(); i++) {
                        getTempProducts().get(0).addAssociatedPart(receivedProduct.getAllAssociatedParts().get(i));
                    }
                }
            }
            //setting new values to assosciated parts list
            partIDColumnFluid.setCellValueFactory(new PropertyValueFactory<>("id"));
            partNameColumnFluid.setCellValueFactory(new PropertyValueFactory<>("name"));
            partPriceColumnFluid.setCellValueFactory(new PropertyValueFactory<>("price"));
            partInventoryColumnFluid.setCellValueFactory(new PropertyValueFactory<>("stock"));
            PartTableFluid.setItems(getTempProducts().get(0).getAllAssociatedParts());

            //ensures that empty part selections cannot be added to part list
            if (!PartTableFixed.getSelectionModel().getSelectedItem().getName().trim().isEmpty()) {
                getTempProducts().get(0).addAssociatedPart(PartTableFixed.getSelectionModel().getSelectedItem());
            }

        } catch (Exception e) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/BadInputWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                stage.initModality(Modality.APPLICATION_MODAL);
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e1) {
                System.out.println("The warning window didnt load");
            }
        }
    }

    public void deletePart(ActionEvent actionEvent) {
        if (tempProducts.isEmpty()) {
            receivedProduct.deleteAssociatedPart(PartTableFluid.getSelectionModel().getSelectedItem());
        } else {
            tempProducts.get(0).deleteAssociatedPart(PartTableFluid.getSelectionModel().getSelectedItem());
        }
    }
}


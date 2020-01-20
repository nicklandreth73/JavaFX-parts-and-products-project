package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //part columns and table
    public TableView<Part> PartsTableMain;
    public TableColumn<Product, Integer> productIdColumn;
    public TableColumn<Product, String> productNameColumn;
    public TableColumn<Product, Integer> productInventoryColumn;
    public TableColumn<Product, Double> productPriceColumn;
    //product columns and table
    public TableView<Product> ProductTableMain;
    public TableColumn<Part, String> partNameColumn;
    public TableColumn<Part, Integer> partInventoryColumn;
    public TableColumn<Part, Double> partPriceColumn;
    public TableColumn<Part, Integer> partIdColumn;
    //text fields
    public TextField productSearch;
    public TextField partSearch;
    public Button mainProductSearchButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Parts table columns are set here
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        //Parts table column are given to the the main parts table here.
        PartsTableMain.setItems(Inventory.getAllParts());
        //Products table columns are set here
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //Product table columns are given to the main product table here
        ProductTableMain.setItems(Inventory.getAllProducts());
    }
    public void mainProductSearch(ActionEvent actionEvent) {
        if(productSearch.getText().trim().isEmpty()) {
        }else {
            String searchText = productSearch.getText().toLowerCase();
            for (Product p : ProductTableMain.getItems()) {
                if (p.getName().toLowerCase().equals(searchText) || Integer.toString(p.getId()).contains(searchText)) {
                    ProductTableMain.getSelectionModel().select(p);
                }
            }
        }
    }
    public void mainPartSearch(ActionEvent actionEvent) {
        if(partSearch.getText().trim().isEmpty()){
        }else {
            String searchText = partSearch.getText().toLowerCase();
            for (Part p : PartsTableMain.getItems()) {
                if (p.getName().toLowerCase().equals(searchText) || Integer.toString(p.getId()).contains(searchText)) {
                    PartsTableMain.getSelectionModel().select(p);
                }
            }
        }
    }
    public void addProductMain(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/AddProductWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnCloseRequest(e-> AddProductWindowController.onClose());
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            System.out.println("Cant load new window");
        }
    }
    public void deleteProductMain(ActionEvent actionEvent) {
        if(Inventory.deleteProduct(ProductTableMain.getSelectionModel().getSelectedItem())){
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/NoSelectionWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            }catch (Exception e1){
                System.out.println("The warning window didn't load");
            }
        }

    }
    public void modifyProductMain(ActionEvent actionEvent) {
       ModifyProductWindowController.acceptProduct(ProductTableMain.getSelectionModel().getSelectedItem());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/ModifyProductWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/NoSelectionWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            }catch (Exception e1){
                System.out.println("The warning window didn't load");
            }
        }
    }
    public void addPartMain(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/AddPartWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            System.out.println("Cant load new window");
        }
    }
    public void deletePartMain(ActionEvent actionEvent) {
        if(Inventory.deletePart(PartsTableMain.getSelectionModel().getSelectedItem())){
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/NoSelectionWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            }catch (Exception e1){
                System.out.println("The warning window didn't load");
            }
        }
    }
    public void modifyPartMain(ActionEvent actionEvent) {
        ModifyPartWindowController.acceptPart(PartsTableMain.getSelectionModel().getSelectedItem());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/ModifyPartWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("outlines/NoSelectionWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
            }catch (Exception e1){
                System.out.println("The warning window didn't load");
            }
        }
    }
}


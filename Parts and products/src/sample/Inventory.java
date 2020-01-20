package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


import static javafx.collections.FXCollections.observableArrayList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static boolean deletePart(Part selectedPart){
        if(selectedPart == null){
            return false;
        }
        allParts.remove(selectedPart);
                return true;
    }
    public static boolean deleteProduct(Product selectedProduct){
        if(selectedProduct == null){
            return false;
        }
        allProducts.remove(selectedProduct);
        return true;
    }
    public static void updatePart(int index, Part selectedPart){
    deletePart(selectedPart);
    }
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
           allProducts.add(newProduct);
    }
}

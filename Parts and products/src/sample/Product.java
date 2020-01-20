package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product extends Inventory {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    Product(){
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.min = 0;
        this.max = 0;
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    public boolean deleteAssociatedPart(Part selectedAspart){
        if(selectedAspart == null){
            return false;
        }
        associatedParts.remove(selectedAspart);
        return true;
    }
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}


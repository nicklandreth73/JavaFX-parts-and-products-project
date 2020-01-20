package sample;

public class InHouse extends Part {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID){
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.machineId = machineID;
    }
    public InHouse(){
        this.setId(0);
        this.setName("");
        this.setPrice(0);
        this.setStock(0);
        this.setMin(0);
        this.setMax(0);
    }
    public int getMachineId() {
        return machineId;
    }
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}

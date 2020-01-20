package sample;

public class Outsourced extends Part {
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
        this.setCompanyName(companyName);
    }
    public Outsourced(){
        this.setId(0);
        this.setName("");
        this.setPrice(0);
        this.setStock(0);
        this.setMin(0);
        this.setMax(0);
        this.setCompanyName("");
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

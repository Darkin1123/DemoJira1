
package Objects;

import java.io.Serializable;

public class ReceiptDetail implements Serializable {
    private String RCode;
    private String pCode;
    private String pName;
    private int quantity;
    private Products product;
    private double cost;
    
    public ReceiptDetail(){}
    
    public ReceiptDetail(String RCode, String pCode, String pName, int quantity, Products product){
        this.RCode = RCode;
        this.product = product;
        this.pCode = pCode;
        this.pName = pName;
        this.quantity = quantity;
        this.cost = getCost();
    }

    public String getRCode() {
        return RCode;
    }

    public void setRCode(String RCode) {
        this.RCode = RCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getpCode() {
        return product.getpCode();
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpName() {
        return product.getProductName();
    }

    public double getCost() {
        return product.getCostPerEach()*quantity;
    }
    
    @Override
    public String toString(){
        return "Receipt Code: "+RCode
                +"\nProduct Code: "+getpCode()
                +"\nProduct Name: "+getpName()
                +"\nQuantity: "+ quantity
                +"\nEstimated Cost: "+getCost();
    }
    
}

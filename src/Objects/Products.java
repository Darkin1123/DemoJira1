
package Objects;

import Validation.Check;
import java.io.Serializable;

public class Products implements Serializable{
    private String pCode;
    private String ProductName;
    private String Type;
    private double CostPerEach;
    private String ManufacturingDate;
    private String ExpiredDate;
    private int inStock;
    
    public Products(){}
    
    public Products(String pCode, String ProductName, String Type, double CostPerEach,
            String ManufacturingDate, String ExpiredDate, int inStock){
        this.pCode = pCode;
        this.ProductName = ProductName;
        this.Type = Type;
        this.CostPerEach = CostPerEach;
        this.ManufacturingDate = ManufacturingDate;
        this.ExpiredDate = ExpiredDate;
        this.inStock = inStock;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        if(ProductName==null || ProductName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid input. Product Name cannot be null !");
        }
        this.ProductName = ProductName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        if (Type == null || Type.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Type. Type must not be null or empty !");
        }
        this.Type = Type;
    }

    public double getCostPerEach() {
        return CostPerEach;
    }

    public void setCostPerEach(double CostPerEach) {
        if (CostPerEach <= 0) {
            throw new IllegalArgumentException("Invalid unit price. "
                    + "Unit price must be a positive number.");
        }
        this.CostPerEach = CostPerEach;
    }

    public String getManufacturingDate() {
        return ManufacturingDate;
    }

    public void setManufacturingDate(String ManufacturingDate) {
        if (ManufacturingDate == null || !Check.checkValidDateFormat(ManufacturingDate)) {
            throw new IllegalArgumentException("Invalid Manufacturing Date "
                    + "date must be a valid date format (dd/MM/yyyy).");
        } 
        this.ManufacturingDate = ManufacturingDate;
    }

    public String getExpiredDate() {
        return ExpiredDate;
    }

    public void setExpiredDate(String ExpiredDate) {
        if (ExpiredDate == null || !Check.checkValidDateFormat(ExpiredDate)) {
            throw new IllegalArgumentException("Invalid Expired Date "
                    + "date must be a valid date format (dd/MM/yyyy).");
        }
        this.ExpiredDate = ExpiredDate;
    }
    
    @Override
    public String toString(){
        return String.format("%-6s %-15s %-15s %-15s %-15s %-15s %-15s",
                pCode, ProductName, Type, CostPerEach,
                ManufacturingDate, ExpiredDate, inStock);
    }
    
}

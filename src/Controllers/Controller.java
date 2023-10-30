
package Controllers;

import CollectionsManagement.ListOfProducts;
import CollectionsManagement.ListOfReceipt;
import Objects.ExportReceipt;
import Objects.ImportReceipt;
import Objects.Products;
import Objects.ReceiptDetail;
import Validation.Check;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Set;
public class Controller {
    static ListOfProducts list = new ListOfProducts();
    static ListOfReceipt receipt = new ListOfReceipt();
    
    public void addProduct(){
        boolean cont = true;
        String pCode, ManuDate, ExDate;
        while(cont){
            while(true){
                pCode = Check.checkString("Enter Product Code: ",
                        "Product Code must be in form [PXXX]",
                        Check.PRODUCTCODE_REGEX);
                if(list.checkDuplicatedID(pCode)){
                    System.out.println("Product have already existed !");
                }
                else break;
            }
            
            String productName = Check.checkString("Enter Product Name: ",
                    "Input must not contain other type than String !",
                    Check.STRING_REGEX);
            
            String Type = Check.checkString("Enter Type: ",
                    "Must be in form [Daily Use] or [Long Shelf Life] !",
                    "^(Daily Use|Long Shelf Life)$");
            
            double costPerEach = Check.checkDouble("Enter Cost per each product: ",
                    "Input must be a positive double");
            while(true){
            ManuDate = Check.checkDate("Enter Manufacture Date: ");
            ExDate = Check.checkDate("Enter Expired Date: ");
            
            LocalDate MD = Check.convertStringToLocalDate(ManuDate);
            LocalDate ED = Check.convertStringToLocalDate(ExDate);
            if(MD.isAfter(ED) || ED.isBefore(MD)){
                System.out.println("The Manufacture Date can not be AFTER Expired Date nor the other way around");
            }
            else break;
            }
            
            int inStock = Check.checkInt("Enter quantity in Stock (Selling): ",
                    "Must be a positive number !");
            Products addProduct = new Products(pCode, productName, Type, costPerEach, ManuDate, ExDate, inStock);
            
            list.addProducts(addProduct);
            
            cont = (Check.checkString("Do you want continue adding ?",
                    "Must in the form of [y] or [N]",
                    Check.YESNO_REGEX)).equalsIgnoreCase("Y") ? true : false;
        }
    }
    
    public void updateProduct(){
        LocalDate MD, ED;
        String pCode = Check.checkString("Enter Product Code: ",
                "Product Code must be in form of [PXXX]",
                Check.PRODUCTCODE_REGEX);
        Products productUpdate = list.findProductByCode(pCode);
        
        if(productUpdate == null){
            System.out.println("The Product Code does not exit !");
            System.out.println("Update Failed !");
            return;
        }
        
        productUpdate.setpCode(pCode);
        
        if(Check.checkUpdate("Product Name")){
            String productName = Check.checkString("Enter new Product Name: ",
                    "Input must be String !",
                    Check.STRING_REGEX);
            productUpdate.setProductName(productName);
        }
        
        if(Check.checkUpdate("Type")){
            String Type = Check.checkString("Enter new Product Type: ",
                    "Input must be String !",
                    Check.STRING_REGEX);
            productUpdate.setType(Type);
        }
        
        if(Check.checkUpdate("Cost per Each")){
            double cost = Check.checkDouble("Enter new Cost: ",
                    "Input value must be a positive Double !");
            productUpdate.setCostPerEach(cost);
        }
        
        if(Check.checkUpdate("Maufacture Date and Expired Date")){
            while(true){
            String ManuDate = Check.checkDate("Enter Manufacture Date: ");
            String ExDate = Check.checkDate("Enter Expired Date: ");
            
            MD = Check.convertStringToLocalDate(ManuDate);
            ED = Check.convertStringToLocalDate(ExDate);
            if(MD.isAfter(ED) || ED.isBefore(MD) ){
                System.out.println("Expired Date can not after Manufacture Date nor the other way around");
            }
            else{
                productUpdate.setManufacturingDate(ManuDate);
                productUpdate.setExpiredDate(ExDate);
                break;
            }
            }
        }
        
        
        System.out.println("Update Product Sucessfully !");
    }
    
    public void deleteProduct(){
        String pCode = Check.checkString("Enter Product code: ",
                "Must be in form of [PXXX]",
                Check.PRODUCTCODE_REGEX);
        Products productlist = list.findProductByCode(pCode);
        if(productlist == null){
            System.out.println("Could not find product Code");
            System.out.println("Delete Failed !");
            return;
        }
        
        list.removeProduct(productlist);
        System.out.println("Delete Successfully !");
    }
    
    public void displayProductList(){
        System.out.printf("%-6s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "pCode", "Product Name", "Type", "Cost Per Each",
                "Manfature Date", "Expired Date", "In Stock");
        for(Products product : list.getListOfProducts()){
            System.out.println(product);
        }
    }
    
    public void createImportReceipt(){
        System.out.println("--Creating new Import Receipt--");
        boolean cont = true;
        String ICode, importDate, pCode, pName;
        int quantity;
        
        while(true){
            ICode = Check.checkString("Enter Import Code: ",
                    "Must be in form of [IXXX]",
                    Check.IMPORTCODE_REGEX);
            if(receipt.checkDuplicatedICode(ICode)){
                System.out.println("The Import Code already exist !");
                System.out.println("Return to the Menu...");
                return;
            }
            else break;
        }
        importDate = Check.checkDate("Enter import date: ");
        
        ImportReceipt IR = new ImportReceipt(ICode, importDate);
        receipt.addImportReceipt(IR);
        
        while(cont){
            
            while(true){
                System.out.println("Adding product to Import Receipt");
                pCode = Check.checkString("Enter the Import Product Code: ",
                        "Must be in form of [PXXX]",
                        Check.PRODUCTCODE_REGEX);
                if(list.checkDuplicatedID(pCode)){
                    break;
                }
                else System.out.println("The Product code does not exist !");
            }
            
            Products product = list.findProductByCode(pCode);
            
            pName = product.getProductName();
            
            quantity = Check.checkInt("Enter import quantity: ",
                    "Input must be a positive number !");
            
            ReceiptDetail RD = new ReceiptDetail(ICode, pCode, pName, quantity, product);
            
            
            System.out.println("The Import receipt detail of "+ICode);
            System.out.println(RD);
            System.out.println("Adding to the List Of Import Receipt");
            IR.addReceiptDetail(RD);
            System.out.println("Successfully imputted");
            
            cont = Check.checkString("Do you want to continue add products to this import receipt ?",
                    "Must be in form of [Y] or [N]",
                    Check.YESNO_REGEX).equalsIgnoreCase("Y") ? true : false;
        }
    }
    
    public void createExportReceipt(){
        System.out.println("--Create Export Receipt--");
        String ECode, exportDate, pCode, pName;
        boolean cont = true;
        int quantity;
        
        while(true){
            ECode = Check.checkString("Enter Export Code: ",
                    "Must be in form of [EXXX]",
                    Check.EXPORTCODE_REGEX);
            if(receipt.checkDuplicatedECode(ECode)){
                System.out.println("The Export Code have already existed !");
                System.out.println("Returning to the Menu...");
                return;
            }
            else break;
        }
        exportDate = Check.checkDate("Enter Export Date: ");
        ExportReceipt ER = new ExportReceipt(ECode, exportDate);
        receipt.addExportReceipt(ER);
        
        while(cont){
            
            while(true){
                pCode = Check.checkString("Enter export Product Code: ",
                        "Must be in form of [PXXX]",
                        Check.PRODUCTCODE_REGEX);
                if(list.checkDuplicatedID(pCode)){
                    break;
                }
                else System.out.println("The Prodcut Code does not exist !");
            }
            
            Products product = list.findProductByCode(pCode);
            
            pName = product.getProductName();
            
            quantity = Check.checkInt("Enter product quantity: ",
                    "Must be a positive number !");
            
            ReceiptDetail RD = new ReceiptDetail(ECode, pCode, pName, quantity, product);
            
            System.out.println("The Receipt Detail of "+ECode);
            System.out.println(RD);
            System.out.println("Adding to the List Of Import Receipt");
            ER.addReceiptDetail(RD);
            System.out.println("Receipt inputed successfully !");
            
            cont = Check.checkString("Do you want to continue adding products into this receipt ?",
                    "Must in the form of [Y] or [N] ",
                    Check.YESNO_REGEX).equalsIgnoreCase("Y") ? true : false ;
            
        }
    }
    
    public void displayImportReceipt(){
        String ICode, pCode, pName;
        int quantity;
        double TotalCost = 0.0;
        
        while(true){
            ICode = Check.checkString("Enter Import Receipt Code: ",
                    "Must be in form of [IXXX] ",
                    Check.IMPORTCODE_REGEX);
            if(receipt.checkDuplicatedICode(ICode)){
                break;
            }
            else{
                System.out.println("The Import Code is unavailble !");
                System.out.println("Return to the Menu");
                return;
            }
        }
        
        System.out.println(" LIST OF IMPORT RECEIPT");
        System.out.println(" Import Code | Import Date | Product Code | Product Name | Quantity | Estimated Cost ");
        System.out.println(" ------------------------------------------------------------------------------------");
        
        for(ImportReceipt IR : receipt.getListOfIReceipt()){
            if(IR.getICode().equalsIgnoreCase(ICode)){
                double EstCost = 0.0;
                for(ReceiptDetail RD: IR.getRdetail()){
                        quantity = RD.getQuantity();
                        pCode = RD.getpCode();
                        pName = RD.getpName();
                        EstCost = RD.getCost();
                        TotalCost += RD.getCost();
                        
                System.out.printf("%-13s | %-13s | %-14s | %-14s | %-10s | %-16s%n ",
                             IR.getICode(),IR.getImportDate(), pCode, pName, quantity, EstCost);    
                }
            }
        }
        
        System.out.println(" ------------------------------------------------------------------------------------");
        System.out.printf("%-13s | %-13s |              |              |          | %-16s%n", "","Total Cost",TotalCost);		
    }
    
    public void displayExportReceipt(){
        String ECode, pCode, pName;
        int quantity;
        double TotalCost = 0.0;
        
        while(true){
            ECode = Check.checkString("Enter Export Receipt Code: ",
                    "Must be in form of [EXXX] ",
                    Check.EXPORTCODE_REGEX);
            if(receipt.checkDuplicatedICode(ECode)){
                break;
            }
            else{
                System.out.println("The Export Code is unavailble !");
                System.out.println("Return to the Menu");
                return;
            }
        }
        
        System.out.println(" LIST OF EXPORT RECEIPT");
        System.out.println(" Export Code | Export Date | Product Code | Product Name | Quantity | Estimated Cost ");
        System.out.println(" ------------------------------------------------------------------------------------");
        
        for(ExportReceipt ER : receipt.getListOfEReceipt()){
            if(ER.getECode().equalsIgnoreCase(ECode)){
                double EstCost = 0.0;
                for(ReceiptDetail RD: ER.getRdetail()){
                        quantity = RD.getQuantity();
                        pCode = RD.getpCode();
                        pName = RD.getpName();
                        EstCost = RD.getCost();
                        TotalCost += RD.getCost();
                    
                System.out.printf("%-13s | %-13s | %-14s | %-14s | %-10s | %-16s%n ",
                             ER.getECode(),ER.getExportDate(), pCode, pName, quantity, EstCost); 
                }
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-13s | %-13s |              |              |          | %-16s%n", "","Total Cost",TotalCost);
    }
    
    public void ExpiredProducts(){
        String today = Check.checkDate("Enter today Date: ");
        LocalDate day = Check.convertStringToLocalDate(today);
        for(Products product: list.getListOfProducts()){
            if(list.findProductsByDate(day)){
                System.out.printf("%-6s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "pCode", "Product Name", "Type", "Cost Per Each",
                "Manfature Date", "Expired Date", "In Stock");
                
                System.out.println(product);
            }
        } 
    }
    
    public void SellingProducts(){
        System.out.println("The Products that have quantity over 0:\n");
        for(Products product: list.getListOfProducts()){
            if(product.getInStock() > 0){
                System.out.printf("%-6s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "pCode", "Product Name", "Type", "Cost Per Each",
                "Manfature Date", "Expired Date", "In Stock");
                
                System.out.println(product);
                
            }
            else if(product.getInStock() == 0) {
                System.out.println("There isn't any more product that are greater than 0 quantity");
                return;
            }
        }
    }
    
    public void OutOfStock(){
        System.out.println("The Products that have quantity less than 3\n");
        for(Products product: list.getListOfProducts()){
            if(product.getInStock() < 3){
                System.out.printf("%-6s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "pCode", "Product Name", "Type", "Cost Per Each",
                "Manfature Date", "Expired Date", "In Stock");
                
                System.out.println(product);
            }
            else if(product.getInStock() >= 3){
                System.out.println("There isn't any product that out of stock !");
                return;
            }
        }
    }

    public static void loadData(){
       FileInputStream fileInputStream = null;
        ObjectInputStream objInputStream = null;
        try {
            fileInputStream = new FileInputStream("products.dat");
            objInputStream = new ObjectInputStream(fileInputStream);
            list.setListOfProducts((Set<Products>) objInputStream.readObject());

            fileInputStream = new FileInputStream("ImportReceipts.dat");
            objInputStream = new ObjectInputStream(fileInputStream);
            receipt.setListOfIReceipt((Set<ImportReceipt>) objInputStream.readObject());
            
            fileInputStream = new FileInputStream("ExportReceipts.dat");
            objInputStream = new ObjectInputStream(fileInputStream);
            receipt.setListOfEReceipt((Set<ExportReceipt>) objInputStream.readObject());

        } catch (Exception e) {
            System.out.println("Error when load data: " + e.getMessage());
        } finally {
            try {
                objInputStream.close();
                fileInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } 
    }
    
    public static void saveData(){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("products.dat");
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(list.getListOfProducts());

            fileOutputStream = new FileOutputStream("ImportReceipts.dat");
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(receipt.getListOfIReceipt());
            
            fileOutputStream = new FileOutputStream("ExportReceipts.dat");
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(receipt.getListOfEReceipt());

            System.out.println("Data written to file successfully.");
            
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        } finally {
            try {
                objOutputStream.close();
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

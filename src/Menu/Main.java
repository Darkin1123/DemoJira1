
package Menu;

import Controllers.Controller;
import Validation.Check;

public class Main {
    public static void main(String[] args) {
        Controller control = new Controller();
        while(true){
            displayMenu();
            int choice = Check.checkInt("Enter your choice [1-13]: ",
                    "Must be a positive number !");
            if(choice < 1 || choice > 13){
                System.out.println("Exit the Program...");
                return;
            }
            switch(choice){
                case 1: 
                    control.addProduct();
                    break;
                case 2:
                    control.updateProduct();
                    break;
                case 3:
                    control.deleteProduct();
                    break;
                case 4:
                    control.displayProductList();
                    break;
                case 5:
                    control.createImportReceipt();
                    break;
                case 6:
                    control.displayImportReceipt();
                    break;
                case 7:
                    control.createExportReceipt();
                    break;
                case 8:
                    control.displayExportReceipt();
                    break;
                case 9:
                    control.ExpiredProducts();
                    break;
                case 10:
                    control.SellingProducts();
                    break;
                case 11:
                    control.OutOfStock();
                    break;
                case 12:
                    control.loadData();
                    control.saveData();
                    break;
                case 13:
                    System.exit(0);
                    break;
            }
            
        }
    }
    
    private static void displayMenu() {
        System.out.println("                               MENU\n"
                + "==========================================================================\n"
                + "1. Add a product\n"
                + "2. Update a product\n"
                + "3. Delete a product\n"
                + "4. Show all products\n"
                + "5. Add Import Receipt\n"
                + "6. Display Import Receipt\n"
                + "7. Add Export Receipt\n"
                + "8. Display Export Receipt\n"
                + "9. Not yet Expired Products Report\n"
                + "10.Products that are in Stock Report\n"
                + "11.Products that running out of Stock Report\n"
                + "12.Save and Load Data\n"
                + "13.Exit \n"
                + "==========================================================================");
    }
    
}

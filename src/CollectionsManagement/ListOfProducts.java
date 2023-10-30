
package CollectionsManagement;

import Objects.Products;
import Validation.Check;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class ListOfProducts {
    Set <Products> ListOfProducts;
    
    public ListOfProducts(){
        ListOfProducts = new HashSet<>();
    }

    public Set<Products> getListOfProducts() {
        return ListOfProducts;
    }

    public void setListOfProducts(Set<Products> ListOfProducts) {
        this.ListOfProducts = ListOfProducts;
    }
    
    public boolean checkDuplicatedID(String pCode){
        for(Products FoundProduct: ListOfProducts){
            if(pCode.equalsIgnoreCase(FoundProduct.getpCode())){
                return true;
            }
        }
        return false;
    }
    
    public Products findProductByCode(String pCode){
        for(Products foundProduct: ListOfProducts){
            if(pCode.equalsIgnoreCase(foundProduct.getpCode())){
                return foundProduct;
            }
        }
        return null;
    }
    
    public void addProducts(Products product){
        ListOfProducts.add(product);
    }
    
    public void removeProduct(Products product){
        ListOfProducts.remove(product);
    }
    
    public boolean findProductsByDate(LocalDate date){
         for(Products product : ListOfProducts){
             if(date.isBefore(Check.convertStringToLocalDate(product.getExpiredDate()))){
                 return true;
             }
         }
         return false;
    }
    
}

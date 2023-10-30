
package CollectionsManagement;

import Objects.ExportReceipt;
import Objects.ImportReceipt;
import Objects.ReceiptDetail;
import java.util.HashSet;
import java.util.Set;

public class ListOfReceipt {
    public Set<ImportReceipt> ListOfIReceipt;
    public Set<ExportReceipt> ListOfEReceipt;
    
    public ListOfReceipt(){
        ListOfIReceipt = new HashSet<>();
        ListOfEReceipt = new HashSet<>();
    }
    
    public Set<ImportReceipt> getListOfIReceipt() {
        return ListOfIReceipt;
    }

    public void setListOfIReceipt(Set<ImportReceipt> ListOfIReceipt) {
        this.ListOfIReceipt = ListOfIReceipt;
    }

    public Set<ExportReceipt> getListOfEReceipt() {
        return ListOfEReceipt;
    }

    public void setListOfEReceipt(Set<ExportReceipt> ListOfEReceipt) {
        this.ListOfEReceipt = ListOfEReceipt;
    }
    
    public boolean checkDuplicatedICode(String Icode){
        for(ImportReceipt IReceipt: ListOfIReceipt){
            if(Icode.equalsIgnoreCase(IReceipt.getICode())){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkDuplicatedECode(String Ecode){
        for(ExportReceipt EReceipt: ListOfEReceipt){
            if(Ecode.equalsIgnoreCase(EReceipt.getECode())){
                return true;
            }
        }
        return false;
    }
    
    public ImportReceipt findImportReceiptByCode(String Icode){
        for(ImportReceipt IReceipt: ListOfIReceipt){
            if(Icode.equalsIgnoreCase(IReceipt.getICode())){
                return IReceipt;
            }
        }
        return null;
    }
    
    public ExportReceipt findExportReceiptByCode(String Icode){
        for(ExportReceipt EReceipt: ListOfEReceipt){
            if(Icode.equalsIgnoreCase(EReceipt.getECode())){
                return EReceipt;
            }
        }
        return null;
    }
    
    public void addImportReceipt(ImportReceipt IReceipt){
        ListOfIReceipt.add(IReceipt);
    }
    
    public void removeExportReceipt(ExportReceipt EReceipt){
        ListOfEReceipt.add(EReceipt);
    }
    
    public void addExportReceipt(ExportReceipt EReceipt){
        ListOfEReceipt.add(EReceipt);
    }
    
    public void removeImportReceipt(ImportReceipt IReceipt){
        ListOfIReceipt.add(IReceipt);
    }
    
    public ReceiptDetail getReceiptDetailByICode(String ICode){
        for(ImportReceipt IR: ListOfIReceipt){
            for(ReceiptDetail RD: IR.getRdetail()){
                if(RD.getRCode().equalsIgnoreCase(ICode)){
                    return RD;
                }
            }
        }
        return null;
    }
    
    public ReceiptDetail getReceiptDetailByECode(String ECode){
        for(ExportReceipt ER: ListOfEReceipt){
            for(ReceiptDetail RD: ER.getRdetail()){
                if(RD.getRCode().equalsIgnoreCase(ECode)){
                    return RD;
                }
            }
        }
        return null;
    }
    
    
}

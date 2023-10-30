
package Objects;

import Validation.Check;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImportReceipt implements Serializable {
    private String ICode;
    private String ImportDate;
    private List<ReceiptDetail> rdetail;
    
    public ImportReceipt(){}
    
    public ImportReceipt(String ICode, String ImportDate){
        this.ICode = ICode;
        this.rdetail = new ArrayList<>();
        this.ImportDate = ImportDate;
    }

    public List<ReceiptDetail> getRdetail() {
        return rdetail;
    }
    
    public void addReceiptDetail(ReceiptDetail input){
        rdetail.add(input);
    }
    
    public String getICode() {
        return ICode;
    }

    public void setICode(String ICode) {
        if (ICode == null || ICode.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Import Code. "
                    + "ICode must not be null or empty.");
        }
        this.ICode = ICode;
    }

    public String getImportDate() {
        return ImportDate;
    }

    public void setImportDate(String ImportDate) {
        if (ImportDate == null || !Check.checkValidDateFormat(ImportDate)) {
            throw new IllegalArgumentException("Invalid Import Date "
                    + "date must be a valid date format (dd/MM/yyyy).");
        }
        this.ImportDate = ImportDate;
    }  
}

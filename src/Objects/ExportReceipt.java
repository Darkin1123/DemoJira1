
package Objects;

import Validation.Check;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExportReceipt implements Serializable {
    private String ECode;
    private List<ReceiptDetail> rdetail;
    private String ExportDate;
    
    public ExportReceipt(){}
    
    public ExportReceipt(String ECode, String ExportDate){
        this.ECode = ECode;
        this.rdetail=new ArrayList<>();
        this.ExportDate = ExportDate;
    }

    public String getExportDate() {
        return ExportDate;
    }

    public void setExportDate(String ExportDate) {
        if (ExportDate == null || !Check.checkValidDateFormat(ExportDate)) {
            throw new IllegalArgumentException("Invalid Export Date "
                    + "date must be a valid date format (dd/MM/yyyy).");
        }
        this.ExportDate = ExportDate;
    }
    
    public String getECode() {
        return ECode;
    }

    public void setECode(String ECode) {
        if (ECode == null || ECode.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Export Code. ECode must not be null or empty !");
        }
        this.ECode = ECode;
    }

    public List<ReceiptDetail> getRdetail() {
        return rdetail;
    }
    
    public void addReceiptDetail(ReceiptDetail input){
        rdetail.add(input);
    }
    
}

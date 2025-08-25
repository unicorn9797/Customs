package model;

public class Tax {
    // fields
    private String taxId;
    private String declarationId;
    private String declarationType;
    private String itemName;      // 新增：貨物名稱
    private String hsCode;        // 可選：對應 HS Code
    private Double customsDuty;
    private Double vat;           // 增值稅
    private Double otherTax;
    private Double totalTax;

    // constructors
    public Tax() { }

    public Tax(String taxId, String declarationId, String declarationType, 
               String itemName, String hsCode, Double customsDuty, Double vat, Double otherTax) {
        super();
        this.taxId = taxId;
        this.declarationId = declarationId;
        this.declarationType = declarationType;
        this.itemName = itemName;
        this.hsCode = hsCode;
        this.customsDuty = customsDuty;
        this.vat = vat;
        this.otherTax = otherTax;
        this.totalTax = (customsDuty == null?0:customsDuty) + 
                        (vat == null?0:vat) + 
                        (otherTax == null?0:otherTax);
    }
    
 // getters & setters

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getDeclarationId() {
		return declarationId;
	}

	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}

	public String getDeclarationType() {
		return declarationType;
	}

	public void setDeclarationType(String declarationType) {
		this.declarationType = declarationType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public Double getCustomsDuty() {
		return customsDuty;
	}

	public void setCustomsDuty(Double customsDuty) {
		this.customsDuty = customsDuty;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getOtherTax() {
		return otherTax;
	}

	public void setOtherTax(Double otherTax) {
		this.otherTax = otherTax;
	}

	public Double getTotalTax() {
		this.totalTax = (customsDuty == null?0:customsDuty) + 
                (vat == null?0:vat) + 
                (otherTax == null?0:otherTax);
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

    
    
}
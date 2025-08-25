package model;

public class Goods {
	//fields
	private String GoodsId;
	private String declarationId;
	private String itemName;
	private String hsCode;//國際標準貨物分類碼
	private String originCountry;
	private Integer quantity;
	private Double unitPrice;
	private Double totalPrice;
	private Double grossWeight;
	private Double netWeight;
	private String quantityUnit;    // 數量單位，例如 件/箱/公斤
	private String unitPriceUnit;   // 單價單位，例如 USD/件、TWD/kg
	private String weightUnit;      // 重量單位，例如 kg、t
	//constructors
	public Goods()
	{
		
	}
	public Goods(String goodsId, String declarationId, String itemName, String hsCode, String originCountry,
			Integer quantity, Double unitPrice, Double grossWeight, Double netWeight) {
		super();
		GoodsId = goodsId;
		this.declarationId = declarationId;
		this.itemName = itemName;
		this.hsCode = hsCode;
		this.originCountry = originCountry;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = quantity*unitPrice;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
	}
	//methods
	public String getGoodsId() {
		return GoodsId;
	}
	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}
	public String getDeclarationId() {
		return declarationId;
	}
	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
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
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotalPrice() {
		this.totalPrice = quantity*unitPrice;
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public String getUnitPriceUnit() {
		return unitPriceUnit;
	}
	public void setUnitPriceUnit(String unitPriceUnit) {
		this.unitPriceUnit = unitPriceUnit;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	
	
	
	
}

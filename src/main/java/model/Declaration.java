package model;

import java.time.LocalDateTime;

public class Declaration {
	//fields
	private String declarationId;
	private LocalDateTime declarationDate;
	private String declarationType;
	private String importerName;
	private String importerId;
	private String exporterName;
	private String exporterId;
	private String transportMode;
	private String status;
	private String digitalSignature;
	private String publicKey;
	private Double taxFound;
	
	//constructors
	public Declaration()
	{
		
	}

	public Declaration(String declarationId, LocalDateTime declarationDate, String declarationType, String importerName,
			String importerId, String exporterName, String exporterId) {
		super();
		this.declarationId = declarationId;
		this.declarationDate = declarationDate;
		this.declarationType = declarationType;
		this.importerName = importerName;
		this.importerId = importerId;
		this.exporterName = exporterName;
		this.exporterId = exporterId;
	}
	
	
	//methods
	
	public String getDeclarationId() {
		return declarationId;
	}

	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}

	public LocalDateTime getDeclarationDate() {
		return declarationDate;
	}

	public void setDeclarationDate(LocalDateTime localDateTime) {
		this.declarationDate = localDateTime;
	}

	public String getDeclarationType() {
		return declarationType;
	}

	public void setDeclarationType(String declarationType) {
		this.declarationType = declarationType;
	}

	public String getImporterName() {
		return importerName;
	}

	public void setImporterName(String importerName) {
		this.importerName = importerName;
	}

	public String getImporterId() {
		return importerId;
	}

	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}

	public String getExporterName() {
		return exporterName;
	}

	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
	}

	public String getExporterId() {
		return exporterId;
	}

	public void setExporterId(String exporterId) {
		this.exporterId = exporterId;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDigitalSignature() {
		return digitalSignature;
	}

	public void setDigitalSignature(String digitalSignature) {
		this.digitalSignature = digitalSignature;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Double getTaxFound() {
		return taxFound;
	}

	public void setTaxFound(Double taxFound) {
		this.taxFound = taxFound;
	}
	
}

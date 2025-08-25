package model;

import java.time.LocalDateTime;

public class Transport {
	//fields
	private String transportId;
	private String declarationId;
	private String vesselOrFlight;
	private String portOfLoading;
	private String portOfDischarge;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	//constructors
	public Transport()
	{
		
	}
	
	public Transport(String transportId, String declarationId, String vesselOrFlight, String portOfLoading,
			String portOfDischarge, LocalDateTime departureDate, LocalDateTime arrivalDate) {
		super();
		this.transportId = transportId;
		this.declarationId = declarationId;
		this.vesselOrFlight = vesselOrFlight;
		this.portOfLoading = portOfLoading;
		this.portOfDischarge = portOfDischarge;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}
	
	//methods
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}
	public String getDeclarationId() {
		return declarationId;
	}
	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}
	public String getVesselOrFlight() {
		return vesselOrFlight;
	}
	public void setVesselOrFlight(String vesselOrFlight) {
		this.vesselOrFlight = vesselOrFlight;
	}
	public String getPortOfLoading() {
		return portOfLoading;
	}
	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}
	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	

}

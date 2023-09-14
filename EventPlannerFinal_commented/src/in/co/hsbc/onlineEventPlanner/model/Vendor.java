/**
 * DOCUMENTATION--------------------------------------------------------------->
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * ---------------------------------------------------------------------------------->
 */
package in.co.hsbc.onlineEventPlanner.model;

import java.util.Objects;

public class Vendor {
	private int vendorId;
	private String vendorAddress;
	private String vendorEmail;
	private String vendorContactNumber;
	private int eventPackages;

	public Vendor() {
		super();
	}

	public Vendor(int id, String address, String email, String contactNumber, int eventPackages) {
		super();
		this.vendorId = id;
		this.vendorAddress = address;
		this.vendorEmail = email;
		this.vendorContactNumber = contactNumber;
		this.eventPackages = eventPackages;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventPackages, vendorAddress, vendorContactNumber, vendorEmail, vendorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendor other = (Vendor) obj;
		return Objects.equals(eventPackages, other.eventPackages) && Objects.equals(vendorAddress, other.vendorAddress)
				&& Objects.equals(vendorContactNumber, other.vendorContactNumber)
				&& Objects.equals(vendorEmail, other.vendorEmail) && vendorId == other.vendorId;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorAddress=" + vendorAddress + ", vendorEmail=" + vendorEmail
				+ ", vendorContactNumber=" + vendorContactNumber + ", eventPackages=" + eventPackages + "]";
	}

	public int getId() {
		return vendorId;
	}

	public void setId(int id) {
		this.vendorId = id;
	}

	public String getAddress() {
		return vendorAddress;
	}

	public void setAddress(String address) {
		this.vendorAddress = address;
	}

	public String getEmail() {
		return vendorEmail;
	}

	public void setEmail(String email) {
		this.vendorEmail = email;
	}

	public String getContactNumber() {
		return vendorContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.vendorContactNumber = contactNumber;
	}

	public int getEventPackages() {
		return eventPackages;
	}

	public void setEventPackages(int eventPackages) {
		this.eventPackages = eventPackages;
	}
}

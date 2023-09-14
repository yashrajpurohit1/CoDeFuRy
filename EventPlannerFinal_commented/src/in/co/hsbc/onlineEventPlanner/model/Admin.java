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

public class Admin {
	private int adminUserName;
	private String aminFullName;
	private String adminPassword;
	private String adminEmail;
	private String adminMobileNumber;

	public int getUserName() {
		return adminUserName;
	}

	public void setUserName(int userName) {
		this.adminUserName = userName;
	}

	public String getFullName() {
		return aminFullName;
	}

	public void setFullName(String fullName) {
		this.aminFullName = fullName;
	}

	public String getPassword() {
		return adminPassword;
	}

	public void setPassword(String password) {
		this.adminPassword = password;
	}

	public String getEmail() {
		return adminEmail;
	}

	public void setEmail(String email) {
		this.adminEmail = email;
	}

	public String getMobileNumber() {
		return adminMobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.adminMobileNumber = mobileNumber;
	}

	public Admin() {
		super();
	}

	public Admin(int userName, String fullName, String password, String email, String mobileNumber) {
		super();
		this.adminUserName = userName;
		this.aminFullName = fullName;
		this.adminPassword = password;
		this.adminEmail = email;
		this.adminMobileNumber = mobileNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminEmail, aminFullName, adminMobileNumber, adminPassword, adminUserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(adminEmail, other.adminEmail) && Objects.equals(aminFullName, other.aminFullName)
				&& Objects.equals(adminMobileNumber, other.adminMobileNumber)
				&& Objects.equals(adminPassword, other.adminPassword) && adminUserName == other.adminUserName;
	}

	@Override
	public String toString() {
		return "Admin [userName=" + adminUserName + ", fullName=" + aminFullName + ", password=" + adminPassword
				+ ", email=" + adminEmail + ", mobileNumber=" + adminMobileNumber + "]";
	}

}

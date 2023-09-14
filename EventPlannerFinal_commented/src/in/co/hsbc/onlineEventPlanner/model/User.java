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

import java.util.Date;
import java.util.Objects;

public class User {
	private int userId;
	private String userFullName;
	private String userUserName;
	private String userPassword;
	private Date dateOfJoining;
	private Date dob;
	private String userDepartment;
	private String userMobileNumber;
	private String userEmail;
	private String userLocation;
	private String userStatus;

	public User() {
		super();
	}

	public User(int userId, String fullName, String username, String password, Date dateOfJoining, Date dob,
			String department, String mobileNumber, String email, String location, String status) {
		super();
		this.userId = userId;
		this.userFullName = fullName;
		this.userUserName = username;
		this.userPassword = password;
		this.dateOfJoining = dateOfJoining;
		this.dob = dob;
		this.userDepartment = department;
		this.userMobileNumber = mobileNumber;
		this.userEmail = email;
		this.userLocation = location;
		this.userStatus = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + userFullName + ", username=" + userUserName + ", password="
				+ userPassword + ", dateOfJoining=" + dateOfJoining + ", dob=" + dob + ", department=" + userDepartment
				+ ", mobileNumber=" + userMobileNumber + ", email=" + userEmail + ", location=" + userLocation
				+ ", status=" + userStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfJoining, userDepartment, dob, userEmail, userFullName, userLocation, userMobileNumber,
				userPassword, userStatus, userId, userUserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateOfJoining, other.dateOfJoining)
				&& Objects.equals(userDepartment, other.userDepartment) && Objects.equals(dob, other.dob)
				&& Objects.equals(userEmail, other.userEmail) && Objects.equals(userFullName, other.userFullName)
				&& Objects.equals(userLocation, other.userLocation)
				&& Objects.equals(userMobileNumber, other.userMobileNumber)
				&& Objects.equals(userPassword, other.userPassword) && Objects.equals(userStatus, other.userStatus)
				&& userId == other.userId && Objects.equals(userUserName, other.userUserName);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return userFullName;
	}

	public void setFullName(String fullName) {
		this.userFullName = fullName;
	}

	public String getUsername() {
		return userUserName;
	}

	public void setUsername(String username) {
		this.userUserName = username;
	}

	public String getPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return userDepartment;
	}

	public void setDepartment(String department) {
		this.userDepartment = department;
	}

	public String getMobileNumber() {
		return userMobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.userMobileNumber = mobileNumber;
	}

	public String getEmail() {
		return userEmail;
	}

	public void setEmail(String email) {
		this.userEmail = email;
	}

	public String getLocation() {
		return userLocation;
	}

	public void setLocation(String location) {
		this.userLocation = location;
	}

	public String getStatus() {
		return userStatus;
	}

	public void setStatus(String status) {
		this.userStatus = status;
	}
}

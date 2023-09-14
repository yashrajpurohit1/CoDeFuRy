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

public class Quotation {
	private int id;
	private Packagee packageType;
	private double estimatedAmount;
	private int planRequestId;
	private int userId;
	private int vendorId;
	private String userStatus;
	private String adminStatus;

	public Quotation() {
		super();
	}

	public Quotation(int id, Packagee packageType, double estimatedAmount, int planRequestId, int userId, int vendorId,
			String userStatus, String adminStatus) {
		super();
		this.id = id;
		this.packageType = packageType;
		this.estimatedAmount = estimatedAmount;
		this.planRequestId = planRequestId;
		this.userId = userId;
		this.vendorId = vendorId;
		this.userStatus = "pending";
		this.adminStatus = adminStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Packagee getPackageType() {
		return packageType;
	}

	public void setPackageType(Packagee packageType) {
		this.packageType = packageType;
	}

	public double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public int getPlanRequestId() {
		return planRequestId;
	}

	public void setPlanRequestId(int planRequestId) {
		this.planRequestId = planRequestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	@Override
	public String toString() {
		return "Quotation [id=" + id + ", packageType=" + packageType + ", estimatedAmount=" + estimatedAmount
				+ ", planRequestId=" + planRequestId + ", userId=" + userId + ", vendorId=" + vendorId + ", userStatus="
				+ userStatus + ", adminStatus=" + adminStatus + "]";
	}
}

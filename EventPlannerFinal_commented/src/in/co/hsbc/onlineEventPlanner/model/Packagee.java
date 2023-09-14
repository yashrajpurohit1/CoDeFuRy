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

import java.util.ArrayList;
import java.util.Objects;

public class Packagee {
	private int packageId;
	private String packageType;
	private ArrayList<Services> services;
	private double packageAmount;

	public Packagee() {
		super();
	}

	public Packagee(int id, String packageType, ArrayList<Services> services, int amount) {
		super();
		this.packageId = id;
		this.packageType = packageType;
		this.services = services;
		this.packageAmount = amount;
	}

	@Override
	public String toString() {
		return "Package [id=" + packageId + ", packageType=" + packageType + ", services=" + services + ", amount="
				+ packageAmount + "]";
	}

	public int getId() {
		return packageId;
	}

	public void setId(int id) {
		this.packageId = id;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public ArrayList<Services> getServices() {
		return services;
	}

	public void setServices(ArrayList<Services> services) {
		this.services = services;
	}

	public double getAmount() {
		return packageAmount;
	}

	public void setAmount(double d) {
		this.packageAmount = d;
	}

	@Override
	public int hashCode() {
		return Objects.hash(packageAmount, packageId, packageType, services);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Packagee other = (Packagee) obj;
		return packageAmount == other.packageAmount && packageId == other.packageId
				&& Objects.equals(packageType, other.packageType) && Objects.equals(services, other.services);
	}

}

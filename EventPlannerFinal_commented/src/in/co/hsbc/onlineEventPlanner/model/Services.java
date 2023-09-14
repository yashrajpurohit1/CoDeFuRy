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

public class Services {
	private int serviceid;
	private String serviceName;
	private double serviceAmount;

	public Services() {
		super();
	}

	public Services(int id, String name, double amount) {
		super();
		this.serviceid = id;
		this.serviceName = name;
		this.serviceAmount = amount;
	}

	public String getName() {
		return serviceName;
	}

	public void setName(String name) {
		this.serviceName = name;
	}

	public double getAmount() {
		return serviceAmount;
	}

	public void setAmount(double amount) {
		this.serviceAmount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(serviceAmount, serviceName, serviceid);
	}

	@Override
	public String toString() {
		return "Services [serviceid=" + serviceid + ", serviceName=" + serviceName + ", serviceAmount=" + serviceAmount
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Services other = (Services) obj;
		return serviceAmount == other.serviceAmount && Objects.equals(serviceName, other.serviceName)
				&& serviceid == other.serviceid;
	}

	public int getId() {
		return serviceid;
	}

	public void setId(int id) {
		this.serviceid = id;
	}

}

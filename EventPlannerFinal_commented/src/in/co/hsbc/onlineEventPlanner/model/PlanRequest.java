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
import java.util.Date;
import java.util.Objects;

public class PlanRequest {
	private int planRequestId;
	private Date fromDate;
	private Date toDate;
	private int numOfAttendent;
	private ArrayList<Services> servicesNeeded;

	public PlanRequest() {
		super();
	}

	public PlanRequest(int planRequestId, Date fromDate, Date toDate, int numOfAttendent,
			ArrayList<Services> servicesNeeded) {
		super();
		this.planRequestId = planRequestId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.numOfAttendent = numOfAttendent;
		this.servicesNeeded = servicesNeeded;
	}

	@Override
	public String toString() {
		return "PlanRequest [planRequestId=" + planRequestId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", numOfAttendent=" + numOfAttendent + ", servicesNeeded=" + servicesNeeded + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fromDate, numOfAttendent, planRequestId, servicesNeeded, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanRequest other = (PlanRequest) obj;
		return Objects.equals(fromDate, other.fromDate) && numOfAttendent == other.numOfAttendent
				&& planRequestId == other.planRequestId && Objects.equals(servicesNeeded, other.servicesNeeded)
				&& Objects.equals(toDate, other.toDate);
	}

	public int getPlanRequestId() {
		return planRequestId;
	}

	public void setPlanRequestId(int planRequestId) {
		this.planRequestId = planRequestId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getNumOfAttendent() {
		return numOfAttendent;
	}

	public void setNumOfAttendent(int numOfAttendent) {
		this.numOfAttendent = numOfAttendent;
	}

	public ArrayList<Services> getServicesNeeded() {
		return servicesNeeded;
	}

	public void setServicesNeeded(ArrayList<Services> servicesNeeded) {
		this.servicesNeeded = servicesNeeded;
	}

}

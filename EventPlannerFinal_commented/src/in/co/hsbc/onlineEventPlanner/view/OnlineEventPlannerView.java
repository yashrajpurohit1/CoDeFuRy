package in.co.hsbc.onlineEventPlanner.view;

import in.co.hsbc.onlineEventPlanner.model.Admin;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.model.Services;
import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.model.Vendor;

public interface OnlineEventPlannerView {
	public void printUser(User u);

	public void printQuotation(Quotation q);

	public void printPlanRequest(PlanRequest plan);

	public void printPackagee(Packagee packagee);

	public void printVendor(Vendor v);

	public void printAdmin(Admin a);

	public void showSuccessMessage(String msg);

	public void showErrorMessage(String msg);

	public String acceptName();

	public String acceptServices();

	public void printService(Services service);

	public String acceptPassword();

}

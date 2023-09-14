package in.co.hsbc.onlineEventPlanner.view.impl;

import java.util.Scanner;

import in.co.hsbc.onlineEventPlanner.model.Admin;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.model.Services;
import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.model.Vendor;
import in.co.hsbc.onlineEventPlanner.view.OnlineEventPlannerView;

public class OnlineEventPlannerViewImpl implements OnlineEventPlannerView {
	private Scanner sc;

	public OnlineEventPlannerViewImpl() {
		sc = new Scanner(System.in);
	}

	// to display the success message

	@Override
	public void showSuccessMessage(String msg) {

		System.out.println(msg);

	}

	// to display the error message
	@Override
	public void showErrorMessage(String msg) {

		System.out.println(msg);

	}

	// for accepting the name
	@Override
	public String acceptName() {

		System.out.println("Enter the name");
		String name = sc.next();
		return name;

	}

	// for accepting the services
	@Override
	public String acceptServices() {

		System.out.println("Enter the services which you require");
		String service = sc.next();
		return service;

	}

	// for displaying the User
	@Override
	public void printUser(User u) {

		System.out.println(u);

	}

	// for displaying the Quotation
	@Override
	public void printQuotation(Quotation q) {

		System.out.println(q);

	}

	// for displaying the Plan Requesr
	@Override
	public void printPlanRequest(PlanRequest plan) {

		System.out.println(plan);

	}

	// for displaying the Packagee
	@Override
	public void printPackagee(Packagee packagee) {

		System.out.println(packagee);

	}

	// for displaying the Vendor

	@Override
	public void printVendor(Vendor v) {

		System.out.println("The Vendor is");
		System.out.println(v);

	}

	// for displaying the Admin
	@Override
	public void printAdmin(Admin a) {

		System.out.println("The Admin is :");
		System.out.println(a);

	}

	// for displaying the services

	@Override
	public void printService(Services service) {

		System.out.println("The services are");
		System.out.println(service);

	}

	// to accept the password
	@Override
	public String acceptPassword() {

		System.out.println("Enter the password");
		String password = sc.next();
		return password;
	}

}

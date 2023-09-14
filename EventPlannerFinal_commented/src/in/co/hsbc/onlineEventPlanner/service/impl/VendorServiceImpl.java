package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.VendorDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.service.VendorService;

//implementation of service interface
public class VendorServiceImpl implements VendorService {
	private VendorDao vendorDao;

	public VendorServiceImpl(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	@Override
	public boolean addPackage(Packagee vendorPackage) throws RecordNotSavedException {

		boolean packages = vendorDao.addPackage(vendorPackage);
		return packages;
	}

	@Override
	public List<PlanRequest> getAllUserPlanRequests() throws RecordNotFetchedException {

		List<PlanRequest> planRequest = vendorDao.getAllUserPlanRequests();
		return planRequest;
	}

	@Override
	public Quotation createQuotation(Quotation quotation) throws RecordNotSavedException {

		Quotation quotations = vendorDao.createQuotation(quotation);
		return quotations;
	}
}

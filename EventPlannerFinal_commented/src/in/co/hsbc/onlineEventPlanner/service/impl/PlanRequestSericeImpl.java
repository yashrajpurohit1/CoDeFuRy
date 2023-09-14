package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PlanRequestDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFoundException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.service.PlanRequestService;

public class PlanRequestSericeImpl implements PlanRequestService {
	private PlanRequestDao planRequestDao;

	public PlanRequestSericeImpl(PlanRequestDao planRequestDao) {
		this.planRequestDao = planRequestDao;
	}

	@Override
	public PlanRequest createPlanRequest(PlanRequest planRequest) throws  RecordNotSavedException {

		PlanRequest planRequests = planRequestDao.createPlanRequest(planRequest);
		return planRequests;
	}

	@Override
	public PlanRequest getPlanRequestById(int id) throws RecordNotFoundException {

		PlanRequest planreq = planRequestDao.getPlanRequestById(id);
		return planreq;
	}

	@Override
	public List<PlanRequest> getAllPlanRequests() throws RecordNotFetchedException {

		List<PlanRequest> plans = planRequestDao.getAllPlanRequests();

		return plans;
	}

	@Override
	public void updatePlanRequest(PlanRequest planRequest) throws RecordNotUpdatedException {

		planRequestDao.updatePlanRequest(planRequest);

	}

	@Override
	public void deletePlanRequest(int id) throws RecordNotDeletedException {

		planRequestDao.deletePlanRequest(id);

	}

	@Override
	public boolean associatePlanRequestWithVendor(int planRequestId, int vendorId) {

		boolean associated = planRequestDao.associatePlanRequestWithVendor(planRequestId, vendorId);
		return associated;
	}
}

package in.co.hsbc.onlineEventPlanner.service;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFoundException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;

public interface PlanRequestService {
	PlanRequest createPlanRequest(PlanRequest planRequest) throws RecordNotUpdatedException, RecordNotSavedException;

	PlanRequest getPlanRequestById(int id) throws RecordNotFoundException;

	List<PlanRequest> getAllPlanRequests() throws RecordNotFetchedException;

	void updatePlanRequest(PlanRequest planRequest) throws RecordNotUpdatedException;

	void deletePlanRequest(int id) throws RecordNotDeletedException;

	boolean associatePlanRequestWithVendor(int planRequestId, int vendorId);
}

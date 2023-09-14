package in.co.hsbc.onlineEventPlanner.dao;

import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Services;

public interface ServiceDao {
	// Crud Functions of Services

	Services createService(Services service) throws RecordNotSavedException;

	Services getServiceById(int id) throws RecordNotFetchedException;

	List<Services> getAllServices() throws RecordNotFetchedException;

	ArrayList<Services> getServicesByPackageTypeId(int packageTypeId) throws RecordNotFetchedException;

	void updateService(Services service) throws RecordNotUpdatedException;

	void deleteService(int id) throws RecordNotDeletedException;
}

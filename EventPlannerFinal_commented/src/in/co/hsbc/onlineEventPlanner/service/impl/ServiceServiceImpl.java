package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.ServiceDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Services;
import in.co.hsbc.onlineEventPlanner.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {
	private ServiceDao serviceDao;

	public ServiceServiceImpl(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	@Override
	public Services createService(Services service) throws RecordNotSavedException {

		Services services = serviceDao.createService(service);
		return services;
	}

	@Override
	public Services getServiceById(int id) throws RecordNotFetchedException {

		Services serviceById = serviceDao.getServiceById(id);
		return serviceById;
	}

	@Override
	public List<Services> getAllServices() throws RecordNotFetchedException {

		List<Services> services = serviceDao.getAllServices();

		return services;
	}

	@Override
	public ArrayList<Services> getServicesByPackageTypeId(int packageTypeId) throws RecordNotFetchedException {

		ArrayList<Services> services = serviceDao.getServicesByPackageTypeId(packageTypeId);
		return services;
	}

	@Override
	public void updateService(Services service) throws RecordNotUpdatedException {

		serviceDao.updateService(service);

	}

	@Override
	public void deleteService(int id) throws RecordNotDeletedException {

		serviceDao.deleteService(id);

	}
}

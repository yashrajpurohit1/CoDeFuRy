package in.co.hsbc.onlineEventPlanner.service.impl;

import java.sql.SQLException;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.AdminDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.TransactionNotExcecuted;
import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.model.Vendor;
import in.co.hsbc.onlineEventPlanner.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public int addVendor(Vendor v) throws SQLException, RecordNotSavedException {
		int status = -1;
		status = adminDao.addVendor(v);

		return status;
	}

	@Override
	public List<Vendor> getAllVendors() throws SQLException, RecordNotFetchedException {
		List<Vendor> vendor = adminDao.getAllVendors();
		return vendor;
	}

	@Override
	public List<User> getAllUserRegistrations() throws SQLException, RecordNotFetchedException {
		List<User> user = adminDao.getAllUserRegistrations();

		return user;
	}

	@Override
	public boolean activateUserRegistration(int userId) throws SQLException, TransactionNotExcecuted {
		boolean activated = adminDao.activateUserRegistration(userId);
		return activated;
	}
}

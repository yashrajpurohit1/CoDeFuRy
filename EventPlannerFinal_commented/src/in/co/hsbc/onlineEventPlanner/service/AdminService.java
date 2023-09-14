package in.co.hsbc.onlineEventPlanner.service;

import java.sql.SQLException;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.TransactionNotExcecuted;
import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.model.Vendor;

public interface AdminService {
	int addVendor(Vendor v) throws SQLException, RecordNotSavedException;

	List<Vendor> getAllVendors() throws SQLException, RecordNotFetchedException;

	List<User> getAllUserRegistrations() throws SQLException, RecordNotFetchedException;

	boolean activateUserRegistration(int userId) throws SQLException, TransactionNotExcecuted;
}

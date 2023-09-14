package in.co.hsbc.onlineEventPlanner.dao;

import java.sql.SQLException;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.model.User;

public interface UserDao {
	// Crud Functions of User

	User viewProfile(int userId) throws SQLException;

	boolean updateUser(int UserId, String emial, String mobileNum, String address) throws SQLException, RecordNotUpdatedException;

	boolean changePassword(int userId, String newPassword) throws SQLException, RecordNotUpdatedException;

	boolean sendPlanRequest(PlanRequest planRequest, int vendorId) throws SQLException, RecordNotFetchedException;

	List<Quotation> getAllQuotations(int userId) throws SQLException, RecordNotFetchedException;

	boolean updateQuotationStatus(int quotationId, String status) throws SQLException, RecordNotUpdatedException;
}

package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PlanRequestDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFoundException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;

//implementation of crud functions
public class PlanRequestDaoImpl implements PlanRequestDao {

	private Connection connection;

	public PlanRequestDaoImpl(Connection connection) {
		this.connection = connection;
	}
	/**
	 * This function takes a Plan Request as an argument add adds that Plan Request to planRequest_tbl
	 * if there is some issue with the argument and record is not added then it throughs
	 * a checked exception RecordNotSavedException
	 */
	@Override
	public PlanRequest createPlanRequest(PlanRequest planRequest) throws RecordNotSavedException {
		String insertQuery = "INSERT INTO plan_requests (from_date, to_date, number_of_persons) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setDate(1, new java.sql.Date(planRequest.getFromDate().getTime()));
			preparedStatement.setDate(2, new java.sql.Date(planRequest.getToDate().getTime()));
			preparedStatement.setInt(3, planRequest.getNumOfAttendent());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				planRequest.setPlanRequestId(generatedKeys.getInt(1));
			}
			else
				throw new RecordNotSavedException("Plan Request Not Created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planRequest;
	}
	/**
	 * This function return the planRequest with the given id and if the record is not 
	 * found in the database it returns a checked exception RecordNotFetchedException
	 */
	@Override
	public PlanRequest getPlanRequestById(int id) throws RecordNotFoundException {
		String selectQuery = "SELECT * FROM plan_requests WHERE request_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToPlanRequest(resultSet);
			}
			else {
				throw new RecordNotFoundException(" Plan Request with Given Id not Found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This Function Returns All planRequest from the database and if no record
	 * is found , it throughs a userdefined exception RecordNotFetchedException
	 */
	@Override
	public List<PlanRequest> getAllPlanRequests() throws RecordNotFetchedException {
		List<PlanRequest> planRequests = new ArrayList<>();
		String selectQuery = "SELECT * FROM plan_requests";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			int count=0;
			while (resultSet.next()) {
				count++;
				planRequests.add(mapResultSetToPlanRequest(resultSet));
			}
			if(count==0)throw new RecordNotFetchedException("Cant Fetch Plan Requests Right Now Try Again Later");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planRequests;
	}

	/**
	 * This function Update a planRequest in the database only if the passed id is already present in the\
	 * database otherwise it throughs a excpetion RecordNotUpdatedException 
	 */
	@Override
	public void updatePlanRequest(PlanRequest planRequest) throws RecordNotUpdatedException {
		int rows=-1;
		String updateQuery = "UPDATE plan_requests SET from_date = ?, to_date = ?, number_of_persons = ? WHERE request_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setDate(1, new java.sql.Date(planRequest.getFromDate().getTime()));
			preparedStatement.setDate(2, new java.sql.Date(planRequest.getToDate().getTime()));
			preparedStatement.setInt(3, planRequest.getNumOfAttendent());
			preparedStatement.setInt(4, planRequest.getPlanRequestId());
			rows=preparedStatement.executeUpdate();
			if(rows==-1)throw new RecordNotUpdatedException("Plane Request Not Updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This function deletes a planRequest from the database which matches with the id passed
	 * as function argument and if no such id is present in the database it will through 
	 * a checked exception RecordNotDeletedException
	 */
	@Override
	public void deletePlanRequest(int id) throws RecordNotDeletedException {
		int numOfRowsUpdated=-1;
		String deleteQuery = "DELETE FROM plan_requests WHERE request_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			numOfRowsUpdated=preparedStatement.executeUpdate();
			if(numOfRowsUpdated==-1)throw new RecordNotDeletedException("Plan Request Not Deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * this function maps Result set to the plan request , this is a intermediate function 
 * and is not called directly 
 * @param resultSet
 * @return
 * @throws SQLException
 */
	private PlanRequest mapResultSetToPlanRequest(ResultSet resultSet) throws SQLException {
		PlanRequest planRequest = new PlanRequest();
		planRequest.setPlanRequestId(resultSet.getInt("request_id"));
		planRequest.setFromDate(resultSet.getDate("from_date"));
		planRequest.setToDate(resultSet.getDate("to_date"));
		planRequest.setNumOfAttendent(resultSet.getInt("number_of_persons"));
		// You may add logic here to fetch additional information if needed
		return planRequest;
	}
/**
 * This Function associates plan request with vendor and insert the record into the databse
 */
	@Override
	public boolean associatePlanRequestWithVendor(int planRequestId, int vendorId) {
		String insertAssociationQuery = "INSERT INTO plan_request_vendor_association (plan_request_id, vendor_id) VALUES (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertAssociationQuery)) {
			preparedStatement.setInt(1, planRequestId);
			preparedStatement.setInt(2, vendorId);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.VendorDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
//implementation of crud functions

public class VendorDaoImpl implements VendorDao {

	private Connection con = null;

	public VendorDaoImpl() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanner_db", "root", "root");
		Class.forName("com.mysql.jdbc.Driver");

	}
	/**
	 * This function takes a package as an argument add adds that package to package_tbl
	 * if there is some issue with the argument and record is not added then it throughs
	 * a checked exception RecordNotSavedException
	 */
	@Override
	public boolean addPackage(Packagee vendorPackage) throws RecordNotSavedException {
		String insertQuery = "INSERT INTO eventPlanner_db.packages_tbl (type, package_type, amount) VALUES (?, ?, ?)";
		int generatedId = -1; // Initialize with a default value
		try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, vendorPackage.getPackageType());
			preparedStatement.setString(2, vendorPackage.getPackageType());
			preparedStatement.setDouble(3, vendorPackage.getAmount());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
			}
			else {
				throw new RecordNotSavedException("Package Not Added");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId > 0;
	}
	/**
	 * This Function Returns All planRequest from the database and if no record
	 * is found , it throughs a userdefined exception RecordNotFetchedException
	 */
	@Override
	public List<PlanRequest> getAllUserPlanRequests() throws RecordNotFetchedException {
		List<PlanRequest> planRequests = new ArrayList<>();
		String selectQuery = "SELECT * FROM eventPlanner_db.planRequest_tbl";
		try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			int count=0;
			while (resultSet.next()) {
				count++;
				planRequests.add(mapResultSetToPlanRequest(resultSet));
			}
			if(count==0)throw new RecordNotFetchedException("Cant Fetch Plan Requests Right Now");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planRequests;
	}
/**
 * this function maps Result set to the plan Request , this is a intermediate function 
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
		return planRequest;
	}

	/**
	 * This function takes a Quotation as an argument add adds that Quoation to quotations
	 * if there is some issue with the argument and record is not added then it throughs
	 * a checked exception RecordNotSavedException
	 */
	@Override
	public Quotation createQuotation(Quotation quotation) throws RecordNotSavedException {
		String insertQuery = "INSERT INTO eventPlanner_db.quotation_tbl (package_type_id, estimated_amount, plan_request_id, user_id, vendor_id, user_status, admin_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, quotation.getPackageType().getId());
			preparedStatement.setDouble(2, quotation.getEstimatedAmount());
			preparedStatement.setInt(3, quotation.getPlanRequestId());
			preparedStatement.setInt(4, quotation.getUserId());
			preparedStatement.setInt(5, quotation.getVendorId());
			preparedStatement.setString(6, quotation.getUserStatus());
			preparedStatement.setString(7, quotation.getAdminStatus());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				quotation.setId(generatedKeys.getInt(1));
			}
			else {
				throw new RecordNotSavedException("Quotation not created");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotation;
	}

}

package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.dao.UserDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.model.User;

//implementation of crud functions
public class UserDaoImpl implements UserDao {

	private Connection con = null;

	public UserDaoImpl() throws SQLException, ClassNotFoundException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanner_db", "root", "root");
		Class.forName("com.mysql.jdbc.Driver");

	}
	/**
	 * This Function Fetch All the User from the database and if no record is found it 
	 * Throughs a checked exception RecordNotFetchedException
	 */
	@Override
	public User viewProfile(int userId) throws SQLException {
		String sql = "SELECT * FROM eventPlanner_db.user_tbl WHERE userId=?";
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		pstmt.setInt(1, userId);
		User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
				rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
		return user;
	}
	/**
	 * This function updates the user attributes in the user_tbl table of the database 
	 * and if the passed id is not present in the database it will through a checked exception
	 * RecordNotUpdatedException
	 */
	@Override
	public boolean updateUser(int UserId, String email, String mobileNum, String address) throws SQLException, RecordNotUpdatedException {
		int numOfRowsUpdated = -1;
		String sql = "UPDATE eventPlanner_db.user_tbl SET userEmail=? , userMobileNumber=?,userLocation=? WHERE userId=?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, mobileNum);
		pstmt.setString(3, address);
		pstmt.setInt(4, UserId);
		numOfRowsUpdated = pstmt.executeUpdate();
		if(numOfRowsUpdated==-1)throw new RecordNotUpdatedException("User Not Updated");
		return numOfRowsUpdated > 0;
	}
	/**
	 * This function updates the password of the user  in the user_tbl table of the database 
	 * and if the passed id is not present in the database it will through a checked exception
	 * RecordNotUpdatedException
	 */
	@Override
	public boolean changePassword(int userId, String newPassword) throws SQLException, RecordNotUpdatedException {
		int numOfRowsUpdated = -1;
		String sql = "UPDATE eventPlanner_db.user_tbl SET userPassword=?  WHERE userId=?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, newPassword);
		pstmt.setInt(2, userId);
		numOfRowsUpdated = pstmt.executeUpdate();
		if(numOfRowsUpdated==-1)throw new RecordNotUpdatedException("Password Not Updated");
		return numOfRowsUpdated > 0;
	}
/**
 * This Function Insert Plan Request into the planRequest_tbl and if there is some issue 
 * with the input data , the function throws a userdefined exception RecordNotFetchedException
 */
	@Override
	public boolean sendPlanRequest(PlanRequest planRequest, int vendorId) throws SQLException, RecordNotFetchedException {
		int numOfRowsUpdated = -1;
		String sql = "INSERT  INTO eventPlanner_db.planRequest_tbl VALUES(?,?,?,?,?)";
		PreparedStatement pstmt;
		java.sql.Date sqlFromDate = new java.sql.Date(planRequest.getFromDate().getTime());
		java.sql.Date sqlToDate = new java.sql.Date(planRequest.getToDate().getTime());
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, planRequest.getPlanRequestId());
		pstmt.setDate(2, sqlFromDate);
		pstmt.setDate(3, sqlToDate);
		pstmt.setInt(4, planRequest.getNumOfAttendent());
		pstmt.setInt(5, vendorId);
		numOfRowsUpdated = pstmt.executeUpdate();
		if (numOfRowsUpdated==-1)throw new RecordNotFetchedException("Cant Send Plan Request Right Now");
		return numOfRowsUpdated > -1;
	}
	/**
	 * This Function Returns All Quotation from the database and if no record
	 * is found , it throughs a userdefined exception RecordNotFetchedException
	 */
	@Override
	public List<Quotation> getAllQuotations(int userId) throws SQLException, RecordNotFetchedException {
		String sql = "SELECT * FROM eventPlanner_db.quotation_tbl";
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(sql);
		ResultSet resultSet = pstmt.executeQuery();
		List<Quotation> quotationList = new ArrayList<>();
		while (resultSet.next()) {
			Quotation quotation = new Quotation();
			quotation.setId(resultSet.getInt("id"));
			quotation.setEstimatedAmount(resultSet.getDouble("estimated_amount"));
			quotation.setPlanRequestId(resultSet.getInt("plan_request_id"));
			quotation.setUserId(resultSet.getInt("user_id"));
			quotation.setVendorId(resultSet.getInt("vendor_id"));
			quotation.setUserStatus(resultSet.getString("user_status"));
			quotation.setAdminStatus(resultSet.getString("admin_status"));

			int packageTypeId = resultSet.getInt("package_type_id");
			PackageeDao packageDao = new PackageeDaoImpl(con);
			Packagee packageType = packageDao.getPackageTypeById(packageTypeId);
			quotation.setPackageType(packageType);
			quotationList.add(quotation);
		}
		return quotationList;
	}
	/**
	 * This function updates the quotation status in the quotation table of the database 
	 * and if the passed id is not present in the database it will through a checked exception
	 * RecordNotUpdatedException
	 */
	@Override
	public boolean updateQuotationStatus(int quotationId, String status) throws SQLException, RecordNotUpdatedException {
		int numOfRowsUpdated = -1;
		String sql = "UPDATE eventPlanner_db.quotation_tbl SET quotationStatus=?  WHERE quotationId=?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setInt(2, quotationId);
		numOfRowsUpdated = pstmt.executeUpdate();
		if(numOfRowsUpdated==-1)throw new RecordNotUpdatedException("Quotation Status Not Updated");
		return numOfRowsUpdated > 0;
	}

}

package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.AdminDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.TransactionNotExcecuted;
import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.model.Vendor;

//implementation of crud functions
public class AdminDaoImpl implements AdminDao {

	private Connection con = null;

	public AdminDaoImpl() throws SQLException, ClassNotFoundException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventPlanner_db", "root", "root");
		Class.forName("com.mysql.jdbc.Driver");

	}
/**
 * This Function Adds Vendor into the eventPlanner_db.vendor_tbl, Only Admin will have the privilege
 * to Add Vendor into the database
 */
	@Override
	public int addVendor(Vendor vendor) throws  RecordNotSavedException, SQLException {
		int numOfRowsUpdated = -1;
		String sql = "INSERT  INTO eventPlanner_db.vendor_tbl VALUES(?,?,?,?,?)";
		PreparedStatement pstmt;

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendor.getId());
		pstmt.setString(2, vendor.getAddress());
		pstmt.setString(3, vendor.getEmail());
		pstmt.setString(4, vendor.getContactNumber());
		pstmt.setInt(5, vendor.getEventPackages());
		numOfRowsUpdated = pstmt.executeUpdate();
		if (numOfRowsUpdated == 1)
			return numOfRowsUpdated;
		else
			throw new RecordNotSavedException("Vendor Not Saved");

	}
/**
 * This Function Fetch All the Vendors from the database and if no record is found it 
 * Throughs a checked exception RecordNotFetchedException
 */
	@Override
	public List<Vendor> getAllVendors() throws SQLException, RecordNotFetchedException {
		String sql = "SELECT * FROM eventPlanner_db.vendor_tbl";
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Vendor> vendors = new ArrayList<>();
		while (rs.next()) {
			Vendor vendor = new Vendor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			vendors.add(vendor);
		}if(vendors.size()==0)throw new RecordNotFetchedException("Cant Fetch Records, Try again letter");
		return vendors;
		
	}
/**
 * This Function Returns All User Registrations from the database and if no record
 * is found , it throughs a userdefined exception RecordNotFetchedException
 */
	@Override
	public List<User> getAllUserRegistrations() throws SQLException, RecordNotFetchedException {
		String sql = "SELECT * FROM eventPlanner_db.user_tbl";
		PreparedStatement pstmt;
		pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<User> users = new ArrayList<>();
		while (rs.next()) {
			User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
					rs.getString(11));
			users.add(user);
		}if(users.size()==0)throw new RecordNotFetchedException("Cant Fetch Records, Try again letter");
		return users;
	}
	
	/**
	 * This Function Activates the UserRegistration status from non-active to active 
	 * and if no record with given id is found it through a checked exception
	 * TransactionNotExcecuted
	 */

	@Override
	public boolean activateUserRegistration(int userId) throws SQLException, TransactionNotExcecuted {
		int numOfRowsUpdated = -1;
		String sql = "UPDATE eventPlanner_db.user_tbl SET userStatus=? WHERE userId=?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "active");
		pstmt.setInt(2, userId);
		numOfRowsUpdated = pstmt.executeUpdate();
		if(numOfRowsUpdated==-1) throw new TransactionNotExcecuted("User Not Activated");
		return numOfRowsUpdated > 0;
	}

}

package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.dao.QuotationDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.Quotation;

//implementation of crud functions
public class QuotationDaoImpl implements QuotationDao {
	private Connection connection;

	public QuotationDaoImpl(Connection connection) {
		this.connection = connection;
	}
	/**
	 * This function takes a Quotation as an argument add adds that Quoation to quotations
	 * if there is some issue with the argument and record is not added then it throughs
	 * a checked exception RecordNotSavedException
	 */
	@Override
	public Quotation createQuotation(Quotation quotation) throws RecordNotSavedException {
		String insertQuery = "INSERT INTO quotations (package_type_id, estimated_amount, plan_request_id, user_id, vendor_id, user_status, admin_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
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
				throw new RecordNotSavedException("Quotation Not Created");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotation;
	}

	/**
	 * This function return the Quotation with the given id and if the record is not 
	 * found in the database it returns a checked exception RecordNotFetchedException
	 */
	@Override
	public Quotation getQuotationById(int id) throws RecordNotFetchedException {
		String selectQuery = "SELECT * FROM quotations WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToQuotation(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This Function Returns All Quotation from the database and if no record
	 * is found , it throughs a userdefined exception RecordNotFetchedException
	 */
	@Override
	public List<Quotation> getAllQuotations() throws RecordNotFetchedException {
		List<Quotation> quotations = new ArrayList<>();
		String selectQuery = "SELECT * FROM quotations";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				quotations.add(mapResultSetToQuotation(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotations;
	}
	/**
	 * This function Update a Quotation in the database only if the passed id is already present in the\
	 * database otherwise it throughs a excpetion RecordNotUpdatedException 
	 */

	@Override
	public void updateQuotation(Quotation quotation) throws RecordNotUpdatedException {
		// TODO Auto-generated method stub
		int numOfRowsUpdated=-1;
		String updateQuery = "UPDATE quotations SET package_type_id = ?, estimated_amount = ?, plan_request_id = ?, user_id = ?, vendor_id = ?, user_status = ?, admin_status = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setInt(1, quotation.getPackageType().getId());
			preparedStatement.setDouble(2, quotation.getEstimatedAmount());
			preparedStatement.setInt(3, quotation.getPlanRequestId());
			preparedStatement.setInt(4, quotation.getUserId());
			preparedStatement.setInt(5, quotation.getVendorId());
			preparedStatement.setString(6, quotation.getUserStatus());
			preparedStatement.setString(7, quotation.getAdminStatus());
			preparedStatement.setInt(8, quotation.getId());
			numOfRowsUpdated=preparedStatement.executeUpdate();
			if(numOfRowsUpdated==-1)throw new RecordNotUpdatedException("Quotation Not Updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * This function deletes a Quotation from the database which matches with the id passed
	 * as function argument and if no such id is present in the database it will through 
	 * a checked exception RecordNotDeletedException
	 */

	@Override
	public void deleteQuotation(int id) throws RecordNotDeletedException {
		// TODO Auto-generated method stub
		int numOfRowsUpdated=-1;
		String deleteQuery = "DELETE FROM quotations WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			numOfRowsUpdated=preparedStatement.executeUpdate();
			if(numOfRowsUpdated==-1)throw new RecordNotDeletedException("Quotation not deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
/**
 * This function updates the quotation status in the quotation table of the database 
 * and if the passed id is not present in the database it will through a checked exception
 * RecordNotUpdatedException
 */
	@Override
	public void updateQuotationStatus(int id, String newStatus) throws RecordNotUpdatedException {
		// TODO Auto-generated method stub
		int numOfRowsUpdated=-1;
		String updateStatusQuery = "UPDATE quotations SET user_status = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery)) {
			preparedStatement.setString(1, newStatus);
			preparedStatement.setInt(2, id);
			numOfRowsUpdated=preparedStatement.executeUpdate();
			if(numOfRowsUpdated==-1)throw new RecordNotUpdatedException("Quotaation Status Not Updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * this function maps Result set to the Quotation , this is a intermediate function 
 * and is not called directly 
 * @param resultSet
 * @return
 * @throws SQLException
 * @throws RecordNotFetchedException
 */
	// Helper method to map ResultSet to Quotation object
	private Quotation mapResultSetToQuotation(ResultSet resultSet) throws SQLException, RecordNotFetchedException {
		Quotation quotation = new Quotation();
		quotation.setId(resultSet.getInt("id"));
		quotation.setEstimatedAmount(resultSet.getDouble("estimated_amount"));
		quotation.setPlanRequestId(resultSet.getInt("plan_request_id"));
		quotation.setUserId(resultSet.getInt("user_id"));
		quotation.setVendorId(resultSet.getInt("vendor_id"));
		quotation.setUserStatus(resultSet.getString("user_status"));
		quotation.setAdminStatus(resultSet.getString("admin_status"));

		// Assuming you have a PackageTypeDAO and a method to get PackageType by ID
		int packageTypeId = resultSet.getInt("package_type_id");
		PackageeDao packageDao = new PackageeDaoImpl(connection); // You should inject the DAO instead of
		// creating it here.
		Packagee packageType = packageDao.getPackageTypeById(packageTypeId);
		quotation.setPackageType(packageType);

		return quotation;

	}

}

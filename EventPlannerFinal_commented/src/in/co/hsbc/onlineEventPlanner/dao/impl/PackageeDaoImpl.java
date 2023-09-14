package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.dao.ServiceDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.Services;

//implementation of crud functions
public class PackageeDaoImpl implements PackageeDao {

	private Connection connection;

	public PackageeDaoImpl(Connection connection) {

		this.connection = connection;
	}
	
	/**
	 * This function return the package with the given id and if the record is not 
	 * found in the database it returns a checked exception RecordNotFetchedException
	 */

	@Override
	public Packagee getPackageTypeById(int packageTypeId) throws RecordNotFetchedException {

		String selectQuery = "SELECT * FROM package_types WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, packageTypeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToPackageType(resultSet);
			}
			else
			{
				throw new RecordNotFetchedException("Package Not Found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * This function takes a package as an argument add adds that package to package_tbl
	 * if there is some issue with the argument and record is not added then it throughs
	 * a checked exception RecordNotSavedException
	 */
	@Override
	public int addPackage(Packagee packagee) throws RecordNotSavedException {
		String insertQuery = "INSERT INTO package_tbl (type, package_type, amount) VALUES (?, ?, ?)";
		int generatedId = -1; // Initialize with a default value
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, packagee.getPackageType());
			preparedStatement.setString(2, packagee.getPackageType());
			preparedStatement.setDouble(3, packagee.getAmount());
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
		return generatedId;
	}
	/**
	 * This Function Returns All Packages from the database and if no record
	 * is found , it throughs a userdefined exception RecordNotFetchedException
	 */
	@Override
	public List<Packagee> getAllPackageTypes() throws RecordNotFetchedException {
		List<Packagee> packageTypes = new ArrayList<>();
		String selectQuery = "SELECT * FROM package_types";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			int count=0;
			while (resultSet.next()) {
				count++;
				packageTypes.add(mapResultSetToPackageType(resultSet));
			}
			if(count==0)
				throw new RecordNotFetchedException("Cant Fetch Packages , Try Again later!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return packageTypes;

	}
	/**
	 * This function Update a package in the database only if the passed id is already present in the\
	 * database otherwise it throughs a checked exception 
	 */

	@Override
	public void updatePackageType(Packagee packageType) throws RecordNotUpdatedException {
        int numOfRowsUpdated=-1;
		String updateQuery = "UPDATE package_types SET type = ?, package_type = ?, amount = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, packageType.getPackageType());
			preparedStatement.setString(2, packageType.getPackageType());
			preparedStatement.setDouble(3, packageType.getAmount());
			preparedStatement.setInt(4, packageType.getId());
		     numOfRowsUpdated=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (numOfRowsUpdated==-1)throw new RecordNotUpdatedException("Package Not Update");
	}
	/**
	 * This function deletes a package from the database which matches with the id passed
	 * as function argument and if no such id is present in the database it will through 
	 * a checked exception RecordNotDeletedException
	 */

	@Override
	public void deletePackageType(int id) throws RecordNotDeletedException {
		int numOfRowsUpdated=-1;
		String deleteQuery = "DELETE FROM package_types WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			numOfRowsUpdated=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(numOfRowsUpdated==-1)
			throw new RecordNotDeletedException("Package Not Deleted");

	}
/**
 * This Function map the result set to the package type, this is a intermediate function 
 * and is not called directly 
 * @param resultSet
 * @return
 * @throws SQLException
 * @throws RecordNotFetchedException
 */

	private Packagee mapResultSetToPackageType(ResultSet resultSet) throws SQLException, RecordNotFetchedException {
		Packagee packageType = new Packagee();
		packageType.setId(resultSet.getInt("id"));
		packageType.setPackageType(resultSet.getString("type"));
		packageType.setPackageType(resultSet.getString("package_type"));
		packageType.setAmount(resultSet.getDouble("amount"));

		ServiceDao serviceDAO = new ServiceDaoImpl(connection);
		ArrayList<Services> services = serviceDAO.getServicesByPackageTypeId(packageType.getId());
		packageType.setServices(services);

		return packageType;
	}

}

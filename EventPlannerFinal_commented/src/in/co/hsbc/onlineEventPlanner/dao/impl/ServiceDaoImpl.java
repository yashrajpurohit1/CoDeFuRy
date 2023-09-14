package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.ServiceDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Services;

//implementation of crud functions
public class ServiceDaoImpl implements ServiceDao {
	private Connection connection;

	public ServiceDaoImpl(Connection connection) {
		this.connection = connection;
	}
	/**
	 * This function takes a service as an argument add adds that service to service_tbl
	 * if there is some issue with the argument and record is not added then it throughs
	 * a checked exception RecordNotSavedException
	 */
	@Override
	public Services createService(Services service) throws RecordNotSavedException {
		int numOfRowsUpdated=-1;
		String insertQuery = "INSERT INTO services (name, cost) VALUES (?,?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, service.getId());
			preparedStatement.setString(2, service.getName());
			preparedStatement.setDouble(3, service.getAmount());
			numOfRowsUpdated=preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				service.setId(generatedKeys.getInt(1));
			}
			else {
				throw new RecordNotSavedException("Service Not Added Right Now");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return service;
	}
	/**
	 * This function return the service with the given id and if the record is not 
	 * found in the database it returns a checked exception RecordNotFetchedException
	 */
	@Override
	public Services getServiceById(int id) throws RecordNotFetchedException {
		String selectQuery = "SELECT * FROM services WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToService(resultSet);
			}
			else {
				throw new RecordNotFetchedException("Cant Fetch Services Right Now");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This Function Returns All services from the database and if no record
	 * is found , it throughs a userdefined exception RecordNotFetchedException
	 */
	@Override
	public List<Services> getAllServices() throws RecordNotFetchedException {
		List<Services> services = new ArrayList<>();
		String selectQuery = "SELECT * FROM services";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			int count=0;
			while (resultSet.next()) {
				count++;
				services.add(mapResultSetToService(resultSet));
			}
			if(count==0)throw new RecordNotFetchedException("Cant ftech All Service right now");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return services;
	}
/**
 * This function return the list of all services havee package id as passed id in the databse 
 * if no such record is present than it will through RecordNotFetchedException checked exception
 */
	@Override
	public ArrayList<Services> getServicesByPackageTypeId(int packageTypeId) throws RecordNotFetchedException {
		ArrayList<Services> services = new ArrayList<>();
		String selectQuery = "SELECT s.* FROM services s "
				+ "INNER JOIN package_type_services pts ON s.id = pts.service_id " + "WHERE pts.package_type_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, packageTypeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			int count=0;
			while (resultSet.next()) {
				count++;
				services.add(mapResultSetToService(resultSet));
			}
			if(count==0)throw new RecordNotFetchedException("No Service with given ID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return services;
	}
	/**
	 * This function Update a service in the database only if the passed id is already present in the\
	 * database otherwise it throughs a excpetion RecordNotUpdatedException 
	 */
	@Override
	public void updateService(Services service) throws RecordNotUpdatedException {
		int numOfRowsUpdated=-1;
		String updateQuery = "UPDATE services SET name = ?, cost = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, service.getName());
			preparedStatement.setDouble(2, service.getAmount());
			preparedStatement.setInt(3, service.getId());
			numOfRowsUpdated=preparedStatement.executeUpdate();
			if(numOfRowsUpdated==-1)throw new RecordNotUpdatedException("Service Not Updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This function deletes a service from the database which matches with the id passed
	 * as function argument and if no such id is present in the database it will through 
	 * a checked exception RecordNotDeletedException
	 */
	@Override
	public void deleteService(int id) throws RecordNotDeletedException {
		int numOfRowsUpdated=-1;
		String deleteQuery = "DELETE FROM services WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			numOfRowsUpdated=preparedStatement.executeUpdate();
			if(numOfRowsUpdated==-1)throw new RecordNotDeletedException("Cant Delete the Service Right Now");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * This function map result set to the service , this is a intermediate function 
 * and is not called directly 
 * @param resultSet
 * @return
 * @throws SQLException
 */
	private Services mapResultSetToService(ResultSet resultSet) throws SQLException {
		Services service = new Services();
		service.setId(resultSet.getInt("id"));
		service.setName(resultSet.getString("name"));
		service.setAmount(resultSet.getDouble("cost"));
		return service;
	}
}

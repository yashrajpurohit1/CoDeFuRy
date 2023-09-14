package in.co.hsbc.onlineEventPlanner.dao;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;

public interface PackageeDao {
	// Crud Functions of Packagee

	Packagee getPackageTypeById(int packageTypeId) throws RecordNotFetchedException;

	int addPackage(Packagee packagee) throws RecordNotSavedException;

	List<Packagee> getAllPackageTypes() throws RecordNotFetchedException;

	void updatePackageType(Packagee packageType) throws RecordNotUpdatedException;

	void deletePackageType(int id) throws RecordNotDeletedException;

}

package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.service.PackageService;

//implementation of service interface
public class PackageServiceImpl implements PackageService {
	private PackageeDao packageDao;

	public PackageServiceImpl(PackageeDao packageDao) {
		this.packageDao = packageDao;
	}

	@Override
	public Packagee getPackageTypeById(int packageTypeId) throws RecordNotFetchedException {

		Packagee packagee = packageDao.getPackageTypeById(packageTypeId);
		return packagee;
	}

	@Override
	public int addPackage(Packagee packagee) throws  RecordNotSavedException {

		int add = packageDao.addPackage(packagee);
		return add;
	}

	@Override
	public List<Packagee> getAllPackageTypes() throws RecordNotFetchedException {

		List<Packagee> packages = packageDao.getAllPackageTypes();
		return packages;
	}

	@Override
	public void updatePackageType(Packagee packageType) throws RecordNotUpdatedException {
		packageDao.updatePackageType(packageType);

	}

	@Override
	public void deletePackageType(int id) throws RecordNotDeletedException {

		packageDao.deletePackageType(id);

	}
}

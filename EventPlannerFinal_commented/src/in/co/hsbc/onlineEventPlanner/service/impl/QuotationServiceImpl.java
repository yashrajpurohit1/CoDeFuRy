package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.QuotationDao;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.service.QuotationService;

//implementation of service interface
public class QuotationServiceImpl implements QuotationService {
	private QuotationDao quotationDao;

	public QuotationServiceImpl(QuotationDao quotationDao) {
		this.quotationDao = quotationDao;
	}

	@Override
	public Quotation createQuotation(Quotation quotation) throws RecordNotSavedException {

		Quotation quotation_ = quotationDao.createQuotation(quotation);
		return quotation_;
	}

	@Override
	public Quotation getQuotationById(int id) throws RecordNotFetchedException {

		Quotation quotationById = quotationDao.getQuotationById(id);
		return quotationById;
	}

	@Override
	public List<Quotation> getAllQuotations() throws RecordNotFetchedException {

		List<Quotation> quotation = quotationDao.getAllQuotations();
		return quotation;
	}

	@Override
	public void updateQuotation(Quotation quotation) throws RecordNotUpdatedException {

		quotationDao.updateQuotation(quotation);

	}

	@Override
	public void deleteQuotation(int id) throws RecordNotDeletedException {

		quotationDao.deleteQuotation(id);

	}

	@Override
	public void updateQuotationStatus(int id, String newStatus) throws RecordNotUpdatedException {

		quotationDao.updateQuotationStatus(id, newStatus);
	}
}

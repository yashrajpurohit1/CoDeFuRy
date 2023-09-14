package in.co.hsbc.onlineEventPlanner.dao;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotDeletedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotFetchedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotSavedException;
import in.co.hsbc.onlineEventPlanner.dao.exception.RecordNotUpdatedException;
import in.co.hsbc.onlineEventPlanner.model.Quotation;

public interface QuotationDao {
	// Crud Functions of Quotation

	Quotation createQuotation(Quotation quotation) throws RecordNotSavedException;

	Quotation getQuotationById(int id) throws RecordNotFetchedException;

	List<Quotation> getAllQuotations() throws RecordNotFetchedException;

	void updateQuotation(Quotation quotation) throws RecordNotUpdatedException;

	void deleteQuotation(int id) throws RecordNotDeletedException;

	void updateQuotationStatus(int id, String newStatus) throws RecordNotUpdatedException;
}

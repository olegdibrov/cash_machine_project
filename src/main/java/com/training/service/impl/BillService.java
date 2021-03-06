package com.training.service.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.dao.impl.BillDAO;
import com.training.model.entity.Bill;
import com.training.service.Service;
import com.training.util.constants.DAOKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class BillService implements Service {
    protected static Logger logger = LogManager.getLogger(BillService.class);


    private BillDAO billDAO;

    public BillService() {
        this.billDAO = (BillDAO) DAOFactory.getDAO(DAOKey.BILL_DAO);
    }

    public List<Bill> getAllBills() {
        return billDAO.getAll();
    }

    public List<Bill> getBillsByUser(Integer idUser) {
        return billDAO.getBillsByUser(idUser);
    }

    public Integer createNewBill(LocalDate date, Integer idUser, Integer idBillStatus) {
        Bill bill = new Bill();
        bill.setDate(date);
        bill.setIdUser(idUser);
        bill.setIdBillStatus(idBillStatus);
        return billDAO.createBillAndGetId(bill);
    }

    public boolean cancelBill(Integer idBill) {
        Bill bill = billDAO.read(idBill);
        if (bill != null) {
            bill.setIdBillStatus(3);
            billDAO.update(bill);
            return true;
        }
        return false;
    }


}

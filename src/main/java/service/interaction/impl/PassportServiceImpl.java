package service.interaction.impl;

import persistence.dao.interaction.PassportDao;
import persistence.dao.interaction.impl.PassportDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import service.interaction.PassportService;

import java.util.List;

public class PassportServiceImpl implements PassportService {

    private final PassportDao passportDao;

    public PassportServiceImpl() {
        this.passportDao = new PassportDaoImpl();
    }

    @Override
    public Long create(Passport passport) {
        /*validate(passport);
        isExist(passport.getId());*/
        return passportDao.create(passport);
    }

    @Override
    public Boolean update(Passport passport) {
        /*validate(passport);
        isExist(passport.getId());*/
        return passportDao.update(passport);
    }

    @Override
    public Boolean delete(Long id) {
        /*isExist(id);*/
        return passportDao.delete(id);
    }

    @Override
    public Passport findById(Long id) {
        /*isExist(id);*/
        return passportDao.findById(id);
    }

    @Override
    public List<Passport> findAll() {
        return passportDao.findAll();
    }

    @Override
    public DataTableResponse<Passport> findAll(DataTableRequest request) {
        DataTableResponse<Passport> passports = passportDao.findAll(request);
        passports.setItemsSize(passportDao.count());
        return passports;
    }

    private void validate(Passport passport) {
        //validations
        //exception if failed
    }

    private void isExist(Long id) {
        if (!passportDao.existById(id)) {
            throw new RuntimeException("entity not found");
        }
    }
}
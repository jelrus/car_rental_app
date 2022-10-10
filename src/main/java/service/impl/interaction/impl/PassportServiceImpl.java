package service.impl.interaction.impl;

import persistence.dao.impl.interaction.PassportDao;
import persistence.dao.impl.interaction.impl.PassportDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import service.impl.interaction.PassportService;

public class PassportServiceImpl implements PassportService {

    private final PassportDao passportDao;

    public PassportServiceImpl() {
        this.passportDao = new PassportDaoImpl();
    }

    @Override
    public long create(Passport passport) {
        return passportDao.create(passport);
    }

    @Override
    public boolean update(Passport passport) {
        return passportDao.update(passport);
    }

    @Override
    public boolean delete(Long id) {
        return passportDao.delete(id);
    }

    @Override
    public Passport findById(Long id) {
        return passportDao.findById(id);
    }

    @Override
    public DataTableResponse<Passport> findAll(DataTableRequest request) {
        return passportDao.findAll(request);
    }
}

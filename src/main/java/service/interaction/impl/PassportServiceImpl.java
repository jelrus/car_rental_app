package service.interaction.impl;

import exceptions.EntityNotFoundException;
import exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.interaction.PassportDao;
import persistence.dao.interaction.impl.PassportDaoImpl;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import service.interaction.PassportService;

import java.util.List;

public class PassportServiceImpl implements PassportService {

    private final PassportDao passportDao;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public PassportServiceImpl(PassportDao passportDao) {
        this.passportDao = passportDao;
    }

    @Override
    public Long create(Passport passport) {
        LOGGER_INFO.info("Passport creating started");
        validate(passport);
        Long passportId = passportDao.create(passport);
        LOGGER_INFO.info("Passport " + passportId +  " creating finished");
        return passportId;
    }

    @Override
    public Boolean update(Passport passport) {
        LOGGER_INFO.info("Passport " + passport.getId() + " updating started");

        if (exist(passport)) {
            validate(passport);
            LOGGER_INFO.info("Passport " + passport.getId() +  " updating finished");
        }

        return passportDao.update(passport);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER_WARNING.warn("Passport " + id +  " deleting started");

        if (exist(findById(id))) {
            LOGGER_WARNING.warn("Passport " + id +  " deleting finished");
        }

        return passportDao.delete(id);
    }

    @Override
    public Passport findById(Long id) {
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
        if (passport.getFirstName().isEmpty() || passport.getFirstName().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank first name");
            throw new InputException("Incorrect Input");
        }

        if (passport.getLastName().isEmpty() || passport.getLastName().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank last name");
            throw new InputException("Incorrect Input");
        }

        if (passport.getBirthDate().toString().isEmpty() || passport.getBirthDate().toString().isBlank() ||
            passport.getBirthDate() == null) {
            LOGGER_WARNING.warn("Empty/blank/null birth date");
            throw new InputException("Incorrect Input");
        }

        if (passport.getCountry().isEmpty() || passport.getCountry().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank country");
            throw new InputException("Incorrect Input");
        }

        if (passport.getZipCode().isEmpty() || passport.getZipCode().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank zip code");
            throw new InputException("Incorrect Input");
        }

        if (passport.getRegion().isEmpty() || passport.getRegion().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank region");
            throw new InputException("Incorrect Input");
        }

        if (passport.getCity().isEmpty() || passport.getCity().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank city");
            throw new InputException("Incorrect Input");
        }

        if (passport.getStreet().isEmpty() || passport.getStreet().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank street");
            throw new InputException("Incorrect Input");
        }

        if (passport.getBuilding().isEmpty() || passport.getBuilding().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank building");
            throw new InputException("Incorrect Input");
        }

        if (passport.getPhoneNumber().isEmpty() || passport.getPhoneNumber().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank phone number");
            throw new InputException("Incorrect Input");
        }

        if (passport.getEmail().isEmpty() || passport.getEmail().isBlank()) {
            LOGGER_WARNING.warn("Empty or blank email");
            throw new InputException("Incorrect Input");
        }
    }

    private boolean exist(Passport passport) {
        if (findById(passport.getId()) != null) {
            return true;
        } else {
            LOGGER_WARNING.warn("Passport not found");
            throw new EntityNotFoundException("Passport not found");
        }
    }
}
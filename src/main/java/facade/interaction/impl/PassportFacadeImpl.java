package facade.interaction.impl;

import facade.interaction.PassportFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import service.impl.interaction.PassportService;
import service.impl.interaction.impl.PassportServiceImpl;
import util.DtoConverter;
import util.RequestUtil;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.request.interaction.PassportDtoRequest;
import view.dto.response.PageData;
import view.dto.response.interaction.PassportDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class PassportFacadeImpl implements PassportFacade {

    private final PassportService passportService;

    public PassportFacadeImpl() {
        this.passportService = new PassportServiceImpl();
    }

    @Override
    public long create(PassportDtoRequest passportDtoRequest) {
        Passport passport = new Passport();
        dtoToPassport(passport, passportDtoRequest);
        return passportService.create(passport);
    }

    @Override
    public boolean update(PassportDtoRequest passportDtoRequest, Long id) {
        Passport passport = passportService.findById(id);
        dtoToPassport(passport, passportDtoRequest);
        return passportService.update(passport);
    }

    @Override
    public boolean delete(Long id) {
        return passportService.delete(id);
    }

    @Override
    public PassportDtoResponse findById(Long id) {
        return new PassportDtoResponse(passportService.findById(id));
    }

    @Override
    public PageData<PassportDtoResponse> findAll(HttpServletRequest request) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(request);
        SortData sortData = RequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Passport> passports = passportService.findAll(dataTableRequest);
        List<PassportDtoResponse> responseList = toDtoList(passports);
        PageData<PassportDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(passports.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void dtoToPassport(Passport passport, PassportDtoRequest passportDtoRequest) {
        passport.setFirstName(passportDtoRequest.getFirstName());
        passport.setLastName(passportDtoRequest.getLastName());
        passport.setAge(passportDtoRequest.getAge());
        passport.setCountry(passportDtoRequest.getCountry());
        passport.setZipCode(passport.getZipCode());
        passport.setRegion(passport.getRegion());
        passport.setCity(passport.getCity());
        passport.setStreet(passport.getStreet());
        passport.setBuilding(passport.getBuilding());
        passport.setPhoneNumber(passport.getPhoneNumber());
        passport.setEmail(passport.getEmail());
    }

    private List<PassportDtoResponse> toDtoList(DataTableResponse<Passport> passports) {
        return passports.getItems().stream()
                                   .map(PassportDtoResponse::new)
                                   .collect(Collectors.toList());
    }
}

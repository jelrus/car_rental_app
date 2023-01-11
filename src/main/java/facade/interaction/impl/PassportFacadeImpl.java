package facade.interaction.impl;

import facade.interaction.PassportFacade;
import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.interaction.Passport;
import service.interaction.PassportService;
import service.interaction.impl.PassportServiceImpl;
import util.facade.DtoConverter;
import util.request.RequestUtil;
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

    public PassportFacadeImpl(PassportService passportService) {
        this.passportService = passportService;
    }

    @Override
    public Long create(PassportDtoRequest dtoReq) {
        Passport passport = new Passport();
        passportSetFields(passport, dtoReq);
        return passportService.create(passport);
    }

    @Override
    public Boolean update(PassportDtoRequest dtoReq, Long id) {
        Passport passport = passportService.findById(id);
        passportSetFields(passport, dtoReq);
        return passportService.update(passport);
    }

    @Override
    public Boolean delete(Long id) {
        return passportService.delete(id);
    }

    @Override
    public PassportDtoResponse findById(Long id) {
        return new PassportDtoResponse(passportService.findById(id));
    }

    @Override
    public List<PassportDtoResponse> findAll() {
        return passportService.findAll().stream().map(PassportDtoResponse::new).collect(Collectors.toList());
    }

    @Override
    public PageData<PassportDtoResponse> findAll(HttpServletRequest req) {
        PageAndSizeData pageAndSizeData = RequestUtil.generatePageAndSizeData(req);
        SortData sortData = RequestUtil.generateSortData(req);
        DataTableRequest dataTableRequest = DtoConverter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Passport> passports = passportService.findAll(dataTableRequest);
        List<PassportDtoResponse> responseList = toDtoList(passports);
        PageData<PassportDtoResponse> pageData = DtoConverter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(passports.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private void passportSetFields(Passport passport, PassportDtoRequest dto) {
        passport.setFirstName(dto.getFirstName());
        passport.setLastName(dto.getLastName());
        passport.setBirthDate(dto.getBirthDate());
        passport.setCountry(dto.getCountry());
        passport.setZipCode(dto.getZipCode());
        passport.setRegion(dto.getRegion());
        passport.setCity(dto.getCity());
        passport.setStreet(dto.getStreet());
        passport.setBuilding(dto.getBuilding());
        passport.setPhoneNumber(dto.getPhoneNumber());
        passport.setEmail(dto.getEmail());
    }

    private List<PassportDtoResponse> toDtoList(DataTableResponse<Passport> actions) {
        return actions.getItems().stream().map(PassportDtoResponse::new).collect(Collectors.toList());
    }
}
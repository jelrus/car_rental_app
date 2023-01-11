package util.controller;

import facade.product.CarFacade;
import facade.product.impl.CarFacadeImpl;
import persistence.dao.product.impl.CarDaoImpl;
import persistence.entity.product.type.CarBrand;
import persistence.entity.product.type.CarQuality;
import service.product.impl.CarServiceImpl;
import view.controller.AbstractServlet;
import view.dto.request.product.CarDtoRequest;
import view.dto.response.PageData;
import view.dto.response.product.CarDtoResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarControllerUtil extends AbstractServlet {

    private final CarFacade carFacade = new CarFacadeImpl(new CarServiceImpl(new CarDaoImpl()));

    public void createCarGet(HttpServletRequest req) {
        List<CarBrand> brand = Arrays.stream(CarBrand.values()).collect(Collectors.toList());
        List<CarQuality> quality = Arrays.stream(CarQuality.values()).collect(Collectors.toList());
        req.setAttribute("brand", brand);
        req.setAttribute("quality", quality);
    }

    public void createCarPost(HttpServletRequest req) {
        CarDtoRequest carReq = new CarDtoRequest();
        setupCarDto(req, carReq);
        carReq.setEnabled(true);
        carFacade.create(carReq);
    }

    public void deleteCarPost(HttpServletRequest req) {
        carFacade.delete(Long.parseLong(req.getParameter("id")));
    }

    public void editCarGet(HttpServletRequest req) {
        CarDtoResponse car = carFacade.findById(Long.parseLong(req.getParameter("id")));
        List<CarBrand> brand = Arrays.stream(CarBrand.values()).collect(Collectors.toList());
        List<CarQuality> quality = Arrays.stream(CarQuality.values()).collect(Collectors.toList());
        req.setAttribute("brand", brand);
        req.setAttribute("quality", quality);
        req.setAttribute("car", car);
    }

    public CarDtoResponse editCarPost(HttpServletRequest req) {
        CarDtoRequest carReq = new CarDtoRequest();
        CarDtoResponse carResp = carFacade.findById(Long.parseLong(req.getParameter("id")));
        setupCarDto(req, carReq);
        carReq.setEnabled(carResp.getEnabled());
        carFacade.update(carReq, carResp.getId());
        return carResp;
    }

    public void hideCarPost(HttpServletRequest req) {
        CarDtoRequest carDtoRequest = new CarDtoRequest();
        CarDtoResponse carDtoResponse = carFacade.findById(Long.parseLong(req.getParameter("id")));
        carDtoRequest.setTitle(carDtoResponse.getTitle());
        carDtoRequest.setProductPic(carDtoResponse.getProductPic());
        carDtoRequest.setCarBrand(carDtoRequest.getCarBrand());
        carDtoRequest.setCarQuality(carDtoRequest.getCarQuality());
        carDtoRequest.setInfo(carDtoRequest.getInfo());
        carDtoRequest.setRentalPrice(carDtoRequest.getRentalPrice());
        carDtoRequest.setEnabled(!carDtoResponse.getEnabled());
        carFacade.updateAccess(carDtoRequest, carDtoResponse.getId());
    }

    public void infoCarGet(HttpServletRequest req) {
        CarDtoResponse carResponse = carFacade.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("car", carResponse);
    }

    public void carsGetAdmin(HttpServletRequest req) {
        PageData<CarDtoResponse> cars = carFacade.findAll(req);
        AbstractServlet.HeaderName[] columnNames = getColumnNamesForCars();
        List<HeaderData> headerData = getHeaderDataList(columnNames, cars);
        req.setAttribute("headerDataList", headerData);
        req.setAttribute( "createUrl", "/admin/product/cars");
        req.setAttribute("cardHeader", "cars");
        req.setAttribute("pageData", cars);
    }

    private AbstractServlet.HeaderName[] getColumnNamesForCars() {
        return new AbstractServlet.HeaderName[]{
                new AbstractServlet.HeaderName("Created", "created", "created"),
                new AbstractServlet.HeaderName("Updated", "updated", "updated"),
                new AbstractServlet.HeaderName("Title", "title", "title"),
                new AbstractServlet.HeaderName("Brand", "brand", "brand"),
                new AbstractServlet.HeaderName("Quality", "quality", "quality"),
                new AbstractServlet.HeaderName("Info", "info", "info"),
                new AbstractServlet.HeaderName("Rental Price", "rentalPrice", "rental_price"),
                new AbstractServlet.HeaderName("Details", null, null),
                new AbstractServlet.HeaderName("Disable", null, null),
                new AbstractServlet.HeaderName("Delete", null, null)
        };
    }

    private void setupCarDto(HttpServletRequest req, CarDtoRequest carDto) {
        carDto.setTitle(req.getParameter("title"));
        carDto.setProductPic(req.getParameter("productPic"));
        carDto.setRentalPrice(new BigDecimal(req.getParameter("rentalPrice")));
        carDto.setInfo(req.getParameter("info"));
        carDto.setCarBrand(CarBrand.valueOf(req.getParameter("brand")));
        carDto.setCarQuality(CarQuality.valueOf(req.getParameter("quality")));
    }
}

package facade;

import view.dto.request.DtoRequest;
import view.dto.response.DtoResponse;
import view.dto.response.PageData;

import javax.servlet.http.HttpServletRequest;

public interface BaseFacade<REQ extends DtoRequest, RESP extends DtoResponse> {

    long create(REQ req);

    boolean update(REQ req, Long id);

    boolean delete(Long id);

    RESP findById(Long id);

    PageData<RESP> findAll(HttpServletRequest request);
}

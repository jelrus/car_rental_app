package facade;

import view.dto.request.DtoRequest;
import view.dto.response.DtoResponse;
import view.dto.response.PageData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaseFacade<REQ extends DtoRequest, RESP extends DtoResponse> {

    Long create(REQ dtoReq);

    Boolean update(REQ dtoReq, Long id);

    Boolean delete(Long id);

    RESP findById(Long id);

    List<RESP> findAll();

    PageData<RESP> findAll(HttpServletRequest req);
}
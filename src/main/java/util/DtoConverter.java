package util;

import persistence.datatable.DataTableRequest;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;
import view.dto.response.DtoResponse;
import view.dto.response.PageData;

import java.util.List;

public final class DtoConverter {

    private DtoConverter() {
    }

    public static DataTableRequest pageAndSortDataToDtoReq(PageAndSizeData pageAndSizeData, SortData sortData) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setOrder(sortData.getOrder());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        return dataTableRequest;
    }

    public static <RES extends DtoResponse> PageData<RES> dtoRespToPageAndSortData(List<RES> items,
                                                                                   PageAndSizeData pageAndSizeData,
                                                                                   SortData sortData) {
        PageData<RES> pageData = new PageData<>();
        pageData.setItems(items);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        return pageData;
    }
}

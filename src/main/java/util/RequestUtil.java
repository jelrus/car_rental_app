package util;

import org.apache.commons.lang3.StringUtils;
import view.dto.request.PageAndSizeData;
import view.dto.request.SortData;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public final class RequestUtil {

    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";
    public static final String DEFAULT_SORT_PARAM_VALUE = "created";
    public static final String DEFAULT_ORDER_PARAM_VALUE = "asc";
    public static final int DEFAULT_PAGE_PARAM_VALUE = 1;
    public static final int DEFAULT_SIZE_PARAM_VALUE = 10;

    private RequestUtil() {
    }

    public static PageAndSizeData generatePageAndSizeData(HttpServletRequest req) {
        int page = req.getParameter(PAGE_PARAM) != null ?
                Integer.parseInt(Objects.requireNonNull(req.getParameter(PAGE_PARAM))) :
                DEFAULT_PAGE_PARAM_VALUE;
        int size = req.getParameter(SIZE_PARAM) != null ?
                Integer.parseInt(Objects.requireNonNull(req.getParameter(SIZE_PARAM))) :
                DEFAULT_SIZE_PARAM_VALUE;
        return new PageAndSizeData(page, size);
    }

    public static PageAndSizeData defaultPageAndSizeData() {
        return new PageAndSizeData(DEFAULT_PAGE_PARAM_VALUE, DEFAULT_SIZE_PARAM_VALUE);
    }

    public static SortData generateSortData(HttpServletRequest webRequest) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ?
                Objects.requireNonNull(webRequest.getParameter(SORT_PARAM)) :
                DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ?
                Objects.requireNonNull(webRequest.getParameter(ORDER_PARAM)) :
                DEFAULT_ORDER_PARAM_VALUE;
        return new SortData(sort, order);
    }

    public static SortData defaultSortData() {
        return new SortData(DEFAULT_SORT_PARAM_VALUE, DEFAULT_ORDER_PARAM_VALUE);
    }
}

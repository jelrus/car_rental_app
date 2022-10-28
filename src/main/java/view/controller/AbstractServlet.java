package view.controller;

import org.apache.commons.lang3.StringUtils;
import view.dto.response.DtoResponse;
import view.dto.response.PageData;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.request.RequestUtil.DEFAULT_ORDER_PARAM_VALUE;

public class AbstractServlet extends HttpServlet {

    public static class HeaderName {

        private String columnName;
        private String tableName;
        private String dbName;

        public HeaderName(String columnName, String tableName, String dbName) {
            this.columnName = columnName;
            this.tableName = tableName;
            this.dbName = dbName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }
    }

    public static class HeaderData {

        private String headerName;
        private boolean active;
        private boolean sortable;
        private String sort;
        private String order;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public boolean isSortable() {
            return sortable;
        }

        public void setSortable(boolean sortable) {
            this.sortable = sortable;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        @Override
        public String toString() {
            return "HeaderData{" +
                    "headerName='" + headerName + '\'' +
                    ", active=" + active +
                    ", sortable=" + sortable +
                    ", sort='" + sort + '\'' +
                    ", order='" + order + '\'' +
                    '}';
        }
    }

    public String createURL(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        StringBuilder sb = new StringBuilder("?");
        for (Map.Entry<String, String[]> m: parameterMap.entrySet()) {
            sb.append(m.getKey()).append("=").append(m.getValue()[0]).append("&");
        }
        return sb.toString();
    }

    public  <RES extends DtoResponse> List<HeaderData> getHeaderDataList(HeaderName[] columnNames, PageData<RES> response) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getDbName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        return headerDataList;
    }
}
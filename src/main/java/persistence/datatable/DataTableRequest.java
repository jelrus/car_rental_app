package persistence.datatable;

import java.util.ArrayList;
import java.util.List;

public class DataTableRequest {

    private String sort;
    private String order;
    private List<String> brandFilter = new ArrayList<>();
    private List<String> qualityFilter = new ArrayList<>();
    private int currentPage;
    private int pageSize;

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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setBrandFilter(List<String> brandFilter) {
        this.brandFilter = brandFilter;
    }

    public void setQualityFilter(List<String> qualityFilter) {
        this.qualityFilter = qualityFilter;
    }

    public String getCurrentFilters() {
        StringBuilder fb = new StringBuilder();

        if (!brandFilter.isEmpty() || !qualityFilter.isEmpty()) {
            fb.append(" AND (");

            if (!brandFilter.isEmpty()) {

                for (int i = 0; i < brandFilter.size(); i++) {
                    if (i != brandFilter.size() - 1) {
                        fb.append("brand=").append(brandFilter.get(i)).append(" or ");
                    } else {
                        fb.append("brand=").append(brandFilter.get(i)).append(")");
                        if (!qualityFilter.isEmpty()) {
                            fb.append(" AND (");
                        }
                    }
                }
            }

            if (!qualityFilter.isEmpty()) {
                for (int i = 0; i < qualityFilter.size(); i++) {
                    if (i != qualityFilter.size() - 1) {
                        fb.append("quality=").append(qualityFilter.get(i)).append(" or ");
                    } else {
                        fb.append("quality=").append(qualityFilter.get(i)).append(") ");
                    }
                }
            }
        }

        return fb.toString();
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "DataTableRequest{" +
                "sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
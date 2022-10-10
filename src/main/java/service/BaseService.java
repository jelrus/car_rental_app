package service;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.BaseEntity;

public interface BaseService<E extends BaseEntity> {

    long create(E entity);

    boolean update(E entity);

    boolean delete(Long id);

    E findById(Long id);

    DataTableResponse<E> findAll(DataTableRequest request);
}

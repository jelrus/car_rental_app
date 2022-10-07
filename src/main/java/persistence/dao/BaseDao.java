package persistence.dao;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.BaseEntity;

public interface BaseDao<E extends BaseEntity> {

    boolean create(E entity);

    boolean update(E entity);

    boolean delete(Long id);

    boolean existById(Long id);

    E findById(Long id);

    DataTableResponse<E> findAll(DataTableRequest request);

    long count();
}
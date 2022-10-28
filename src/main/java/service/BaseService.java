package service;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {

    Long create(E entity);

    Boolean update(E entity);

    Boolean delete(Long id);

    E findById(Long id);

    List<E> findAll();

    DataTableResponse<E> findAll(DataTableRequest request);
}
package persistence.dao;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.util.List;

public interface BaseDao<E extends BaseEntity> {

    Long create(E entity);

    Boolean update(E entity);

    Boolean delete(Long id);

    Boolean existById(Long id);

    E findById(Long id);

    List<E> findAll();

    DataTableResponse<E> findAll(DataTableRequest request);

    Integer count();

    Long generateKeys(PreparedStatement ps);
}
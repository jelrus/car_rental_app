package service.impl.user;

import persistence.datatable.DataTableRequest;
import persistence.datatable.DataTableResponse;
import persistence.entity.BaseEntity;

public interface UserService<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(Long id);

    E findById(Long id);

    DataTableResponse<E> findAll(DataTableRequest request);
}

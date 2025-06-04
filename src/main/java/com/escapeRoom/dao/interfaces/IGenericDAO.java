package com.escapeRoom.dao.interfaces;

import java.util.List;
import java.util.Optional;

   public interface IGenericDAO<T, ID> {
        boolean create(T entity);
        Optional<T> findById(ID id);
        boolean update(T entity);
        boolean deleteById(ID id);
        List<T> findAll();
        boolean existsById(ID id);
    }


package org.album.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    T save(T t);

    T update(T t);

    List<T> findAll();

    Optional<T> findById(int id);

    void delete(int id);

    List<Integer> getIdList();

}

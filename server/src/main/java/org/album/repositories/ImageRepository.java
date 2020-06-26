package org.album.repositories;

import org.album.domains.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select i.id from image i",
            nativeQuery = true)
    List<Long> getAllId();
}

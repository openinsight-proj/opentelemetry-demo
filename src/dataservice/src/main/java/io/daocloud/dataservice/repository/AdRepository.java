package io.daocloud.dataservice.repository;

import io.daocloud.dataservice.entity.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface AdRepository extends CrudRepository<Ad, Integer> {
    Iterable<Ad> findByAdKey(@Param("adKey") String adKey);
}

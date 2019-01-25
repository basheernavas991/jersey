package com.jazeera.jersey.security.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.UserMeta;

@Repository
public interface UserMetaRepository extends DataTablesRepository<UserMeta, Integer>{

}

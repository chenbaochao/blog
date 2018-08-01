package com.cbc.myblog.admin.repository;


import com.cbc.myblog.admin.domain.Catalog;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {


}

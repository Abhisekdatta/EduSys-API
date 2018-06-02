package com.edcs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.edcs.model.EntityModel;

public interface EntityRepository extends JpaRepository<EntityModel, Integer>{

	@Transactional
	@Modifying
    @Query("UPDATE EntityModel e SET e.isActive = :status WHERE e.id = :entityId")
    int updateEntityStatus(@Param("entityId") int entityId, @Param("status") boolean status);
}

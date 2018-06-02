package com.edcs.web.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edcs.model.PageModel;
import com.edcs.model.RoleModel;

public interface PageRepository extends JpaRepository<PageModel,Integer>{

	public Set<PageModel>findByName(String name);
	public Set<PageModel>findByRoleMapPageModelList(RoleModel roleModel);
}

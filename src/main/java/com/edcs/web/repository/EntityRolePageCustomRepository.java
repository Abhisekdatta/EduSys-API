package com.edcs.web.repository;

import java.util.Set;

import com.edcs.model.EntityRolePageModel;
import com.edcs.model.RoleModel;

public interface EntityRolePageCustomRepository {

	public Set<EntityRolePageModel>findEntityRolePageModelByEntityIdAndRoleSet(int entityId,Set<RoleModel>roles);
}

package com.edcs.web.repository.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edcs.model.EntityRolePageModel;
import com.edcs.model.RoleModel;
import com.edcs.web.repository.EntityRolePageCustomRepository;

@Repository("entityRolePageCustomRepository")
public class EntityRolePageCustomRepositoryImpl implements EntityRolePageCustomRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<EntityRolePageModel> findEntityRolePageModelByEntityIdAndRoleSet(int entityId, Set<RoleModel> roles) {
		
		try{
			Set<Integer>roleIdList=new HashSet<Integer>();
			for(RoleModel role:roles){
				roleIdList.add(role.getId());
			}
			Set<EntityRolePageModel> entityRolePageModelList=new HashSet<EntityRolePageModel>(
											   sessionFactory.getCurrentSession().createCriteria(EntityRolePageModel.class,"entityRolePageModel")
			                                  .createAlias("entityRolePageModel.entityModel", "entityModel")
			                                  .createAlias("entityRolePageModel.roleModel", "roleModel")
			                                  .add(Restrictions.eq("entityModel.id", entityId))
			                                  .add(Restrictions.in("roleModel.id",roleIdList))
			                                  .list());
			System.out.println(entityRolePageModelList.size());
			return entityRolePageModelList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}

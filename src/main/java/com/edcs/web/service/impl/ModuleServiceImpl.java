package com.edcs.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edcs.bean.ModuleBean;
import com.edcs.bean.OperationsBean;
import com.edcs.bean.PageBean;
import com.edcs.model.EntityModel;
import com.edcs.model.EntityRolePageModel;
import com.edcs.model.ModuleModel;
import com.edcs.model.OperationModel;
import com.edcs.model.PageModel;
import com.edcs.model.RoleModel;
import com.edcs.security.model.User;
import com.edcs.security.repository.UserRepository;
import com.edcs.web.repository.EntityRolePageCustomRepository;
import com.edcs.web.repository.ModuleRepository;
import com.edcs.web.repository.PageRepository;
import com.edcs.web.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityRolePageCustomRepository entityRolePageCustomRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<ModuleBean> findModuleBasedOnUserName(String userName) {
		
		
		User userModel=userRepository.findByUsername(userName);
		
		if(null!=userModel && userModel.getEnabled()){
			Set<EntityModel>entityModelList=userModel.getEntityModelList();
			Set<RoleModel>roleModelList=userModel.getRoleMapUserModelList();
			
			if(null!=entityModelList && !entityModelList.isEmpty()){
				return findModulesByEntityAndRoles(entityModelList,roleModelList);
			}else{
				return findModulesByRoles(roleModelList);
			}
		}
		return new ArrayList<ModuleBean>();
	}
	
	private List<ModuleBean>findModulesByRoles(Set<RoleModel> roleModelList){
		Set<PageModel>finalPageModelList=new HashSet<PageModel>();
		if(null!=roleModelList && !roleModelList.isEmpty()){
			for(RoleModel roleModel:roleModelList){
				Set<PageModel>pageModelList=pageRepository.findByRoleMapPageModelList(roleModel);
				finalPageModelList.addAll(pageModelList);
			}
			return getModuleBeanList(finalPageModelList);
		}
		return new ArrayList<ModuleBean>();
	}
	
	private List<ModuleBean>findModulesByEntityAndRoles(Set<EntityModel> entityModelList , Set<RoleModel> roleModelList){
		Set<PageModel>pageModelList=new HashSet<PageModel>();
		for(EntityModel entityModel:entityModelList){
			if(entityModel.isActive()){
				Set<EntityRolePageModel>entityRolePageModelList=entityRolePageCustomRepository
															   .findEntityRolePageModelByEntityIdAndRoleSet(entityModel.getId(), 
																	   										roleModelList);
				for(EntityRolePageModel entityRolePageModel:entityRolePageModelList){
					pageModelList.add(entityRolePageModel.getPage());
				}
				return getModuleBeanList(pageModelList);
			}
		}
		return new ArrayList<ModuleBean>();
	}
	
	
	
	private List<ModuleBean>getModuleBeanList(Set<PageModel>pageModelList){
		
		Map<Integer ,List<PageModel>>modulePageMap=new HashMap<Integer ,List<PageModel>>();
		for(PageModel pageModel:pageModelList){
			if(modulePageMap.containsKey(pageModel.getModule().getId())){
				List<PageModel>pageModelInnerList=modulePageMap.get(pageModel.getModule().getId());
				pageModelInnerList.add(pageModel);
				modulePageMap.put(pageModel.getModule().getId(), pageModelInnerList);
			}else{
				List<PageModel>pageModelInnerList=new ArrayList<PageModel>();
				pageModelInnerList.add(pageModel);
				modulePageMap.put(pageModel.getModule().getId(), pageModelInnerList);
			}
		}
		
		List<ModuleBean>moduleBeanList=new ArrayList<ModuleBean>();
		for(Map.Entry<Integer, List<PageModel>>entry:modulePageMap.entrySet()){
			ModuleBean moduleBean=new ModuleBean();
			List<PageModel> pages=entry.getValue();
			ModuleModel moduleModel=pages.get(0).getModule();
			
			List<PageBean>pageBeanList=new ArrayList<PageBean>();
			
			for(PageModel pageModel:pages){
				PageBean pageBean=new PageBean();
				BeanUtils.copyProperties(pageModel, pageBean);
				
				List<OperationsBean>operationBeanList=new ArrayList<OperationsBean>();
				for(OperationModel operationModel:pageModel.getOperations()){
					OperationsBean operationsBean=new OperationsBean();
					BeanUtils.copyProperties(operationModel, operationsBean);
					operationBeanList.add(operationsBean);
				}
				pageBean.setOperationBeanList(operationBeanList);
				pageBeanList.add(pageBean);
			}
			
			BeanUtils.copyProperties(moduleModel, moduleBean);
			moduleBean.setPageModuleBeanList(pageBeanList);
			moduleBeanList.add(moduleBean);
		}
		return moduleBeanList;
	}

}

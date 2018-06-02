package com.edcs.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edcs.bean.EntityBean;
import com.edcs.model.EntityModel;
import com.edcs.security.service.UserAuthServiceImpl;
import com.edcs.web.repository.EntityRepository;

@RestController
public class EntityController {
	
	@Autowired
	private UserAuthServiceImpl userAuthService;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@GetMapping(value="api/entity/{entityId}")
	public EntityBean findOne(HttpServletRequest request,
							  @PathVariable ("entityId") Integer entityId){
		if(userAuthService.validateUser(request)){
			EntityBean entityBean=new EntityBean();
			EntityModel entityModel=entityRepository.findOne(entityId);
			BeanUtils.copyProperties(entityModel, entityBean);
			return entityBean;
		}
		return null;
	}
	
	@GetMapping(value="api/pageableEntities")
	public Page<EntityBean>findAllEntity(HttpServletRequest request, Pageable pageable){
		if(userAuthService.validateUser(request)){
			Page<EntityModel>entityModelList=entityRepository.findAll(pageable);
			List<EntityBean>entityBeanList=null;
			for(EntityModel EntityModel:entityModelList.getContent()){
				EntityBean entityBean=new EntityBean();
				BeanUtils.copyProperties(EntityModel, entityBean);
				if(null==entityBeanList){
					entityBeanList=new ArrayList<EntityBean>();
				}
				entityBeanList.add(entityBean);
			}
			Page<EntityBean> page=new PageImpl<EntityBean>(entityBeanList, 
					                                      new PageRequest(pageable.getPageNumber(), pageable.getPageSize()), 
					                                      entityModelList.getTotalElements());
			
			System.out.println("size of entity="+page.getSize());
			return page;
		}else{
			return null;
		}
		
	}
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@GetMapping(value="api/entities")
	public List<EntityBean>findAllEntity(HttpServletRequest request){
		if(userAuthService.validateUser(request)){
			List<EntityModel>entityModelList=entityRepository.findAll();
			List<EntityBean>entityBeanList=null;
			for(EntityModel EntityModel:entityModelList){
				EntityBean entityBean=new EntityBean();
				BeanUtils.copyProperties(EntityModel, entityBean);
				if(null==entityBeanList){
					entityBeanList=new ArrayList<EntityBean>();
				}
				entityBeanList.add(entityBean);
			}
			return entityBeanList;
		}
		return null;
	}
	
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@PostMapping(value="api/entity")
	public EntityBean saveEntity(@RequestBody EntityBean enityBean,HttpServletRequest request){
		EntityModel entityModel=new EntityModel();
		BeanUtils.copyProperties(enityBean, entityModel);
		if(userAuthService.validateUser(request)){
			entityModel.setEntityType("S");
			entityModel=entityRepository.save(entityModel);
			BeanUtils.copyProperties(entityModel, enityBean);
			return enityBean;
		}
		return null;
	}
	
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@PutMapping(value="api/entity/{entityId}/{status}")
	public Integer updateEntityStatus(@PathVariable ("entityId") Integer entityId,
			                       @PathVariable ("status") boolean status,
			                       HttpServletRequest request){
	
		if(userAuthService.validateUser(request)){
			return entityRepository.updateEntityStatus(entityId, status);
		}
		return null;
	}
	
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@DeleteMapping(value="api/entity/{entityId}")
	public void deleteEntity(@PathVariable ("entityId") Integer entityId,HttpServletRequest request){
		if(userAuthService.validateUser(request)){
			entityRepository.delete(entityId);
		}
	}

}

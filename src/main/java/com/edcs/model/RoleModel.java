package com.edcs.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.edcs.security.model.User;

@Entity
@Table(name = "IES_ROL")
public class RoleModel implements Serializable{

	
	private static final long serialVersionUID = 3664456772457438343L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_ROL_ID")
	private int id;
	
	@Column(name="ROL_NAME", nullable=false,length=60)
	@NotNull
	private String name;
	
	@ManyToMany(mappedBy = "roleModelList")
	private Set<EntityModel>entityModelList;
	
	@ManyToMany(mappedBy = "roleMapUserModelList")
	private Set<User>userModelList;
	
	@ManyToMany(mappedBy = "roleMapPageModelList")
	private Set<PageModel>pageModelList;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;

	public RoleModel() {
	}

	public RoleModel(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<EntityModel> getEntityModelList() {
		return entityModelList;
	}

	public void setEntityModelList(Set<EntityModel> entityModelList) {
		this.entityModelList = entityModelList;
	}

	public Set<User> getUserModelList() {
		return userModelList;
	}

	public void setUserModelList(Set<User> userModelList) {
		this.userModelList = userModelList;
	}

	public Set<PageModel> getPageModelList() {
		return pageModelList;
	}

	public void setPageModelList(Set<PageModel> pageModelList) {
		this.pageModelList = pageModelList;
	}

	@Override
	public String toString() {
		return "RoleModel [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}
	
	
	
}

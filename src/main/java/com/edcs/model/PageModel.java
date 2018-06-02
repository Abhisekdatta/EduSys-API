package com.edcs.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "IES_PAG")
public class PageModel implements Serializable{


	private static final long serialVersionUID = 4135220215710593818L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_PAG_ID")
	private int id;
	
	@Column(name="PAG_NAME", nullable=false,length=80)
	@NotNull
	private String name;
	
	@Column(name="URL", nullable=false,length=120)
	@NotNull
	private String url;
	
	@Column(name="KEY_NO",nullable=false,length=20)
	@NotNull
	private String key;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FK_MOD_ID")
	@NotNull
	private ModuleModel module; 
	
	@ManyToMany(mappedBy = "pages")
    private Set<OperationModel> operations = new HashSet<OperationModel>();
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "IES_ROL_PAG_MAP",
            joinColumns = {@JoinColumn(name = "FK_PAG_ID", referencedColumnName = "PK_PAG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_ROL_ID", referencedColumnName = "PK_ROL_ID")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"FK_PAG_ID", "FK_ROL_ID"})})
	private Set<RoleModel>roleMapPageModelList;
	
	/*@ManyToMany(mappedBy = "pages")
    private Set<EntityRoleModel> entityRoles = new HashSet<EntityRoleModel>();*/
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;

	
	public PageModel() {
		super();
	}

	public PageModel(String name, String url, String key, ModuleModel module, boolean isActive) {
		this.name = name;
		this.url = url;
		this.key = key;
		this.module = module;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ModuleModel getModule() {
		return module;
	}

	public void setModule(ModuleModel module) {
		this.module = module;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<OperationModel> getOperations() {
		return operations;
	}

	public void setOperations(Set<OperationModel> operations) {
		this.operations = operations;
	}

	/*public Set<EntityRoleModel> getEntityRoles() {
		return entityRoles;
	}

	public void setEntityRoles(Set<EntityRoleModel> entityRoles) {
		this.entityRoles = entityRoles;
	}*/

	public Set<RoleModel> getRoleMapPageModelList() {
		return roleMapPageModelList;
	}

	public void setRoleMapPageModelList(Set<RoleModel> roleMapPageModelList) {
		this.roleMapPageModelList = roleMapPageModelList;
	}

	@Override
	public String toString() {
		return "PageModule [id=" + id + ", name=" + name + ", url=" + url + ", key=" + key + ", module=" + module
				+ ", isActive=" + isActive + "]";
	}
	
	
	
}

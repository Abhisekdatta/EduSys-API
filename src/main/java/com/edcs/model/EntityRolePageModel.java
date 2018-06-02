package com.edcs.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IES_ENT_ROL_PAG_MAP")
public class EntityRolePageModel implements Serializable{

	private static final long serialVersionUID = -2940953635139624596L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_ENT_ROL_PAG_ID")
	private int id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FK_ENT_ID")
	private EntityModel entityModel;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FK_ROL_ID")
	private RoleModel roleModel;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FK_PAG_ID")
	private PageModel page;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "IES_ENT_ROL_PAG_OPT_MAP", 
        joinColumns = {@JoinColumn(name = "FK_ENT_ROL_PAG_ID")}, 
        inverseJoinColumns = { @JoinColumn(name = "FK_OPT_ID") }
    )
	private Set<OperationModel>operations=new HashSet<OperationModel>();
	
	
	public EntityRolePageModel() {
		super();
	}
	

	public EntityRolePageModel(EntityModel entityModel, RoleModel roleModel, PageModel page,
			Set<OperationModel> operations) {
		super();
		this.entityModel = entityModel;
		this.roleModel = roleModel;
		this.page = page;
		this.operations = operations;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public PageModel getPage() {
		return page;
	}

	public void setPage(PageModel page) {
		this.page = page;
	}
	public Set<OperationModel> getOperations() {
		return operations;
	}
	public void setOperations(Set<OperationModel> operations) {
		this.operations = operations;
	}




	public EntityModel getEntityModel() {
		return entityModel;
	}




	public void setEntityModel(EntityModel entityModel) {
		this.entityModel = entityModel;
	}




	public RoleModel getRoleModel() {
		return roleModel;
	}




	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}
	

	@Override
	public String toString() {
		return "EntityRolePageModel [id=" + id + ", entityModel=" + entityModel.getName() + ", roleModel=" + roleModel.getName() + ", page="
				+ page.getName() + ", operations=" + operations + "]";
	}
}

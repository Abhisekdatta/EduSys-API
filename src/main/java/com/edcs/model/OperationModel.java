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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "IES_OPT")
public class OperationModel implements Serializable{

	private static final long serialVersionUID = 6063611140446756752L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_OPT_ID")
	private int id;
	
	@Column(name="OPT_NAME", nullable=false,length=40)
	@NotNull
	private String name;
	
	@Column(name="KEY_NO",nullable=false,length=20)
	@NotNull
	private String key;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "IES_PAG_OPT_MAP", 
        joinColumns = {@JoinColumn(name = "FK_OPT_ID")}, 
        inverseJoinColumns = { @JoinColumn(name = "FK_PAG_ID") }
    )
    Set<PageModel> pages = new HashSet<PageModel>();
	
	@ManyToMany(mappedBy = "operations")
	Set<EntityRolePageModel>entityRolePages=new HashSet<EntityRolePageModel>();

	public OperationModel() {
		super();
	}

	public OperationModel(String name, String key, boolean isActive) {
		this.name = name;
		this.key = key;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<PageModel> getPages() {
		return pages;
	}

	public void setPages(Set<PageModel> pages) {
		this.pages = pages;
	}

	public Set<EntityRolePageModel> getEntityRolePages() {
		return entityRolePages;
	}

	public void setEntityRolePages(Set<EntityRolePageModel> entityRolePages) {
		this.entityRolePages = entityRolePages;
	}

	@Override
	public String toString() {
		return "OperationModel [id=" + id + ", name=" + name + ", key=" + key + ", isActive=" + isActive + "]";
	}
	
	
}

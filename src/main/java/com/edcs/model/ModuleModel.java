package com.edcs.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "IES_MOD")
public class ModuleModel implements Serializable{

	private static final long serialVersionUID = -5774434987169158320L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_MOD_ID")
	private int id;
	
	@Column(name="MOD_NAME")
	@NotNull
	private String name;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FK_MOD_ID",nullable=true)
	private ModuleModel module;
	
	@Column(name="MOD_ICN")
	private String moduleIcon;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;
	
	
	
	public ModuleModel() {
		super();
	}
	
	public ModuleModel(String name, ModuleModel module, boolean isActive) {
		this.name = name;
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

	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}

	@Override
	public String toString() {
		return "ModuleModel [id=" + id + ", name=" + name + ", module=" + module + ", isActive=" + isActive + "]";
	}
	
	
}

package com.edcs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.edcs.security.model.User;

@Entity
@Table(name = "IES_ENT")
public class EntityModel implements Serializable{

	private static final long serialVersionUID = -2001362276097944851L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_ENT_ID")
	private int id;
	
	@Column(name="ENT_TYP",nullable=false,length=2)
	private String entityType;
	
	@Column(name="ENT_NAME", nullable=false,length=200)
	@NotNull
	private String name;
	
	@Column(name="ENT_CODE", nullable=false,length=40)
	@NotNull
	private String code;
	
	@Column(name="EMAIL",length=100)
	private String email;
	
	@Column(name="CNT_NO",length=30)
	private String contactNo;
	
	@Column(name="FAX_NO",length=30)
	private String faxNo;
	
	@Column(name="WEB_SITE",length=180)
	private String webSite;
	
	@Column(name="ADR_LIN1")
	private String addressLine1;
	
	@Column(name="ADR_LIN2")
	private String addressLine2;
	
	@Column(name="NO_USR", nullable=false)
	@NotNull
	private Integer numberOfUser;
	
	@Column(name="CNTR_STR_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date contractStartDate;
	
	@Column(name="CNTR_END_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date contractEndDate;
	
	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "IES_ROL_ENT_MAP", 
        joinColumns =  {@JoinColumn(name = "FK_ENT_ID",referencedColumnName = "PK_ENT_ID")},
        inverseJoinColumns={@JoinColumn(name = "FK_ROL_ID,",referencedColumnName = "PK_ROL_ID")},
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"FK_ENT_ID", "FK_ROL_ID"})})
	private Set<RoleModel>roleMapEntityModelList;*/
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="IES_ENT_ROL_MAP",
	           joinColumns={@JoinColumn(name="FK_ENT_ID",referencedColumnName="PK_ENT_ID")},
	           inverseJoinColumns={@JoinColumn(name="FK_ROL_ID",referencedColumnName="PK_ROL_ID")})
	private Set<RoleModel>roleModelList;
	
	@ManyToMany(mappedBy="entityModelList")
	private Set<User>userList;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;

	public EntityModel() {
		super();
	}
	
	

	public String getEntityType() {
		return entityType;
	}



	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}



	public EntityModel(String name, String entityType, String code, String email, String contactNo, String faxNo, String webSite,
			String addressLine1, String addressLine2, Integer numberOfUser, Date contractStartDate,
			Date contractEndDate, boolean isActive) {
		super();
		this.entityType=entityType;
		this.name = name;
		this.code = code;
		this.email = email;
		this.contactNo = contactNo;
		this.faxNo = faxNo;
		this.webSite = webSite;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.numberOfUser = numberOfUser;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Integer getNumberOfUser() {
		return numberOfUser;
	}

	public void setNumberOfUser(Integer numberOfUser) {
		this.numberOfUser = numberOfUser;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	/*public Set<RoleModel> getRoleMapEntityModelList() {
		return roleMapEntityModelList;
	}



	public void setRoleMapEntityModelList(Set<RoleModel> roleMapEntityModelList) {
		this.roleMapEntityModelList = roleMapEntityModelList;
	}*/



	public Set<RoleModel> getRoleModelList() {
		return roleModelList;
	}



	public void setRoleModelList(Set<RoleModel> roleModelList) {
		this.roleModelList = roleModelList;
	}



	public Set<User> getUserList() {
		return userList;
	}



	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}



	@Override
	public String toString() {
		return "EntityModel [id=" + id + ", entityType=" + entityType + ", name=" + name + ", code=" + code + ", email=" + email + ", contactNo="
				+ contactNo + ", faxNo=" + faxNo + ", webSite=" + webSite + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", numberOfUser=" + numberOfUser + ", contractStartDate="
				+ contractStartDate + ", contractEndDate=" + contractEndDate + ", isActive=" + isActive + "]";
	}
	
	
	
}

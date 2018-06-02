package com.edcs.security.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.edcs.model.EntityModel;
import com.edcs.model.RoleModel;

@Entity
@Table(name = "IES_USER")
public class User implements Serializable{

    
	private static final long serialVersionUID = 5281344280412972444L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PK_USR_ID")
    private int id;

    @Column(name="USR_NAM",length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name="PWD",length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name="FST_NAM",length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name="LST_NAM",length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name="EMAIL",length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;
    
    @Column(name="CNT_NO",length = 50)
    private String contactNo;
    
    @Column(name="ADR")
    private String address;
    
    @Column(name="IMG")
	@Lob
    private Blob image;

    @NotNull
    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "IES_USER_AUTH_MAP",
            joinColumns = {@JoinColumn(name = "FK_USR_ID", referencedColumnName = "PK_USR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_AUTH_ID", referencedColumnName = "PK_AUTH_ID")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"FK_USR_ID", "FK_AUTH_ID"})})
    private List<Authority> authorities;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "IES_ROL_USR_MAP",
            joinColumns = {@JoinColumn(name = "FK_USR_ID", referencedColumnName = "PK_USR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_ROL_ID", referencedColumnName = "PK_ROL_ID")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"FK_USR_ID", "FK_ROL_ID"})})
    private Set<RoleModel>roleMapUserModelList;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "IES_ENT_USR_MAP",
            joinColumns = {@JoinColumn(name = "FK_USR_ID", referencedColumnName = "PK_USR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_ENT_ID", referencedColumnName = "PK_ENT_ID")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"FK_USR_ID", "FK_ENT_ID"})})
    private Set<EntityModel>entityModelList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Set<RoleModel> getRoleMapUserModelList() {
		return roleMapUserModelList;
	}

	public void setRoleMapUserModelList(Set<RoleModel> roleMapUserModelList) {
		this.roleMapUserModelList = roleMapUserModelList;
	}

	public Set<EntityModel> getEntityModelList() {
		return entityModelList;
	}

	public void setEntityModelList(Set<EntityModel> entityModelList) {
		this.entityModelList = entityModelList;
	}
}
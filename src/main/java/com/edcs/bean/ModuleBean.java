package com.edcs.bean;

import java.util.List;

public class ModuleBean {

	private Integer id;
	private String name;
	private String moduleIcon;
	private ModuleBean moduleBean;
	private List<PageBean>pageModuleBeanList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ModuleBean getModuleBean() {
		return moduleBean;
	}
	public void setModuleBean(ModuleBean moduleBean) {
		this.moduleBean = moduleBean;
	}
	public List<PageBean> getPageModuleBeanList() {
		return pageModuleBeanList;
	}
	public void setPageModuleBeanList(List<PageBean> pageModuleBeanList) {
		this.pageModuleBeanList = pageModuleBeanList;
	}
	public String getModuleIcon() {
		return moduleIcon;
	}
	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	
	
	
}

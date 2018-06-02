package com.edcs.bean;

import java.util.List;

public class PageBean {

	private Integer id;
	private String name;
	private String url;
	private String key;
	private List<OperationsBean>operationBeanList;
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
	public List<OperationsBean> getOperationBeanList() {
		return operationBeanList;
	}
	public void setOperationBeanList(List<OperationsBean> operationBeanList) {
		this.operationBeanList = operationBeanList;
	}
	
	
}

package com.edcs.web.service;

import java.util.List;

import com.edcs.bean.ModuleBean;

public interface ModuleService {

	public List<ModuleBean>findModuleBasedOnUserName(String userName);
}

package com.edcs.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edcs.security.model.Authority;
import com.edcs.security.model.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
	public List<Authority>findAllByName(AuthorityName authorityName);
}

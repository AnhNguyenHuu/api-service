package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.React;

public interface ReactRepository extends JpaRepository<React, Long>,JpaSpecificationExecutor<React>{
	List<React> findByPublished(boolean published);
	List<React> findByTitleContaining(String title);
}

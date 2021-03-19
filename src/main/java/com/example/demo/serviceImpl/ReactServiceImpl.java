package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReactDto;
import com.example.demo.dto.ReactJson;
import com.example.demo.model.React;
import com.example.demo.repository.ReactRepository;
import com.example.demo.service.ReactService;
import com.example.demo.specification.ReferenceObjectSpecification;


@Service
public class ReactServiceImpl implements ReactService{

	
	@Autowired
	private ReferenceObjectSpecification<React> referenceObjectSpecification;
	
	@Autowired
	private ReactRepository reactRepository;
	
	@Override
	public Page<React> gets(React react) {
		Specification<React> spec = referenceObjectSpecification.searchReact(react.getTitle());
		Page<React> page = reactRepository.findAll(spec,PageRequest.of(react.getPage(), react.getSize(), Sort.by(Sort.Direction.DESC, "id")));
		List<React> ls = new ArrayList<>();
//		if(page != null) {
//			page.getContent().stream().forEach(p ->{
//				ReactDto reactDto2 = new ReactDto();
//				BeanUtils.copyProperties(p, reactDto2);
//				ls.add(reactDto2);
//			});
//		}
		
		return new PageImpl<>(page.getContent(),PageRequest.of(react.getPage(),react.getSize()),page.getTotalElements());
	}
	
}

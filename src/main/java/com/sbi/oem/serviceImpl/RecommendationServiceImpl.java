package com.sbi.oem.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sbi.oem.dto.PriorityResponseDto;
import com.sbi.oem.dto.RecommendationPageDto;
import com.sbi.oem.dto.Response;
import com.sbi.oem.enums.PriorityEnum;
import com.sbi.oem.model.Component;
import com.sbi.oem.model.Department;
import com.sbi.oem.model.RecommendationType;
import com.sbi.oem.repository.ComponentRepository;
import com.sbi.oem.repository.DepartmentRepository;
import com.sbi.oem.repository.RecommendationTypeRepository;
import com.sbi.oem.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	private RecommendationTypeRepository recommendationTypeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Override
	public Response<?> getRecommendationPageData(Long companyId) {
		try {
			RecommendationPageDto recommendationPageDto = new RecommendationPageDto();
			List<RecommendationType> recommendationList = recommendationTypeRepository.findAllByCompanyId(companyId);
			recommendationPageDto.setRecommendationTypeList(recommendationList);
			List<Department> departmentList = departmentRepository.findAllByCompanyId(companyId);
			recommendationPageDto.setDepartmentList(departmentList);
			List<Component> componentList = componentRepository.findAllByCompanyId(companyId);
			recommendationPageDto.setComponentList(componentList);
			List<PriorityEnum> priorityEnumList = Arrays.asList(PriorityEnum.values());
			List<PriorityResponseDto> priorityResponse = new ArrayList<>();
			for (PriorityEnum enums : priorityEnumList) {
				PriorityResponseDto dto = new PriorityResponseDto();
				dto.setId(enums.getId());
				dto.setName(enums.getName());
				priorityResponse.add(dto);
			}
			recommendationPageDto.setPriorityList(priorityResponse);
			return new Response<>(HttpStatus.OK.value(), "Recommendation page data.", recommendationPageDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong.", null);
		}

	}

}
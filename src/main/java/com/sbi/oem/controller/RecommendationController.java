package com.sbi.oem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.oem.dto.RecommendationAddRequestDto;
import com.sbi.oem.dto.RecommendationDetailsRequestDto;
import com.sbi.oem.dto.RecommendationRejectionRequestDto;
import com.sbi.oem.dto.Response;
import com.sbi.oem.model.Recommendation;
import com.sbi.oem.service.RecommendationService;
import com.sbi.oem.service.ValidationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	private ValidationService validationService;

	@GetMapping("/page/data")
	public ResponseEntity<?> getRecommendationPageData(@RequestParam("companyId") Long companyId) {
		Response<?> response = recommendationService.getRecommendationPageData(companyId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createRecommendation(
			@ModelAttribute RecommendationAddRequestDto recommendationAddRequestDto) {
		Response<?> validationResponse = validationService
				.checkForRecommendationAddPayload(recommendationAddRequestDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = recommendationService.addRecommendation(recommendationAddRequestDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		} else {
			return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
		}
	}

	@GetMapping("/view")
	public ResponseEntity<?> viewRecommendationByRefId(@RequestParam("refId") String refId) {
		Response<?> response = recommendationService.viewRecommendation(refId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}

	@GetMapping("/get/all")
	public ResponseEntity<?> getAllRecommendations() {
		Response<?> response = recommendationService.getAllRecommendations();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/deplyoment/details/provide")
	public ResponseEntity<?> provideRecommendationDeplyomentDetails(
			@RequestBody RecommendationDetailsRequestDto recommendationDetailsRequestDto) {
		Response<?> validationResponse = validationService
				.checkForDeploymentDetailsAddPayload(recommendationDetailsRequestDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = recommendationService
					.setRecommendationDeploymentDetails(recommendationDetailsRequestDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		} else {
			return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
		}
	}

	@PostMapping("/rejected/by/appowner")
	public ResponseEntity<?> recommendationRejectByAppOwner(
			@RequestBody RecommendationRejectionRequestDto recommendation) {
		Response<?> validationResponse = validationService
				.checkForAppOwnerRecommendationRejectedPayload(recommendation);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = recommendationService.rejectRecommendationByAppOwner(recommendation);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		} else {
			return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
		}
	}
}

package com.sbi.oem.service;

import com.sbi.oem.dto.RecommendationAddRequestDto;
import com.sbi.oem.dto.Response;

public interface ValidationService {

	Response<?> checkForRecommendationAddPayload(RecommendationAddRequestDto recommendationAddRequestDto);

}
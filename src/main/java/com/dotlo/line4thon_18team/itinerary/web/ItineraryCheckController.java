package com.dotlo.line4thon_18team.itinerary.web;

import com.dotlo.line4thon_18team.auth.LoginUser;
import com.dotlo.line4thon_18team.itinerary.ItineraryValidationService;
import com.dotlo.line4thon_18team.itinerary.validation.result.ValidationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 일정 검증 API (GEN-CHECK-001, BE3). 로그인이 필요하고 남의 여행이면 404다.
 */
@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@RequiredArgsConstructor
public class ItineraryCheckController {

    private final ItineraryValidationService validationService;

    /** 일정의 시각을 계산하고 영업시간·이동시간 문제를 찾는다. 저장하지 않는다. */
    @PostMapping("/check")
    public ValidationResult check(@PathVariable("tripId") long tripId,
                                  @LoginUser Long userId,
                                  @Valid @RequestBody ItineraryCheckRequest request) {
        return validationService.validate(tripId, userId, request.toDraft(), request.origin());
    }
}

package com.dotlo.line4thon_18team.preference.web;

import com.dotlo.line4thon_18team.auth.LoginUser;
import com.dotlo.line4thon_18team.preference.PreferenceInput;
import com.dotlo.line4thon_18team.preference.UserPreferenceService;
import com.dotlo.line4thon_18team.preference.UserPreferenceView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 기본 취향 API (BE3). 가입할 때 한 번 고르고, 여행별 취향을 입력하지 않았을 때 쓴다.
 * 로그인이 필요하고, 자기 것만 보고 고친다 (경로에 사용자 id를 받지 않는다).
 *
 * <p>여행별 취향은 {@code /api/trips/{tripId}/preferences} 쪽이다.
 */
@RestController
@RequestMapping("/api/users/me/preferences")
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    /** 저장 (전체 덮어쓰기). 요청 바디는 여행별 취향 저장과 같은 모양이다. */
    @PutMapping
    public UserPreferenceView save(@LoginUser Long userId, @RequestBody PreferenceInput input) {
        return userPreferenceService.save(userId, input);
    }

    /** 조회. 고른 적이 없으면 기본값(saved=false). */
    @GetMapping
    public UserPreferenceView get(@LoginUser Long userId) {
        return userPreferenceService.get(userId);
    }
}

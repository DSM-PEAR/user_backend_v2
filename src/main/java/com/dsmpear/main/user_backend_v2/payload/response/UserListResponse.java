package com.dsmpear.main.user_backend_v2.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserListResponse {

    private List<UserResponse> userResponses;

    private int totalElements;

}

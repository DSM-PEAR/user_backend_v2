package com.dsmpear.main.user_backend_v2.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfilePageResponse {

    private String userName;

    private String userEmail;

    private String gitHub;

    private String selfIntro;

}

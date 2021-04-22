package com.dsmpear.main.user_backend_v2.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetSelfIntroRequest {

    private String intro;

    private String github;

}

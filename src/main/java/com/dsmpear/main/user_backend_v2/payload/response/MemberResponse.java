package com.dsmpear.main.user_backend_v2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private Integer memberId;

    private String memberEmail;

    private String memberName;

}

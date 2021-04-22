package com.dsmpear.main.user_backend_v2.payload.response;

import com.dsmpear.main.user_backend_v2.entity.report.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageReportResponse {

    private Integer reportId;

    private String title;

    private Type type; // 팀, 개인, 동아리

    private LocalDateTime createdAt;

    private Boolean isSubmitted; // 제출됨

    private Boolean isAccepted; // 승인 or 미승인

    private Boolean isRejected; // 거부됐는지 여부

}

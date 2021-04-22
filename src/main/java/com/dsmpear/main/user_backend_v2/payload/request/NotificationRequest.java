package com.dsmpear.main.user_backend_v2.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    @NotNull(message = "잘못된 내용이 없는지 확인해주세요.")
    private String boardId;

    @NotNull(message = "잘못된 내용이 없는지 확인해주세요.")
    private String email;

    @NotNull(message = "잘못된 내용이 없는지 확인해주세요.")
    private String body;

    @NotNull(message = "잘못된 내용이 없는지 확인해주세요.")
    private Boolean isAccepted;

}

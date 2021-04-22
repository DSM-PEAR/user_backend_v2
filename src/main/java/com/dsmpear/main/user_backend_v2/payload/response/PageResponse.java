package com.dsmpear.main.user_backend_v2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {

    private Long totalElements;

    private int totalPages;

}

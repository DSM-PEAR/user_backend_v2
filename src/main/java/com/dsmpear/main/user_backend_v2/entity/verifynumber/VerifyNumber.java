package com.dsmpear.main.user_backend_v2.entity.verifynumber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "verify_number", timeToLive = 60 * 3)
public class VerifyNumber {

    @Id
    private Integer id;

    @Indexed
    private String email;

    private String verifyNumber;

    @TimeToLive
    private Long ttl;

    public boolean verifyNumber(String number) {
        return this.verifyNumber.equals(number);
    }

}

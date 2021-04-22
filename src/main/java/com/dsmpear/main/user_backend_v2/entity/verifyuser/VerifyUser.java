package com.dsmpear.main.user_backend_v2.entity.verifyuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(timeToLive = 60 * 3)
public class VerifyUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UUID;

    @Indexed
    private String email;

    @TimeToLive
    private Long ttl;

    public VerifyUser(String email) {
        this.email = email;
    }

}

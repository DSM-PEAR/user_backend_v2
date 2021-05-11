package com.dsmpear.main.user_backend_v2.config;

import com.dsmpear.main.user_backend_v2.security.JwtAuthenticationEntryPoint;
import com.dsmpear.main.user_backend_v2.security.JwtConfigurer;
import com.dsmpear.main.user_backend_v2.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .sessionManagement().disable()
                .formLogin().disable()
                .authorizeRequests()
                    .antMatchers("/auth").permitAll()
                    .antMatchers("/email").permitAll()
                    .antMatchers("/email/**").permitAll()
                    .antMatchers("/account").permitAll()
                    .antMatchers(HttpMethod.GET, "/report").permitAll()
                    .antMatchers(HttpMethod.POST, "/question").permitAll()
                    .antMatchers(HttpMethod.GET, "/notice").permitAll()
                    .antMatchers(HttpMethod.GET, "/notice/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/user/profile/report").permitAll()
                    .antMatchers(HttpMethod.GET, "/profile/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/profile/report").permitAll()
                    .antMatchers(HttpMethod.GET, "/profile/report/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/member/{reportId}").permitAll()
                    .antMatchers(HttpMethod.GET, "/search/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/report/filter").permitAll()
                    .antMatchers(HttpMethod.GET, "/report/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

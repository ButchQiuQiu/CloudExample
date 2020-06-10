package com.butch.securityzuul6101.config;

import com.butch.securityzuul6101.security.JwtAuthenticationEntryPoint;
import com.butch.securityzuul6101.security.JwtAuthorizationTokenFilter;
import com.butch.securityzuul6101.security.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity// 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //token为空的处理
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // 用户账号密码验证逻辑
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    // 自定义的Token拦截器
    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;


    //先来这里认证一下
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    //拦截在这配
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //添加匿名用户的请求处理
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                //放行请求
                .antMatchers("/test").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/haha").permitAll()
                .antMatchers("/sysUser/test").permitAll()
                //放行匿名用户的OPTIONS访问。
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest().authenticated()       // 剩下所有的验证都需要验证
                .and()
                .csrf().disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session


        //添加拦截器，在xxx之前拦截，xxx为第二参数，第一参数为添加的拦截器。
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    //注入密码加密器
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }


    //注入默认的AuthenticationManager.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
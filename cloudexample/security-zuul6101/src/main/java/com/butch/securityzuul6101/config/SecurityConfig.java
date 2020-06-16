package com.butch.securityzuul6101.config;

import com.butch.securityzuul6101.security.handle.LoginFailedHandle;
import com.butch.securityzuul6101.security.handle.LoginSuccessHandle;
import com.butch.securityzuul6101.security.handle.MyLogoutSuccessHandler;
import com.butch.securityzuul6101.security.service.JwtUserDetailsService;
import com.butch.securityzuul6101.util.SecurityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityUtil securityUtil;

    //----------------------------------------注销
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;

    //-------------------------------------------登录
    /**
     * 登录成功handle 通知前端，并且给前端的请求头挂上jwt
     */
    @Autowired
    LoginSuccessHandle loginSuccessHandle;

    /**
     * 登录失败handle 通知前端
     */
    @Autowired
    LoginFailedHandle loginFailedHandle;

    /**
     * 密码编码解码器
     */
    @Autowired
    @Qualifier("NoPassWordEncoder")
    PasswordEncoder passwordEncoder;

    /**
     * 用户账号密码验证逻辑，验证成功就触发登陆成功handle
     */
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    /**
     * 自定义的Token拦截器
     */
    // @Autowired
    // JwtAuthorizationTokenFilter authenticationTokenFilter;

    /**
     * 注入时调用，设置UserDetail、passwordEncoder后会启用默认的DaoAuthenticationProvider
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * http配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 添加匿名用户的请求处理
                // .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                //放行匿名用户的OPTIONS访问,主要提供跨域支持
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                // 所有请求都得通过验证
                .anyRequest().authenticated()
                // 配置登录请求的url和
                .and().formLogin().loginPage("/page-login.html").loginProcessingUrl("/user/login")
                // 登录成功或者失败的处理器
                .successHandler(loginSuccessHandle).failureHandler(loginFailedHandle).permitAll()
                // 设置login_p页面向login提交的表单的各个元素名.
                .usernameParameter("username").passwordParameter("password")
                // 设置注销请求的url和对应的自定义处理器.
                // 大坑--jwt没有seesion----------------------------------------------------
                .and().logout().logoutUrl("/user/logout").logoutSuccessHandler(myLogoutSuccessHandler).permitAll()
                // 禁用 Spring Security 自带的跨域处理
                .and().csrf().disable()
                // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 添加JWT拦截器，主要捕获用户的jwt参数。在xxx之前拦截，xxx为第二参数，第一参数为添加的拦截器。
        http.addFilterBefore(securityUtil.getJwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * web配置 过滤掉登陆界面的资源以免验证拦截等拦截器循环重定向
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/page-login.html", "/assets/**", "/images/**", "/js/**", "/vendors/**",
                 "favicon.ico", "/page-login.html");
    }
}
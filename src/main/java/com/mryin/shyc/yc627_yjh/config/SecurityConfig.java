package com.mryin.shyc.yc627_yjh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @Author: CXD
 * Date: 2020/5/3 18:06
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   /* @Resource
    private UserService userService;*/
    //授权，链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人都可访问，功能页只能有对应权限才能访问
        //配置请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限则跳转到登录页面，需开启登录页面，默认为/login请求，可自定义登录页
        http.formLogin().loginPage("/toLogin");
        //关闭csrf功能，注销操作失败可能是开启了csrf
        http.csrf().disable();
        //开启注销操作，并定制注销跳转页面
        http.logout().logoutSuccessUrl("/index");
        //自定义开启记住我功能  cookie，默认保存两周
        http.rememberMe().rememberMeParameter("rememberMe");
    }

    //认证，springboot2.1.x可直接使用
    //密码编码异常--PasswordEncoder，即提示需要使用密码加密
    //在SpringSecurity5.0+版本中，新增 md5、base64等众多加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在缓存中模拟添加用户+角色，正常应该从数据库中读取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("swpuchenxinde1").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3").and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3").and()
                .withUser("swpuchenxinde2").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");

        //不写此处在使用rememberMe功能时会出现异常：UserDetailsService is required.

    }

    /**
     * 不写此方法passwordEncoder会抛java.lang.IllegalArgumentException:
     *      There is no PasswordEncoder mapped for the id "null"
     * 因为SpringSecurity新版本需使用密码加密
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

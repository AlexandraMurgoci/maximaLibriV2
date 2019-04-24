package com.maximaLibri.maximaLibriV2.config;

import com.maximaLibri.maximaLibriV2.model.RoleName;
import com.maximaLibri.maximaLibriV2.security.AuthentificationFilter;
import com.maximaLibri.maximaLibriV2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public AuthentificationFilter AuthentificationFilter() {return new AuthentificationFilter();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/registration**",
                        "/confirm",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**",
                        "/",
                        "/index",
                        "/book/show/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/book/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/book/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        //http.addFilterAfter(AuthentificationFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }
}

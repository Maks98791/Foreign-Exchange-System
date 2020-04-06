package com.example.ForeignExchangeSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.unit.DataSize;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bcp;
    @Autowired
    private DataSource ds;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(ds).passwordEncoder(bcp);
    }

    protected void configure(HttpSecurity httpSec) throws Exception {
        httpSec
                .authorizeRequests()
                .antMatchers("/").permitAll()  //linki dostepne dla wszystkich \/
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/adduser").permitAll()
                .antMatchers("/activatelink/**").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") //link autoryzowany tylko dla tej roli
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true") //error jesli zlee logowanie
                .defaultSuccessUrl("/").usernameParameter("email") //przekierowanie na glowna strone jesli sie zalogujemy
                .passwordParameter("password")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/") //wylogowanie uzytkownika
                .and().exceptionHandling().accessDeniedPage("/denied"); //strona wywywoływana gdy chcemy sie dostać gdzies bez potrzebnej roli
    }

    public void configure(WebSecurity webSec) throws Exception {
        //web security ignoruje te foldery można sobie tutaj coś dodac jeśli sie nad czymś pracuje
        webSec.ignoring()
                .antMatchers("/resources/**", "/statics/**", "/css/**", "/js/**", "/images/**", "/incl/**");
    }


}

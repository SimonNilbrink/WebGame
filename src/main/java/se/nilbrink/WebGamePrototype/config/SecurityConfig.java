package se.nilbrink.WebGamePrototype.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth)throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("simon")
                .password("{noop}password")
                .roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}

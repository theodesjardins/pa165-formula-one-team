package cz.muni.fi.pa165.mvc.config.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Configuration
@EnableWebSecurity
@Order(101)
@ComponentScan(basePackageClasses = {AuthProvider.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/**/create").authenticated()
                    .antMatchers("/**/edit/*").authenticated()
                    .antMatchers("/**/submit").authenticated()
                    .antMatchers("/**/delete/*").authenticated()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }
}

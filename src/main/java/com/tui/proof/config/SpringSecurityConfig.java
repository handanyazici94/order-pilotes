package com.tui.proof.config;

import com.tui.proof.model.enm.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()).and()
                .authenticationProvider(authenticationProvider())
                .jdbcAuthentication()
                .dataSource(dataSource);
    }

    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}admin").authorities("USER")
            .and()
            .withUser("admin").password("{noop}admin").authorities("ADMIN");
    }
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .authorizeRequests()
                .antMatchers("/api/v1/client/**").permitAll()
                .antMatchers("/api/v1/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/order/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/order/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/order/**").hasRole(Role.ADMIN.toString())
                //.anyRequest().authenticated()
            .and().csrf().ignoringAntMatchers("/h2-console/**")
            .and().headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()
            .formLogin().disable();


        /*

        // Enable CORS and disable CSRF

        // Set permissions on endpoints
          http.authorizeRequests()
        // Our public endpoints
       // .antMatchers("/api/public/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/v1/client/**").permitAll()
          .antMatchers(HttpMethod.PUT, "/api/v1/order/client/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/v1/order/client/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/v1/order/list/**").permitAll();
          //.and()
          //.httpBasic();
         // .antMatchers(HttpMethod.GET, "/api/v1/order/search").hasAuthority("ADMIN")
        // Our private endpoints
         // .anyRequest().authenticated();
        */
    }
}

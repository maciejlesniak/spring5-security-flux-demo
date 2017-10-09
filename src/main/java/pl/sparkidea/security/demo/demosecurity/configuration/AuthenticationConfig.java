package pl.sparkidea.security.demo.demosecurity.configuration;

import org.springframework.boot.actuate.autoconfigure.security.EndpointRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Maciej Lesniak
 */
@Configuration
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .requestMatchers(EndpointRequest.to("status", "info")).authenticated()
                    .requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                    .requestMatchers(StaticResourceRequest.toCommonLocations()).permitAll()
                    .antMatchers("/v2/api-docs").hasRole("SWAGGER")
                    .antMatchers("/").anonymous()
                    .antMatchers("/**").hasAnyRole("USER")
                    .and()
                .httpBasic()
                .and()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("swagger").password("swagger").roles("SWAGGER");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

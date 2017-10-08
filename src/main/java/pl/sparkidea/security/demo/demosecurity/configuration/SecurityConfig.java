package pl.sparkidea.security.demo.demosecurity.configuration;

import org.springframework.boot.actuate.autoconfigure.security.EndpointRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Maciej Lesniak
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement().disable()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to("status", "info")).authenticated()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                .requestMatchers(StaticResourceRequest.toCommonLocations()).permitAll()
                .antMatchers("/**").hasAnyRole("USER")
                .and()
                .httpBasic()
                .and()
                .formLogin().disable();


    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("user123").roles("USER").build());
        return manager;
    }

}

package thelocalspot.application.security;

import thelocalspot.application.views.list.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("admin", "coordinator", "user", "host")
                .build();
        UserDetails coordinator = User.builder()
                .username("coordinator")
                .password("{noop}coordinator")
                .roles("coordinator")
                .build();
        UserDetails user = User.builder()
                .username("user")
                // password = password with this hash, don't tell anybody :-)
                .password("{noop}user")
                .roles("user")
                .build();
        UserDetails host = User.builder()
                .username("host")
                .password("{noop}host")
                .roles("host")
                .build();
        return new InMemoryUserDetailsManager(admin, coordinator, user, host);
    }
}
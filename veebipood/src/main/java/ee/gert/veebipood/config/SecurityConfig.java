package ee.gert.veebipood.config;


import ee.gert.veebipood.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors().and().headers().xssProtection().disable().and()
                .csrf().disable()

//                .cors(cors -> cors.disable())  // Disable CORS if you don’t need it
//                .headers(headers -> headers
//                        .xssProtection(xss -> xss.disable())  // Disable XSS protection if needed
//                )

                .authorizeHttpRequests( requests -> requests
                        .requestMatchers(new AntPathRequestMatcher("/products")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll() //FIXME: signup not allowed without
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
//                .addFilterAfter(jwtFilter, BasicAuthenticationFilter.class)
                .build();
    }
}

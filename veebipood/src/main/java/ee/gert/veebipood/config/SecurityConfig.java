package ee.gert.veebipood.config;


import ee.gert.veebipood.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
//                        .requestMatchers(new AntPathRequestMatcher("/public-products")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/product")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/find-by-name")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll() //FIXME: signup not allowed without
//                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/product/**")).hasAuthority("admin")
//                        .requestMatchers(new AntPathRequestMatcher("/categories")).permitAll() //GET
//                        .requestMatchers(new AntPathRequestMatcher("/categories")).hasAuthority("admin") //POST
//                        .requestMatchers(new AntPathRequestMatcher("/products/**")).hasAuthority("admin")
//                        .requestMatchers(new AntPathRequestMatcher("/products/**")).hasAuthority("admin")
//                        .requestMatchers(new AntPathRequestMatcher("/all-products")).hasAuthority("admin")
                        .requestMatchers(("/public-products")).permitAll()
                        .requestMatchers(("/product")).permitAll()
                        .requestMatchers(("/find-by-name")).permitAll()
                        .requestMatchers(("/signup")).permitAll() //FIXME: signup not allowed without? without what?
                        .requestMatchers(("/login")).permitAll()
                        .requestMatchers(HttpMethod.GET,("/categories")).permitAll() //GET
                        .requestMatchers(HttpMethod.POST, ("/categories")).hasAuthority("admin") //POST
                        .requestMatchers(("/all-products")).hasAuthority("admin")
                        .requestMatchers(("/products/**")).hasAuthority("admin")
                        .requestMatchers(("admin")).permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
//                .addFilterAfter(jwtFilter, BasicAuthenticationFilter.class)
                .build();
    }
}

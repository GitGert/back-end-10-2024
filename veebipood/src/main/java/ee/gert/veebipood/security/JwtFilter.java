package ee.gert.veebipood.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@Component
public class JwtFilter extends BasicAuthenticationFilter {
    // @ LAZY - is to prevent circular references
    public JwtFilter(@Lazy AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request);
        System.out.println(request.getMethod());
        System.out.println(request.getHeaders(HttpHeaders.AUTHORIZATION));
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println(token);
        // Get token
        if (token != null && token.startsWith("Bearer ")){
            token = token.replace("Bearer ", "");
            System.out.println(token);
//
//             Jwts.parser().unsecuredDecompression();

            String securityKey = "RejIo94s8uVncM6wTDELhWLfkBcP8jQ5zE+JrdzaXs3Xoud9BgcQm0/iUzJsmytcRdfoilTS0ZKeF/6lFHfNJg==";
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));


            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();


            //NOTE: DEPRECATED VERION:
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)))
//                    .build()
//                    .parseClaimsJws(requestToken)
//                    .getBody();


            System.out.println(claims.get("firstName"));
            System.out.println(claims.get("lastName"));
            System.out.println(claims.get("email"));

            List<GrantedAuthority> authorities = new ArrayList<>();
            //TODO: read the data and create a authentication instance with it

            String email = claims.get("email").toString();
            String credentials = claims.get("firstName") + " " + claims.get("lastName");
                                                                            // tokeni seest email                    ja credentials
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, credentials, authorities ); //FL -FirstLast
            // the authentication will be passed to the Securitycontextholder
            SecurityContextHolder.getContext().setAuthentication(authentication); // see ride teeb autentituks
        }

        super.doFilterInternal(request, response, chain);
    }
}

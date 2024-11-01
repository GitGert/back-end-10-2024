package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.model.Token;
//import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    public Token getToken(Person person) {
        Date experationDate = new Date(new Date().getTime() + 2* 3600*1000);
        Map<String, String> claims = new HashMap<>();
        claims.put("email",person.getEmail());
        claims.put("firstName",person.getFirstName());
        claims.put("lastName",person.getLastName());

//        String securityKey =  Jwts.SIG.HS256.key();
        String securityKey = "RejIo94s8uVncM6wTDELhWLfkBcP8jQ5zE+JrdzaXs3Xoud9BgcQm0/iUzJsmytcRdfoilTS0ZKeF/6lFHfNJg==";
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));

        String jwtToken = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(experationDate)
                .claims(claims)
                .signWith(key)
                .compact();

//        String jwtToken = "jwtToken";
//                .serializeToJsonWith(serializer)  // Set serializer here
//                .setSubject(person.getUsername())
//                .compact();


        System.out.println("jwtToken: ");
        System.out.println(jwtToken);
        Token token = new Token();
        token.setToken(jwtToken);
        token.setExpiration(experationDate);


        return token;
    }
}

package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.exception.ValidationException;
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

import static ee.gert.veebipood.utils.ValidationUtil.validateEmail;

@Service
public class AuthService {

    public Token getToken(Person person) {
        Date experationDate = new Date(new Date().getTime() + 2* 3600*1000); // 2 * an hour in milliseconds
        Map<String, String> claims = new HashMap<>();
        claims.put("email",person.getEmail());
        claims.put("firstName",person.getFirstName());
        claims.put("lastName",person.getLastName());
        claims.put("admin",String.valueOf(person.isAdmin()));
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

    public void validate(Person person) throws ValidationException {
        if (person.getEmail() == null || person.getEmail().isEmpty()){
            throw new ValidationException("Email cannot be empty");
        }
        if (!validateEmail(person.getEmail())){
            throw new ValidationException("Email is not correct");
        }
        if (person.getPassword() == null || person.getPassword().isEmpty()){
            throw new ValidationException("Password cannot be empty");
        }
        if (person.getFirstName() == null || person.getFirstName().isEmpty()){
            throw new ValidationException("First name cannot be empty");
        }
        if (person.getLastName() == null || person.getLastName().isEmpty()){
            throw new ValidationException("Last name cannot be empty");
        }
    }




}

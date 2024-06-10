package spring.security.example.spring_security.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();//Generate secretkey 


    public String generateToken(UserDetails userDetails){
        Claims claims = Jwts.claims()
        .add("authorities",userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()))
        .build();
        return createToken(claims, userDetails.getUsername());//we going to add claims here
    }


    private String createToken(Claims claims, String username) {
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+(1000*60*60)))
            .signWith(SECRET_KEY)
            .claims(claims)
            .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);//we going to use methot reference
    }


    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> clamsResolver) {
        final Claims claims = extractAllClaims(token);
        return clamsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseEncryptedClaims(token).getPayload();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    

}

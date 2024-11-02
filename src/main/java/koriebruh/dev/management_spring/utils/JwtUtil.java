package koriebruh.dev.management_spring.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "secret-jgn-bilangsiapasiapa";
    private static final String ISSUER = "KING-JAMAL";

    public String generateToken(String username, String idUser){
        return Jwts.builder()
                .setSubject(username)
                .claim("id_user", idUser)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)) // <-- kadaluarsa nya 10 hour
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Helper untuk mengambil klaim token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractIssuer(String token) {
        return extractClaim(token, Claims::getIssuer);
    }

    public String extractIdUser(String token) {
        return extractAllClaims(token).get("id_user", String.class);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}

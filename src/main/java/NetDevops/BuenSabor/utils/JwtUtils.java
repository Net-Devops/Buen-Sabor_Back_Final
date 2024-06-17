package NetDevops.BuenSabor.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.private.key}")
    private String privateKey;
    @Value("${security.jwt.public.generator}")
    private String userGenerator;

    public String createToken(Authentication authentication) {

        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        //queda en el context holder
        String username = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (30*60000)))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return jwtToken;

    }

    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            DecodedJWT decodeJWT = verifier.verify(token);
            return decodeJWT;

        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("El token no es valido");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
       return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClain (DecodedJWT decodedJWT, String claim){
        return decodedJWT.getClaim(claim);
    }

    public Map<String,Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}

package $package$.$name;format="normalize"$.utils;

import java.security.Key;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import $package$.$name;format="normalize"$.core.model.ErrorCode;
import $package$.$name;format="normalize"$.config.JwtConfig;
import $package$.$name;format="normalize"$.dto.AuthorizedUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import io.vavr.control.Either;
import io.vavr.control.Try;

/**
 * JwtUtil
 */
@Component
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    private Key key;

    @PostConstruct
    public void initVars() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecret()));
    }

    public String authorize(Long userId, Integer ut) {
        return Jwts.builder()
            .claim("id", userId)
            .claim("ut", ut)
            .signWith(key)
            .compact();
    }

    public Either<ErrorCode, AuthorizedUser> verify(String token) {
        return Try.of(() -> Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token))
            .filter(jws -> {
                    var claims = jws.getBody();
                    return claims != null && claims.get("id") != null && claims.get("ut") != null;
                })
            .map(jws -> {
                    var claims = jws.getBody();
                    var id = claims.get("id", Long.class);
                    var ut = claims.get("ut", Integer.class);
                    return new AuthorizedUser(id, ut);
                })
            .toEither(ErrorCode.InvalidToken);
     }
}

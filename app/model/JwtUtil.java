package model;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public abstract class JwtUtil {

    // Generate a symmetric key for signing and verifying JWT tokens
    public static Key generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    // Create a JWT token for a user
    public static String createToken(String username, Key key) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 86400000))
                .signWith(key)
                .compact();
    }

    // Verify and decode a JWT token
    public static Jws<Claims> parseToken(String token, Key key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}

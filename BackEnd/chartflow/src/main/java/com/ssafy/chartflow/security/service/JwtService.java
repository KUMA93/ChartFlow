package com.ssafy.chartflow.security.service;

import com.ssafy.chartflow.security.entity.RefreshToken;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import com.ssafy.chartflow.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@PropertySource("classpath:jwt.yml")
@Slf4j
public class JwtService {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;
    private final String accessTokenSecretKey;
    private final String refreshTokenSecretKey;
    private final long accessTokenExpirationHours;
    private final long refreshTokenExpirationHours;
    private final String issuer;
    private final long hour = 60 * 60 * 1000;

    public JwtService(
            @Value("${access-token-secret-key}") String accessTokenSecretKey,
            @Value("${refresh-token-secret-key}") String refreshTokenSecretKey,
            @Value("${access-token-expiration-hours}") long accessTokenExpirationHours,
            @Value("${refresh-token-expiration-hours}") long refreshTokenExpirationHours,
            @Value("${issuer}") String issuer
    ){
        this.accessTokenSecretKey = accessTokenSecretKey;
        this.refreshTokenSecretKey = refreshTokenSecretKey;
        this.accessTokenExpirationHours = accessTokenExpirationHours;
        this.refreshTokenExpirationHours = refreshTokenExpirationHours;
        this.issuer = issuer;
    }
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return Long.valueOf(extractClaim(token, claims -> claims.get("userId").toString()));
    }

    public String extractUserName(String token) {
        return extractClaim(token, claims -> claims.get("username", String.class));
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails, accessTokenSecretKey, accessTokenExpirationHours);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        String refreshToken = generateToken(new HashMap<>(), userDetails, refreshTokenSecretKey, refreshTokenExpirationHours);
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setUser((User) userDetails);
        refreshTokenService.save(refreshTokenEntity);
        return refreshToken;
    }

    public String refreshToken(String expiredToken, String refreshToken) {
        if (isTokenValid(refreshToken, loadUserByRefreshToken(refreshToken))) {  // Refresh Token 검증
            UserDetails userDetails = loadUserByRefreshToken(refreshToken);  // DB에서 UserDetails 가져오기
            return generateToken(userDetails);  // 새 Access Token 발급
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    private UserDetails loadUserByRefreshToken(String refreshToken) {
        RefreshToken storedRefreshToken = refreshTokenService.findByToken(refreshToken);
        if (storedRefreshToken != null) {
            return userRepository.findUserByRefreshToken_token(refreshToken);
        } else {
            throw new RuntimeException("Refresh token does not exist");
        }
    }

    private String generateToken
            (Map<String, Object> extraClaims, UserDetails userDetails,
             String secretKey, long expirationHours) {
        User user = (User) userDetails;
        extraClaims.put("userId", user.getId());
        extraClaims.put("username", user.getName());

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationHours * hour))
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        log.info("secretKey : " + accessTokenSecretKey);
        return Jwts
                .parserBuilder()
                .setSigningKey(accessTokenSecretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(String secretKey) {
        log.info(secretKey);
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

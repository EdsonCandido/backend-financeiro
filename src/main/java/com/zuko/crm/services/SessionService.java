package com.zuko.crm.services;


import com.zuko.crm.dto.request.LoginRequestDTO;
import com.zuko.crm.dto.response.LoginResponseDTO;
import com.zuko.crm.repositorys.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SessionService {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;

    public SessionService(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder encode) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.encode = encode;
    }

    public LoginResponseDTO session(LoginRequestDTO req) {
        var userExist = this.userRepository.findByLogin(req.login());

        if (userExist.isEmpty() || userExist.get().isPasswordMatch(req.password(), encode)) {
            throw new RuntimeException("Usuário ou senha inválido");
        }

        if (!userExist.get().getActive()) throw new RuntimeException("Usuário desabilitado");

        var now = Instant.now();
        var expireIn = 3000L;

        var scopes = userExist.get().getAdmin();

        var claims = JwtClaimsSet.builder()
                .issuer("api")
                .subject(userExist.get().getUserId().toString())
                .claim("scope", scopes)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expireIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expireIn, userExist.get());
    }
}
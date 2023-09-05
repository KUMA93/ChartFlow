package com.ssafy.chartflow.user.service;

import com.ssafy.chartflow.security.service.JwtService;
import com.ssafy.chartflow.user.dto.RequestLoginDto;
import com.ssafy.chartflow.user.dto.ResponseAuthenticationDto;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    public UserServiceTest() {
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
        RequestLoginDto loginDto = new RequestLoginDto("test@email.com", "password");
        User mockUser = User.builder()
                .email("test@email.com")
                .password("password")
                .build();
        ResponseAuthenticationDto expectedResponse = ResponseAuthenticationDto.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(mockUser);
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(any(UserDetails.class))).thenReturn("refreshToken");

        ResponseAuthenticationDto actualResponse = userService.login(loginDto);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testRegister() {
        User user = User.builder()
                .email("test@email.com")
                .name("John")
                .password("password")
                .nickname("johnny")
                .build();

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.regist("test@email.com", "password", "John", "johnny");

        assertEquals(user, registeredUser);
    }
}
package com.fetin2026.whtie_label.usertest;

import com.fetin2026.whtie_label.dto.register.UserRegisterDTO;
import com.fetin2026.whtie_label.repository.UsersRepository;
import com.fetin2026.whtie_label.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith({org.mockito.junit.jupiter.MockitoExtension.class})
public class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    private UserRegisterDTO registerDTO;

    @BeforeEach
    void setup(){
        registerDTO = new UserRegisterDTO("Gab", "gab@email.com", "teste");
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists(){
        when(usersRepository.findByEmail(registerDTO.email()));

        assertThrows(RuntimeException.class, () -> usersService.register(registerDTO));
    }
}

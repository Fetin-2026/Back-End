package com.fetin2026.whtie_label.service;

import com.fetin2026.whtie_label.dto.delete.UserDeleteDTO;
import com.fetin2026.whtie_label.dto.register.UserRegisterDTO;
import com.fetin2026.whtie_label.dto.update.UserUpdateDTO;
import com.fetin2026.whtie_label.entity.Users;
import com.fetin2026.whtie_label.exception.InvalidEmailException;
import com.fetin2026.whtie_label.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$");

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public List<Users> allUsers(){
        return usersRepository.findAll();
    }

    public String register(UserRegisterDTO dto){
        Users user = new Users();
        user.setEmail(validateEmail(dto.email()));
        user.setName(dto.name());
        user.setPassword(dto.password());
        usersRepository.save(user);
        return "Usuário Registrado com sucesso";
    }

    public String update(UserUpdateDTO dto){
        Users users  = usersRepository.findById(dto.id()).orElseThrow(()->new RuntimeException("Usuario nao encontrado"));
        if(dto.email() != null){
            users.setEmail(dto.email());
        }
        if(dto.name() != null){
            users.setName(dto.name());
        }
        if (dto.password() != null){
            users.setPassword(dto.password());
        }

        usersRepository.save(users);
        return "Dados atualizados com sucesso";
    }

    public String delete(UserDeleteDTO dto){
        usersRepository.deleteById(dto.id());
        return "Usuário Deletado com Sucesso!";
    }

    private String validateEmail(String email){
        if(usersRepository.findByEmail(email).isPresent() || email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new InvalidEmailException("Email Invalido por favor digite um email valido");
        }
        return email;
    }
}

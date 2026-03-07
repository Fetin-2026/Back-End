package com.fetin2026.whtie_label.controller;

import com.fetin2026.whtie_label.dto.delete.UserDeleteDTO;
import com.fetin2026.whtie_label.dto.register.UserRegisterDTO;
import com.fetin2026.whtie_label.dto.update.UserUpdateDTO;
import com.fetin2026.whtie_label.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(usersService.allUsers());
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO data){
        return ResponseEntity.ok(usersService.register(data));
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDTO data){
        return ResponseEntity.ok(usersService.update(data));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteDTO data){
        return ResponseEntity.ok(usersService.delete(data));
    }
}

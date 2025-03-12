package com.vergueiro_group.notifyhub_backend_bff.controller;


import com.vergueiro_group.notifyhub_backend_bff.business.UserService;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.EnderecoDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.TelefoneDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService(userDTO);
    }

    @GetMapping
    public ResponseEntity<UserDTO> searchUserForEmail(
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.searchUserForEmail(email,token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserForEmail(
            @PathVariable String email,
            @RequestHeader("Authorization") String token) {
        userService.deleteUserForEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateDataUser(@RequestBody UserDTO dto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateDataUser(token, dto));
    }

    @PutMapping("/address")
    public ResponseEntity<EnderecoDTO> updateAddress(@RequestBody EnderecoDTO dto,
                                                     @RequestParam("id") UUID id,
                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateAddress(dto, id, token));
    }

    @PutMapping("/telephone")
    public ResponseEntity<TelefoneDTO> updateTelephone(@RequestBody TelefoneDTO dto,
                                                       @RequestParam("id") UUID id,
                                                       @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateTelephone(dto, id, token));
    }

    @PostMapping("/address")
    public ResponseEntity<EnderecoDTO> addAddress(@RequestBody EnderecoDTO dto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.addAddress(token, dto));
    }

    @PostMapping("/telephone")
    public ResponseEntity<TelefoneDTO> addPhone(@RequestBody TelefoneDTO dto,
                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.addPhone(token, dto));
    }

}
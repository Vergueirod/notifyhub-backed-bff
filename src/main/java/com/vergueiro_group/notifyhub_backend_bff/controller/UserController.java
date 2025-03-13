package com.vergueiro_group.notifyhub_backend_bff.controller;


import com.vergueiro_group.notifyhub_backend_bff.business.UserService;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.EnderecoDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.TelefoneDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "user", description = "add, user and login")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Salvar Usuários",
            description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários",
            description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String userLogin(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email",
            description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UserDTO> searchUserForEmail(
            @RequestParam("email") String email,
            @RequestHeader(name="Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.searchUserForEmail(email,token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuários por Id",
            description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<Void> deleteUserForEmail(
            @PathVariable String email,
            @RequestHeader(name="Authorization", required = false) String token) {
        userService.deleteUserForEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuários",
            description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UserDTO> updateDataUser(@RequestBody UserDTO dto,
                                                  @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateDataUser(token, dto));
    }

    @PutMapping("/address")
    @Operation(summary = "Atualiza Endereço de Usuários",
            description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTO> updateAddress(@RequestBody EnderecoDTO dto,
                                                     @RequestParam("id") UUID id,
                                                     @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateAddress(dto, id, token));
    }

    @PutMapping("/telephone")
    @Operation(summary = "Atualiza Telefone de Usuários",
            description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTO> updateTelephone(@RequestBody TelefoneDTO dto,
                                                       @RequestParam("id") UUID id,
                                                       @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateTelephone(dto, id, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Salva Endereço de Usuários",
            description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTO> addAddress(@RequestBody EnderecoDTO dto,
                                                  @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(userService.addAddress(token, dto));
    }

    @PostMapping("/telephone")
    @Operation(summary = "Salva Telefone de Usuários",
            description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTO> addPhone(@RequestBody TelefoneDTO dto,
                                                @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(userService.addPhone(token, dto));
    }

}
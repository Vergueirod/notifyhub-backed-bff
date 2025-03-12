package com.vergueiro_group.notifyhub_backend_bff.infrastructure.client;

import com.vergueiro_group.notifyhub_backend_bff.business.dto.EnderecoDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.TelefoneDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTO searchUserForEmail(@RequestParam("email") String email,
                               @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    String login(@RequestBody UserDTO userDTO);

    @DeleteMapping("/{email}")
    Void deletaUserForEmail(
            @PathVariable String email,
            @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTO updateDataUser(@RequestBody UserDTO dto,
                           @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    EnderecoDTO updateAddress(@RequestBody EnderecoDTO dto,
                              @RequestParam("id") UUID id,
                              @RequestHeader("Authorization") String token);

    @PutMapping("/telephone")
    TelefoneDTO updateTelephone(@RequestBody TelefoneDTO dto,
                                                       @RequestParam("id") UUID id,
                                                       @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    EnderecoDTO addAddress(@RequestBody EnderecoDTO dto,
                                                  @RequestHeader("Authorization") String token);

    @PostMapping("/telephone")
    TelefoneDTO addPhone(@RequestBody TelefoneDTO dto,
                         @RequestHeader("Authorization") String token);
}

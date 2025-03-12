package com.vergueiro_group.notifyhub_backend_bff.business;

import com.vergueiro_group.notifyhub_backend_bff.business.dto.EnderecoDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.TelefoneDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.UserDTO;
import com.vergueiro_group.notifyhub_backend_bff.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient client;

    public UserDTO saveUser(UserDTO userDTO) {
        return client.saveUser(userDTO);
    }

    public String userLogin(UserDTO userDTO){
        return client.login(userDTO);
    }

    public UserDTO searchUserForEmail(String email, String token) {
        return client.searchUserForEmail(email, token);
    }

    public void deleteUserForEmail(String email, String token) {
        client.deletaUserForEmail(email, token);
    }

    public UserDTO updateDataUser(String token, UserDTO dto){
        return client.updateDataUser(dto, token);
    }
    public EnderecoDTO updateAddress(EnderecoDTO dto, UUID id,  String token) {
        return client.updateAddress(dto, id ,token);
    }

    public TelefoneDTO updateTelephone(TelefoneDTO dto, UUID id,  String token){
        return client.updateTelephone(dto, id, token);
    }

    public EnderecoDTO addAddress(String token, EnderecoDTO dto) {
        return client.addAddress(dto, token );
    }

    public TelefoneDTO addPhone(String token, TelefoneDTO dto){
        return client.addPhone(dto, token );
    }
}

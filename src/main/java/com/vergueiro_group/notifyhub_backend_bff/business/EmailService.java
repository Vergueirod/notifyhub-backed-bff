package com.vergueiro_group.notifyhub_backend_bff.business;


import com.vergueiro_group.notifyhub_backend_bff.business.dto.TaskDTO;
import com.vergueiro_group.notifyhub_backend_bff.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void sendEmail(TaskDTO dto){
        emailClient.sendEmail(dto);
    }
}

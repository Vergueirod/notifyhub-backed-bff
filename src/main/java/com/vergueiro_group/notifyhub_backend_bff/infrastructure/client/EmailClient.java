package com.vergueiro_group.notifyhub_backend_bff.infrastructure.client;

import com.vergueiro_group.notifyhub_backend_bff.business.dto.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

    void sendEmail(@RequestBody TaskDTO dto);
}

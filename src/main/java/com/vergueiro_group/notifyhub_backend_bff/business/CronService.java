package com.vergueiro_group.notifyhub_backend_bff.business;

import com.vergueiro_group.notifyhub_backend_bff.business.dto.LoginRequestDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.TaskDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.UserDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.enums.StatusNotificationEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TaskService taskService;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;

    @Value("${user.password}")
    private String password;

    @Scheduled(cron = "${cron.hour}")
    public void searchTaskNextHour(){
        String token = login(UserDTO.builder()
                .email(email)
                .password(password)
                .build());
        log.info("Iniciada a busca de tarefa");
        LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
        LocalDateTime futureHourPlusFive = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TaskDTO> taskList = taskService.searchScheduledTasksByPeriod(futureHour, futureHourPlusFive, token);
        log.info("Tarefas encontradas " + taskList);
        taskList.forEach(task -> {
            emailService.sendEmail(task);
            log.info("Email enviado para o usuario " + task.getUserEmail());
            taskService.updateStatus(StatusNotificationEnum.NOTIFIED, task.getId(), token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }
    public String login(UserDTO dto){
        return userService.userLogin(dto);
    }
}


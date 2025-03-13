package com.vergueiro_group.notifyhub_backend_bff.business;


import com.vergueiro_group.notifyhub_backend_bff.business.dto.TaskDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.enums.StatusNotificationEnum;
import com.vergueiro_group.notifyhub_backend_bff.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;

    public TaskDTO saveTask(String token, TaskDTO dto) {
        return taskClient.saveTask(dto, token);
    }

    public List<TaskDTO> searchScheduledTasksByPeriod(LocalDateTime startDate,
                                                      LocalDateTime endDate,
                                                      String token) {
        return taskClient.searchScheduledTasksByPeriod(endDate, startDate, token);
    }

    public List<TaskDTO> findByUserEmail(String token) {
        return taskClient.findByEmailUser(token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(token, id);
    }

    public TaskDTO updateStatus(StatusNotificationEnum status, String id, String token) {
        return  taskClient.updateStatusNotification(status, id, token);
    }

    public TaskDTO updateTask(TaskDTO dto, String id, String token) {
        return  taskClient.updateTask(dto, id, token);
    }
}

package com.vergueiro_group.notifyhub_backend_bff.infrastructure.client;

import com.vergueiro_group.notifyhub_backend_bff.business.dto.TaskDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.enums.StatusNotificationEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "scheduler-task", url = "${task.url}")
public interface TaskClient {

    @PostMapping
    TaskDTO saveTask(@RequestBody TaskDTO dto,
                     @RequestHeader("Authorization") String token);


    @GetMapping("/events")
    List<TaskDTO> searchScheduledTasksByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TaskDTO> findByEmailUser(
            @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTO updateStatusNotification(
            @RequestParam("status") StatusNotificationEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTO updateTask(
            @RequestBody TaskDTO dto,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);
}

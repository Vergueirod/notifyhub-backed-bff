package com.vergueiro_group.notifyhub_backend_bff.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vergueiro_group.notifyhub_backend_bff.business.enums.StatusNotificationEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

    private String id;
    private String task;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss" )
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss" )
    private LocalDateTime eventData;
    private String userEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss" )
    private LocalDateTime updateData;
    private StatusNotificationEnum statusNotificationEnum;
}

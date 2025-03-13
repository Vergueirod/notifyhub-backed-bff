package com.vergueiro_group.notifyhub_backend_bff.controller;

import com.vergueiro_group.notifyhub_backend_bff.business.TaskService;
import com.vergueiro_group.notifyhub_backend_bff.business.dto.TaskDTO;
import com.vergueiro_group.notifyhub_backend_bff.business.enums.StatusNotificationEnum;
import com.vergueiro_group.notifyhub_backend_bff.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO dto,
                                            @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.saveTask(token, dto));
    }

    @GetMapping("/events")
    @Operation(summary = "Busca tarefas por Período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TaskDTO>> searchScheduledTasksByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestHeader(name="Authorization", required = false) String token){
            return ResponseEntity.ok(taskService.searchScheduledTasksByPeriod(startDate, endDate, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usuário",
            description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TaskDTO>> findByEmailUser(
            @RequestHeader(name="Authorization", required = false) String token){
            return ResponseEntity.ok(taskService.findByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id", description = "Deleta tarefas cadastrads por ID")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deleteTaskById(
            @RequestParam("id") String id,
            @RequestHeader(name="Authorization", required = false) String token) {
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TaskDTO> updateStatusNotification(
            @RequestParam("status") StatusNotificationEnum status,
            @RequestParam("id") String id,
            @RequestHeader(name="Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.updateStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alterads")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TaskDTO> updateTask(
            @RequestBody TaskDTO dto,
            @RequestParam("id") String id,
            @RequestHeader(name="Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.updateTask(dto, id, token));
    }
}

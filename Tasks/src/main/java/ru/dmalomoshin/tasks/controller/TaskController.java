package ru.dmalomoshin.tasks.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dmalomoshin.tasks.model.Task;
import ru.dmalomoshin.tasks.model.TaskStatus;
import ru.dmalomoshin.tasks.service.TaskService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final Counter allTasksCounter = Metrics.counter("all_tasks_count");

    private final TaskService taskService;

    @GetMapping
    public List<Task> taskList() {
        allTasksCounter.increment();
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

package taskmanager;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la logica de negocio para la gestion de tareas.
 */
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo de la tarea no puede estar vacio.");
        }
        boolean alreadyExists = repository.findAll().stream()
            .anyMatch(t -> t.getTitle().equalsIgnoreCase(title.trim()));
        if (alreadyExists) {
            throw new IllegalStateException("Ya existe una tarea con ese titulo.");
        }
        return repository.save(title);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task updateTaskTitle(int id, String newTitle) {
        Task task = findTaskOrThrow(id);
        task.setTitle(newTitle);
        return task;
    }

    public void completeTask(int id) {
        Task task = findTaskOrThrow(id);
        task.markAsCompleted();
    }

    public void deleteTask(int id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new IllegalArgumentException("No existe una tarea con ID: " + id);
        }
    }

    private Task findTaskOrThrow(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No existe una tarea con ID: " + id));
    }
}

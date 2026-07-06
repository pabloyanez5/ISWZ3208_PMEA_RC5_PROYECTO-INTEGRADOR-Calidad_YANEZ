package taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio responsable del almacenamiento de tareas en memoria.
 */
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task save(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> findById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public boolean deleteById(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    public int count() {
        return tasks.size();
    }
}

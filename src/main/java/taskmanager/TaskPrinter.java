package taskmanager;

import java.util.List;

/**
 * Responsable de mostrar las tareas en consola.
 */
public class TaskPrinter {

    public void printAll(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("=== Lista de Tareas ===");
        tasks.forEach(System.out::println);
        System.out.println("Total: " + tasks.size() + " tarea(s).");
    }

    public void printTask(Task task) {
        System.out.println(task);
    }
}

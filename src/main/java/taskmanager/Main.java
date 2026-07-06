package taskmanager;

/**
 * Punto de entrada de la aplicacion.
 */
public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        TaskPrinter printer = new TaskPrinter();

        service.createTask("Completar proyecto de Calidad");
        service.createTask("Configurar pipeline CI/CD");
        service.createTask("Escribir pruebas unitarias");

        printer.printAll(service.getAllTasks());

        service.completeTask(1);
        service.deleteTask(2);

        System.out.println("\n--- Estado final ---");
        printer.printAll(service.getAllTasks());
    }
}

package taskmanager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
class TaskServiceTest {
    private TaskService service;
    @BeforeEach
    void setUp() { service = new TaskService(new TaskRepository()); }
    @Test
    void crearTarea_conTituloValido_debeRetornarTarea() {
        Task task = service.createTask("Mi primera tarea");
        assertNotNull(task);
        assertEquals("Mi primera tarea", task.getTitle());
        assertFalse(task.isCompleted());
    }
    @Test
    void crearTarea_conTituloVacio_debeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> service.createTask(""));
    }
    @Test
    void crearTarea_conTituloNulo_debeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> service.createTask(null));
    }
    @Test
    void crearTarea_duplicada_debeLanzarExcepcion() {
        service.createTask("Tarea duplicada");
        assertThrows(IllegalStateException.class, () -> service.createTask("Tarea duplicada"));
    }
    @Test
    void obtenerTareas_sinTareas_debeRetornarListaVacia() {
        assertTrue(service.getAllTasks().isEmpty());
    }
    @Test
    void obtenerTareas_conTareas_debeRetornarTodasLasTareas() {
        service.createTask("Tarea 1");
        service.createTask("Tarea 2");
        assertEquals(2, service.getAllTasks().size());
    }
    @Test
    void completarTarea_conIdValido_debeMarcarComoCompletada() {
        Task task = service.createTask("Tarea a completar");
        service.completeTask(task.getId());
        assertTrue(service.getAllTasks().get(0).isCompleted());
    }
    @Test
    void completarTarea_conIdInvalido_debeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> service.completeTask(99));
    }
    @Test
    void eliminarTarea_conIdValido_debeReducirLista() {
        Task task = service.createTask("Tarea a eliminar");
        service.deleteTask(task.getId());
        assertTrue(service.getAllTasks().isEmpty());
    }
    @Test
    void eliminarTarea_conIdInvalido_debeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask(99));
    }
    @Test
    void actualizarTitulo_conDatosValidos_debeActualizar() {
        Task task = service.createTask("Titulo viejo");
        service.updateTaskTitle(task.getId(), "Titulo nuevo");
        assertEquals("Titulo nuevo", service.getAllTasks().get(0).getTitle());
    }
    @Test
    void actualizarTitulo_conIdInvalido_debeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> service.updateTaskTitle(99, "Nuevo titulo"));
    }
}

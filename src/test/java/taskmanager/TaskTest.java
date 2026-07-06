package taskmanager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TaskTest {
    @Test
    void crearTask_conDatosValidos_debeInicializarCorrectamente() {
        Task task = new Task(1, "Estudiar Clean Code");
        assertEquals(1, task.getId());
        assertEquals("Estudiar Clean Code", task.getTitle());
        assertFalse(task.isCompleted());
    }
    @Test
    void crearTask_conTituloVacio_debeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Task(1, ""));
    }
    @Test
    void marcarComoCompletada_debeActualizarEstado() {
        Task task = new Task(1, "Tarea test");
        task.markAsCompleted();
        assertTrue(task.isCompleted());
    }
    @Test
    void toString_tareaCompletada_debeContenerEtiqueta() {
        Task task = new Task(1, "Tarea test");
        task.markAsCompleted();
        assertTrue(task.toString().contains("[COMPLETADA]"));
    }
    @Test
    void toString_tareaPendiente_debeContenerEtiqueta() {
        Task task = new Task(1, "Tarea test");
        assertTrue(task.toString().contains("[PENDIENTE]"));
    }
}

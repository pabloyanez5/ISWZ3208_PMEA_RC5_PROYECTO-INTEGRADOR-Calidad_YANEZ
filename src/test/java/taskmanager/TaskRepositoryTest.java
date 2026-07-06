package taskmanager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TaskRepositoryTest {
    private TaskRepository repository;
    @BeforeEach
    void setUp() { repository = new TaskRepository(); }
    @Test
    void guardar_debeIncrementarContador() {
        repository.save("Tarea 1");
        repository.save("Tarea 2");
        assertEquals(2, repository.count());
    }
    @Test
    void buscarPorId_existente_debeRetornarTarea() {
        Task saved = repository.save("Tarea buscable");
        assertTrue(repository.findById(saved.getId()).isPresent());
    }
    @Test
    void buscarPorId_inexistente_debeRetornarVacio() {
        assertTrue(repository.findById(99).isEmpty());
    }
    @Test
    void eliminarPorId_existente_debeRetornarTrue() {
        Task saved = repository.save("Tarea a borrar");
        assertTrue(repository.deleteById(saved.getId()));
        assertEquals(0, repository.count());
    }
    @Test
    void eliminarPorId_inexistente_debeRetornarFalse() {
        assertFalse(repository.deleteById(99));
    }
}

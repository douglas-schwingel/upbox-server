//package br.com.upboxserver.repository;
//
//import br.com.upboxserver.controller.UsuarioController;
//import br.com.upboxserver.models.Usuario;
//import com.google.gson.Gson;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.UUID;
//
//import static org.junit.Assert.*;
//
//public class UsuarioRepositoryTest {
//
//    private Usuario joseSilva;
//
//    @Mock
//    private UsuarioRepository repository;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        joseSilva = new Usuario();
//        joseSilva.setNome("José da Silva");
//        joseSilva.setUuid(UUID.randomUUID());
//        joseSilva.setEmail("josesilva@gmail.com");
//        joseSilva.setUsername("josesilva");
//        joseSilva.setSenha("123mudar");
//    }
//
////    @Test
////    public void deveSalvarUsuario() {
////
////        UsuarioController controller = new UsuarioController(repository);
////
////        Mockito.when(repository.salva(joseSilva)).thenReturn(new Gson().toJson(joseSilva));
////        String s = controller.salvaUsuario(new Gson().toJson(joseSilva));
////        assertEquals(new Gson().toJson(joseSilva), s);
////
////    }
//
//}


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import Negocio.Factoria.FactoriaSA;
import Negocio.Usuario.SAUsuario;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferUsuario;

public class UsuarioTest {

  @Test
  public void test() {
    SAUsuario saUsuario = FactoriaSA.getInstancia().generarSAUsuario();
    TransferUsuario tU1 = new TransferAlumno();
    tU1.setId("s");
    tU1.setPassword("s");
    saUsuario.crearUsuario(tU1);
    assertEquals(true, saUsuario.crearUsuario(tU1));

    TransferUsuario tU2 = new TransferAlumno();
    tU2.setId("a");
    saUsuario.crearUsuario(tU2);
    assertEquals(true, saUsuario.crearUsuario(tU2));

    TransferUsuario tU3 = new TransferAlumno();
    tU3.setId("s");
    saUsuario.crearUsuario(tU3);
    assertEquals(false, saUsuario.crearUsuario(tU3));

    saUsuario.crearUsuario(tU3);
    assertEquals(true, saUsuario.eliminarUsuario(tU2.getId()));
    assertEquals(false, saUsuario.eliminarUsuario(tU2.getId()));

    assertEquals(false, saUsuario.iniciarSesion(tU1.getId(), "a"));
    assertEquals(true, saUsuario.iniciarSesion(tU1.getId(), "s"));

    tU1.setPassword("a");
    assertEquals(true, saUsuario.editarUsuario(tU1));
    assertEquals(true, saUsuario.iniciarSesion(tU1.getId(), "a"));
    assertEquals(false, saUsuario.iniciarSesion(tU1.getId(), "s"));
  }
}
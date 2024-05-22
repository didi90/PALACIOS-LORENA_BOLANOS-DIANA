package dh.backend.test;

import dh.backend.dao.impl.OdontologoEnMemoria;
import dh.backend.model.Odontologo;
import dh.backend.service.OdontologoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoEnMemoriaTest {

    private static OdontologoService odontologoService = new OdontologoService(new OdontologoEnMemoria());

    @Test
    @DisplayName("Testear que un odontólogo fue guardado")
    void testOdontologoGuardado(){
        Odontologo odontologo = new Odontologo("12345", "Hugo", "Martínez");
        Odontologo odontologoDesdeLaMemoria = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoDesdeLaMemoria);
    }

    @Test
    @DisplayName("Testear que se puedan mostrar todos los odontólogos")
    void testearTodosLosOdontologos(){
        Odontologo odontologo = new Odontologo("12345", "Hugo", "Martínez");
        Odontologo odontologo1 = new Odontologo("2234", "Lorena", "Palacios");
        Odontologo odontologo2 = new Odontologo("5645", "Diana", "Bolanios");
        odontologoService.registrarOdontologo(odontologo);
        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);
        List<Odontologo> odontologosRecibidos= odontologoService.buscarTodos();
        assertEquals(3,odontologosRecibidos.size());

    }


}
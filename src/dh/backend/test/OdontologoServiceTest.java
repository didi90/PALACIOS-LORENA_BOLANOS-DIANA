package dh.backend.test;

import dh.backend.dao.impl.OdontologoIDaoH2;
import dh.backend.model.Odontologo;
import dh.backend.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService(new OdontologoIDaoH2());

    @BeforeAll
    static  void crearTablas(){

        Connection connection = null;

        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/PrimerExamen;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }finally {
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
            }
        }

    }

    @Test
    @DisplayName("Testear que un odontólogo persiste en la base de datos")
    void testearOdontologoEnBaseDeDatos(){
        Odontologo odontologo = new Odontologo("12345", "Hugo", "Martínez");
        Odontologo odontologoPersistido = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoPersistido);
    }

    @Test
    @DisplayName("Testear que se puedan mostrar todos los odontólogos")
    void testearTodosLosOdontologos(){
        List<Odontologo> odontologosRecibidos= odontologoService.buscarTodos();
        assertEquals(2,odontologosRecibidos.size());

    }








}
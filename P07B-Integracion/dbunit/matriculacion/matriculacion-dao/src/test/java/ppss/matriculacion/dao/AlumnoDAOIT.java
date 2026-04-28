package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Integracion-Fase1")
public class AlumnoDAOIT {

    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;
    private IAlumnoDAO alumnoDAO;

    @BeforeEach
    public void setUp() throws Exception {
        databaseTester = new MiJdbcDatabaseTester(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/matriculacion?useSSL=false",
                "ppss_user",
                "ppss-2025"
        );

        connection = databaseTester.getConnection();
        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
    }

    private void inicializarBD(String ficheroXml) throws Exception {
        IDataSet dataSet = new FlatXmlDataFileLoader().load(ficheroXml);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private void comprobarTablaAlumnos(String ficheroEsperado) throws Exception {
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load(ficheroEsperado);
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(
                new SortedTable(expectedTable, new String[]{"nif"}),
                new SortedTable(actualTable, new String[]{"nif"})
        );
    }

    private AlumnoTO crearAlumno(String nif, String nombre, String direccion,
                                 String email, LocalDate fechaNacimiento) {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(nif);
        alumno.setNombre(nombre);
        alumno.setDireccion(direccion);
        alumno.setEmail(email);
        alumno.setFechaNacimiento(fechaNacimiento);
        return alumno;
    }

    @Test
    public void testA1() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        AlumnoTO alumno = crearAlumno(
                "33333333C",
                "Elena Aguirre Juarez",
                null,
                null,
                LocalDate.of(1985, 2, 22)
        );

        assertDoesNotThrow(() -> alumnoDAO.addAlumno(alumno));

        comprobarTablaAlumnos("/tabla-esperada-testA1.xml");
    }

    @Test
    public void testA2() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        AlumnoTO alumno = crearAlumno(
                "11111111A",
                "Alfonso Ramirez Ruiz",
                "Rambla, 22",
                "alfonso@ppss.ua.es",
                LocalDate.of(1982, 2, 22)
        );

        DAOException ex = assertThrows(
                DAOException.class,
                () -> alumnoDAO.addAlumno(alumno)
        );

        assertTrue(ex.getMessage().contains("Error al conectar con BD"));
    }

    @Test
    public void testA3() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        AlumnoTO alumno = crearAlumno(
                "44444444D",
                null,
                null,
                null,
                LocalDate.of(1982, 2, 22)
        );

        DAOException ex = assertThrows(
                DAOException.class,
                () -> alumnoDAO.addAlumno(alumno)
        );

        assertTrue(ex.getMessage().contains("Error al conectar con BD"));
    }

    @Test
    public void testA4() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        DAOException ex = assertThrows(
                DAOException.class,
                () -> alumnoDAO.addAlumno(null)
        );

        assertTrue(ex.getMessage().contains("Alumno nulo"));
    }

    @Test
    public void testA5() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        AlumnoTO alumno = crearAlumno(
                null,
                "Pedro Garcia Lopez",
                null,
                null,
                LocalDate.of(1982, 2, 22)
        );

        DAOException ex = assertThrows(
                DAOException.class,
                () -> alumnoDAO.addAlumno(alumno)
        );

        assertTrue(ex.getMessage().contains("Error al conectar con BD"));
    }

    @Test
    public void testB1() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        assertDoesNotThrow(() -> alumnoDAO.delAlumno("11111111A"));

        comprobarTablaAlumnos("/tabla-esperada-testB1.xml");
    }

    @Test
    public void testB2() throws Exception {
        inicializarBD("/tabla-inicial.xml");

        DAOException ex = assertThrows(
                DAOException.class,
                () -> alumnoDAO.delAlumno("33333333C")
        );

        assertTrue(ex.getMessage().contains("No se ha borrado ningún alumno"));
    }
}
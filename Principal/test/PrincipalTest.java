

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import principal.CamionesPesados;
import principal.Carros;
import principal.Motos;
import principal.MotosAcuaticas;
import principal.Principal;


public class PrincipalTest {
    
    public PrincipalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of indiceCarrosMBarato method, of class Principal.
     */
    @Test
    public void testIndiceCarrosMBarato() {
        System.out.println("Test Carros mas Caro");
        Carros[] carritos = new Carros[] { new Carros("Toyota","Hilux","Rojo",12000,2020)};
        Object expResult = 0;
        Object result = Principal.indiceCarrosMBarato(carritos);
        assertEquals(expResult, result);
         //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of indiceCamionPesado method, of class Principal.
     */
    @Test
    public void testIndiceCamionPesado() {
    System.out.println("Test Camion mas Pesado");
    CamionesPesados[] camiones = new CamionesPesados[] { new CamionesPesados("Kia","Motors","Negro",24000,2022,1000,"Si")};
    Object expResult = 0;
    Object result = Principal.indiceCamionPesado(camiones);
    assertEquals(expResult, result);
         //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of indicepotenciamoto method, of class Principal.
     */
    @Test
    public void testIndicepotenciamoto() {
        System.out.println("Test Mayor potencia en turbinas");
        MotosAcuaticas[] motos = new MotosAcuaticas[] { new MotosAcuaticas("Honda","23k","Verde",1400,2010,1000,"No")};
        Object expResult = 0;
        Object result = Principal.indicepotenciamoto(motos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }


    /**
     * Test of AutoBarato method, of class Principal.
     */
    @Test
    public void testAutoBarato() {
        System.out.println("Test Auto mas Barato");
        Carros[] carrito = new Carros[] { new Carros("BMW","56k","Amarillo",1500,2000)};
        Object expResult = null;
        Object result = Principal.indiceCarrosMBarato(carrito);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of MotoBarata method, of class Principal.
     */
    @Test
    public void testMotoBarata() {
       System.out.println("Test Moto mas Barata");
        Motos[] motost = new Motos[] { new Motos("Yamaha","R56","Azul",2000,2015)};
        Object expResult = null;
        Object result = Principal.indiceMotosBarata(motost);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    @Test
    public void testIndipotenciamoto() {
        System.out.println("Test indice potencia moto");
        MotosAcuaticas[] motos = new MotosAcuaticas[] { new MotosAcuaticas("Honda","23k","Verde",1400,2010,1000,"No")};
        Object expResult = null;
        Object result = Principal.indicepotenciamoto(motos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
}

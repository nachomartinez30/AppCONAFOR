/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.conafor.taxonomia.mod;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ignacio.martinez
 */
public class CDColectaBotanicaTest {
    
    public CDColectaBotanicaTest() {
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
     * Test of getSecciones method, of class CDColectaBotanica.
     */
    @Test
    public void testGetSecciones() {
        System.out.println("getSecciones");
        CDColectaBotanica instance = new CDColectaBotanica();
        List<CatESeccionTaxonomica> expResult = null;
        List<CatESeccionTaxonomica> result = instance.getSecciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClaveCreada method, of class CDColectaBotanica.
     */
    @Test
    public void testGetClaveCreada() {
        System.out.println("getClaveCreada");
        int UPMID = 0;
        CDColectaBotanica instance = new CDColectaBotanica();
        List<String> expResult = null;
        List<String> result = instance.getClaveCreada(UPMID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreclave method, of class CDColectaBotanica.
     */
    @Test
    public void testGetPreclave() {
        System.out.println("getPreclave");
        int upmID = 0;
        CDColectaBotanica instance = new CDColectaBotanica();
        String expResult = "";
        String result = instance.getPreclave(upmID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUPMID method, of class CDColectaBotanica.
     */
    @Test
    public void testGetUPMID() {
        System.out.println("getUPMID");
        CDColectaBotanica instance = new CDColectaBotanica();
        List<Integer> expResult = null;
        List<Integer> result = instance.getUPMID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClaveColecta method, of class CDColectaBotanica.
     */
    @Test
    public void testGetClaveColecta() {
        System.out.println("getClaveColecta");
        int upmID = 0;
        CDColectaBotanica instance = new CDColectaBotanica();
        List<String> expResult = null;
        List<String> result = instance.getClaveColecta(upmID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertColectaBotanica method, of class CDColectaBotanica.
     */
    @Test
    public void testInsertColectaBotanica() {
        System.out.println("insertColectaBotanica");
        CEColectaBotanica ceColecta = null;
        CDColectaBotanica instance = new CDColectaBotanica();
        instance.insertColectaBotanica(ceColecta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarClaveColecta method, of class CDColectaBotanica.
     */
    @Test
    public void testModificarClaveColecta() {
        System.out.println("modificarClaveColecta");
        String Clave = "";
        int UPMID = 0;
        String ClaveNueva = "";
        CDColectaBotanica instance = new CDColectaBotanica();
        instance.modificarClaveColecta(Clave, UPMID, ClaveNueva);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarColectaBotanica method, of class CDColectaBotanica.
     */
    @Test
    public void testActualizarColectaBotanica() {
        System.out.println("actualizarColectaBotanica");
        CEColectaBotanica ceColecta = null;
        CDColectaBotanica instance = new CDColectaBotanica();
        instance.actualizarColectaBotanica(ceColecta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignarClaveColecta method, of class CDColectaBotanica.
     */
    @Test
    public void testAsignarClaveColecta() {
        System.out.println("asignarClaveColecta");
        String nombreTabla = "";
        String nombreCampo = "";
        int noIndividuo = 0;
        int sitioID = 0;
        String claveColecta = "";
        CDColectaBotanica instance = new CDColectaBotanica();
        instance.asignarClaveColecta(nombreTabla, nombreCampo, noIndividuo, sitioID, claveColecta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarClave method, of class CDColectaBotanica.
     */
    @Test
    public void testInsertarClave() {
        System.out.println("insertarClave");
        CEColectaBotanica ceColecta = null;
        int UPMID = 0;
        String claveColecta = "";
        CDColectaBotanica instance = new CDColectaBotanica();
        instance.insertarClave(ceColecta, UPMID, claveColecta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColectaBotanica method, of class CDColectaBotanica.
     */
    @Test
    public void testGetColectaBotanica() {
        System.out.println("getColectaBotanica");
        String claveColecta = "";
        CDColectaBotanica instance = new CDColectaBotanica();
        CEColectaBotanica expResult = null;
        CEColectaBotanica result = instance.getColectaBotanica(claveColecta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeClave method, of class CDColectaBotanica.
     */
    @Test
    public void testExisteClave() {
        System.out.println("existeClave");
        String claveColecta = "";
        CDColectaBotanica instance = new CDColectaBotanica();
        boolean expResult = false;
        boolean result = instance.existeClave(claveColecta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hayDatosColecta method, of class CDColectaBotanica.
     */
    @Test
    public void testHayDatosColecta() {
        System.out.println("hayDatosColecta");
        String claveColecta = "";
        CDColectaBotanica instance = new CDColectaBotanica();
        boolean expResult = false;
        boolean result = instance.hayDatosColecta(claveColecta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hayColecta method, of class CDColectaBotanica.
     */
    @Test
    public void testHayColecta() {
        System.out.println("hayColecta");
        CDColectaBotanica instance = new CDColectaBotanica();
        boolean expResult = false;
        boolean result = instance.hayColecta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarCapturaEspecie method, of class CDColectaBotanica.
     */
    @Test
    public void testValidarCapturaEspecie() {
        System.out.println("validarCapturaEspecie");
        String tabla = "";
        int sitioID = 0;
        CDColectaBotanica instance = new CDColectaBotanica();
        boolean expResult = false;
        boolean result = instance.validarCapturaEspecie(tabla, sitioID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarCapturaGenero method, of class CDColectaBotanica.
     */
    @Test
    public void testValidarCapturaGenero() {
        System.out.println("validarCapturaGenero");
        String tabla = "";
        int sitioID = 0;
        CDColectaBotanica instance = new CDColectaBotanica();
        boolean expResult = false;
        boolean result = instance.validarCapturaGenero(tabla, sitioID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarTipoComponente method, of class CDColectaBotanica.
     */
    @Test
    public void testValidarTipoComponente() {
        System.out.println("validarTipoComponente");
        int sitioID = 0;
        CDColectaBotanica instance = new CDColectaBotanica();
        boolean expResult = false;
        boolean result = instance.validarTipoComponente(sitioID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

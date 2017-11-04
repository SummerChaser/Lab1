package project_one;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Lab1Test {

    @Before
    public void setUp() throws Exception {
        Lab1.preDeal(); 
        System.out.println("before");
    }

    @Test
    public void testQueryBridgeWords1() {
        System.out.println( Lab1.queryBridgeWords("seek", "to"));
        String result = "No bridge words from \"seek\" to \"to\"!";
        System.out.println(result);
        System.out.println((Lab1.queryBridgeWords("seek", "to")));
        assertEquals(result, Lab1.queryBridgeWords("seek", "to"));
    }
    @Test
    public void testQueryBridgeWords2() {
        System.out.println( Lab1.queryBridgeWords("to", "explore"));
        String result = "No bridge words from \"to\" to \"explore\"!";
        System.out.println(result);
        assertEquals(result, Lab1.queryBridgeWords("to", "explore"));
    }
    @Test
    public void testQueryBridgeWords3() {
        System.out.println( Lab1.queryBridgeWords("explore", "new"));
        String result = "The bridge words from \"explore\" to \"new\" is:strange ";
        System.out.println(result);
        assertEquals(result, Lab1.queryBridgeWords("explore", "new"));
    }
    @Test
    public void testQueryBridgeWords4() {
        System.out.println( Lab1.queryBridgeWords("new", "and"));
        String result = "The bridge words from \"new\" to \"and\" is:life ";
        System.out.println(result);
        assertEquals(result, Lab1.queryBridgeWords("new", "and"));
    }
    @Test
    public void testQueryBridgeWords5() {
        System.out.println( Lab1.queryBridgeWords("and", "exciting"));
        String result = "No \"exciting\" in the graph!";
        System.out.println(result);
        assertEquals(result, Lab1.queryBridgeWords("and", "exciting"));
    }
    @Test
    public void testQueryBridgeWords6() {
        System.out.println( Lab1.queryBridgeWords("exciting", "synergies"));
        String result = "No \"exciting\" and \"synergies\" in the graph!";
        System.out.println(result);
        assertEquals(result, Lab1.queryBridgeWords("exciting", "synergies"));
    }
    @Test
    public void testQueryBridgeWords7() {
        System.out.println( Lab1.queryBridgeWords("and", "s"));
        String result = "No \"s\" in the graph!";
        System.out.println(result);
        assertEquals(result, Lab1.queryBridgeWords("and", "s"));
    }

}

package P3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
public class FriendshipGraphTest {
	/**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    public void getDistanceTest() {
    	FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person kitty = new Person("kitty");
		Person lilac = new Person("lilac");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(kitty);
		graph.addVertex(lilac);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		graph.addEdge(ross,kitty );
		graph.addEdge(kitty, ross);
		graph.addEdge(kitty, lilac);
		graph.addEdge(lilac, kitty);
		assertEquals(1,graph.getDistance(rachel,ross));
		assertEquals(2,graph.getDistance(rachel,ben));
		assertEquals(0,graph.getDistance(rachel,rachel));
		assertEquals(-1,graph.getDistance(rachel,kramer));
		assertEquals(3,graph.getDistance(rachel,lilac));
		assertEquals(2,graph.getDistance(ben,kitty));//测试全部成功
    }
    
    
}

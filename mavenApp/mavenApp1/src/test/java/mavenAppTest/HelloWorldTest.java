package mavenAppTest;
import static org.junit.Assert.*;
import mavenApp.HelloWorld;

import org.junit.Test;

/**
 * 
 *Used to test HelloWorld
 */
public class HelloWorldTest
{

		/**
		 * Test method for {@link com.jcg.antdemo.HelloWorld#add(int, int)}.
		 */
		@Test
		public void testAdd()
		{
				HelloWorld hw = new HelloWorld();
				assertEquals(5, hw.add(3, 2));
		}

}
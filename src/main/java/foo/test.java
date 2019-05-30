    
package foo;

/**
 * Foo class
 */
public class test {

    public static int div(int a, int b, int c, int d) throws Exception {
    	if (b == 0) {
    		throw new UnsupportedOperationException("Can't divide by zero!");
    	}
        return a / b;  
    }

}

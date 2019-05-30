    
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

public class test1 {

    public static int div(int e, int f, int g, int h) throws Exception {
    	if (f == 0) {
    		throw new UnsupportedOperationException("Can't divide by zero!");
    	}
        return e / f;  
    }

}


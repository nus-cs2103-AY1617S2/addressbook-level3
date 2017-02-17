package seedu.addressbook.common;

public class PolymorphismQuestion {

/* Brought up in tutorial (on method overriding):
 * method name and parameters makes up the method signature 
 * return types are not part of the signature
 * if we override a method in the subclass and make it have a different return type, there will be a run-time error 
 *
 * explanation was wrong, subclass cannot override the method with a different return type
 */
}


class MySuperclass {
    
    int myMethod() {
        return 0;
    }
    
}

class MySubclass extends MySuperclass {
    
    String myMethod() {
    // Compile time error : The return type is incompatible with MySuperclass.myMethod()
    // although return type is not part of the method signature, it must be the same as the superclass's method
        
        return "test";
    }
    
}
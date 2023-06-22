package playground;

// class which makes the analogies between Scala objects and Java statics
// access directly from Class, not instance. Cần từ khóa "static"
public class JavaPlayground {
    public static void main(String args[]) {

        System.out.println(Person.N_EYES); //Vẫn phải call Class trc
        System.out.println(Person.eye);
    }
}
class Person {
    // declare a constant variable (can't change, từ khóa "final")
    public static final int N_EYES = 2;
    // declare a variable (can change)
    public static int eye = 3;
}


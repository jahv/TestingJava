package jahv.java.testing.powermock;

import java.util.List;

/**
 * Created by ahernandez on 12/6/16.
 */
public class MyClass {

    private Dependency dependency;
    private Dependency dependency2;

    public String regards(int number, String name) {
        String regard = dependency.sayHello(name);
        return regard + " " + number;
    }

    public String regards2(int number, String name) {
        String regard = dependency.sayHello(name);
        regard = dependency.sayHello(name);
        return regard + " " + number;
    }

    public String notSetter() {
        if(dependency2 == null) {
            dependency2 = new Dependency();
        }

        String x = dependency2.getData();
        String y = dependency2.sayHello(x);
        return y;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    private int add(int a, int b) {
        return a+b;
    }

}

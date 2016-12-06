package jahv.java.testing.powermock;

import java.lang.reflect.Field;

import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * Created by ahernandez on 12/6/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MyClass.class)
public class MyClassTest {

    MyClass myClass;

    @Before
    public void setUp() {
        myClass = new MyClass();
    }

    /**
     * Testing with mocks and expected behavior
     */
    @Test
    public void normalTest() {
        Dependency mockDependency = EasyMock.createMock(Dependency.class); //Mock
        EasyMock.expect(mockDependency.sayHello(EasyMock.anyObject(String.class)))
                .andReturn("This is a mock"); //Expect behavior
        PowerMock.replayAll(mockDependency); //Replay for the behavior
        myClass.setDependency(mockDependency); //Inject mock

        String result = myClass.regards(1, "jahv");
        System.out.println(result);

        Assert.assertEquals("This is a mock 1", result);

        PowerMock.verifyAll(); //Verify all callings for mock methods
    }

    /**
     * Using times
     */
    @Test
    public void normalTest2() {
        Dependency mockDependency = EasyMock.createMock(Dependency.class); //Mock
        EasyMock.expect(mockDependency.sayHello(EasyMock.anyObject(String.class)))
                .andReturn("This is a mock").times(2); //Expect behavior
        PowerMock.replayAll(mockDependency); //Replay for the behavior
        myClass.setDependency(mockDependency); //Inject mock

        String result = myClass.regards2(1, "jahv");
        System.out.println(result);

        Assert.assertEquals("This is a mock 1", result);

        PowerMock.verifyAll();
    }


    /**
     * Using partial mock for only mocking the getData method
     * Inject mock by reflection
     */
    @Test
    public void injectByReflection() throws Exception {
        Dependency dependencyMock = PowerMock.createPartialMock(Dependency.class, "getData");
        EasyMock.expect(dependencyMock.getData()).andReturn("Comes from mock");
        PowerMock.replayAll(dependencyMock);

        Field field = myClass.getClass().getDeclaredField("dependency2");
        field.setAccessible(true);
        field.set(myClass, dependencyMock);

        String result = myClass.notSetter();
        System.out.println(result);

        Assert.assertEquals("Hello Comes from mock", result);

        PowerMock.verifyAll();
    }

    /**
     * Testing private
     */
    @Test
    public void testPrivate() throws Exception {
        int result = Whitebox.invokeMethod(myClass, "add", 1, 2);
        System.out.println(result);

        Assert.assertEquals(3, result);
    }

}

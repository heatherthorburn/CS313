/* To run tests start the server in the correct port for each test*/

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class Testing {
    @Test
    public void test1() throws Exception {
        String[] testArray = {"Heather","Thorburn"};
        String testCom = "getFirstName";
        OurObject testObj = new OurObject(testCom, testArray);

        Client client = new Client();
        OurObject result = client.run(testObj, 9999);

        Assert.assertArrayEquals(result.getArguments(), result.getArguments());
        Assert.assertEquals(result.getCommand(), "getFirstName");
        Assert.assertEquals(result.getResult(), "Heather");
    }

    @Test
    public void test2() throws Exception {
        String[] testArray = {"Martin","Harvey"};
        String testCom = "getSecondName";
        OurObject testObj = new OurObject(testCom, testArray);


        Client client = new Client();
        OurObject result = client.run(testObj, 9998);

        Assert.assertArrayEquals(result.getArguments(), testArray);
        Assert.assertEquals(result.getCommand(), "getSecondName");
        Assert.assertEquals(result.getResult(), "Harvey");
    }

    @Test
    public void test3() throws Exception {
        String[] testArray = {"Plamena","Peykova"};
        String testCom = "concat";
        OurObject testObj = new OurObject(testCom, testArray);

        Client client = new Client();
        OurObject result = client.run(testObj, 9997);

        Assert.assertArrayEquals(result.getArguments(), testArray);
        Assert.assertEquals(result.getCommand(), "concat");
        Assert.assertEquals(result.getResult(), "PlamenaPeykova");
    }

    @Test
    public void test4() throws Exception {
        String[] testArray = {"Jamie","Shepherd"};
        String testCom = "capitalise";
        OurObject testObj = new OurObject(testCom, testArray);

        Client client = new Client();
        OurObject result = client.run(testObj, 9996);

        Assert.assertArrayEquals(result.getArguments(), testArray);
        Assert.assertEquals(result.getCommand(), "capitalise");
        Assert.assertEquals(result.getResult(), "JAMIE SHEPHERD");
    }
}

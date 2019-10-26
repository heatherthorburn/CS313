import org.junit.*;

import javax.print.DocFlavor;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringClientTest {


    @Before
    public void setUp() throws IOException {
        StringServer server = new StringServer(304);;


    }

    @Test
    public void firstNameTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "304";

        ByteArrayInputStream in = new ByteArrayInputStream("getFirstName Martin Harvey".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("Martin", out.toString());
    }

    @Test
    public void SecondNameTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "304";

        ByteArrayInputStream in = new ByteArrayInputStream(("getSecondName Martin Harvey").getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("Harvey", out.toString());
    }

    @Test
    public void toUpperTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "304";

        ByteArrayInputStream in = new ByteArrayInputStream("toUpper Martin Harvey".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("MARTIN HARVEY", out.toString());
    }

    @Test
    public void toLowerTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "304";

        ByteArrayInputStream in = new ByteArrayInputStream("toLower Martin Harvey".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("martin harvey", out.toString());
    }


}
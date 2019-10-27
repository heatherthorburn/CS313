import org.junit.*;

import javax.print.DocFlavor;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringClientTest {


    @Before
    public void setUp() throws IOException {

    }

    @Test
    public void firstNameTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "308";
        ByteArrayInputStream in = new ByteArrayInputStream("getFirstName Martin Harvey".getBytes());

        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("Response: Martin", out.toString().trim());
    }

    @Test
    public void SecondNameTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "308";

        ByteArrayInputStream in = new ByteArrayInputStream(("getSecondName Martin Harvey").getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("Response: Harvey", out.toString().trim());
    }

    @Test
    public void toUpperTest(){
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "308";

        ByteArrayInputStream in = new ByteArrayInputStream("toUpperCase Martin Harvey".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringClient.run(args, System.in);

        assertEquals("Response: MARTIN HARVEY", out.toString().trim());
    }

    @Test
    public void concat(){
        System.out.println("TOLOWER");
        String[] args = new String[10];
        args[0] = "localhost";
        args[1] = "308";

        ByteArrayInputStream in = new ByteArrayInputStream("concat Martin Harvey".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printOut = new PrintStream(out);
        System.setOut(new PrintStream(out));
        StringClient.run(args, System.in);
        assertEquals("Response: MartinHarvey", out.toString().trim());
    }


}
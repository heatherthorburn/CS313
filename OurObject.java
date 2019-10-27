import java.io.Serializable;
import java.util.Arrays;

public class OurObject implements Serializable {
    private String command;
    private String[] arguments;
    private String result;

    /*public static OurObject fromString(String str) {
        String[] components = str.split(" ");
        String command = components[0];
        String[] arguments = Arrays.copyOfRange(components, 1, components.length);
        return new OurObject(command, arguments);
    }*/

    public OurObject(String com, String[] arg) {
        this.command = com;
        this.arguments = arg;
        this.result = null;
    }

    public void setCommand (String com) {
        this.command = com;
    }

    public void setArguments (String[] arg) {
        this.arguments = arg;
    }

    public void setResult (String res) {
        this.result = res;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }

    public String getResult() {
        return result;
    }



}

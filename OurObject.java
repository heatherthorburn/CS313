import java.io.Serializable;

public class OurObject implements Serializable {
    private String command;
    private String[] arguments;
    private String result;

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

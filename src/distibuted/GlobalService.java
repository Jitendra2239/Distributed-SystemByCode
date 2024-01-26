package distibuted;

import java.io.IOException;

public interface GlobalService extends MsgHandler {
    public void initialize(int x, FuncUser prog);
    public int computeGlobal() throws IOException;
}

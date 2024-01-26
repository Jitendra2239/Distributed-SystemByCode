package distibuted;

import java.io.IOException;

public interface Camera extends MsgHandler {
    void globalState() throws IOException;
}
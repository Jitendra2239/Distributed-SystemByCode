package distibuted;

import java.io.IOException;

public interface Lock extends MsgHandler {
    public void requestCS() throws IOException; //may block
    public void releaseCS() throws IOException;
}

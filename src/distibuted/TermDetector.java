package distibuted;

import java.io.IOException;

public interface TermDetector {
    public void initiate() throws IOException;
    public void sendAction();
    public void turnPassive() throws IOException;
    public void handleMsg(Msg m, int srcsId, String tag)throws IOException ;
}
package distibuted;

import java.io.IOException;
import java.util.TimerTask;
public class RestartTask extends TimerTask {
    MsgHandler app;
    public RestartTask(MsgHandler app) {
        this.app = app;
    }
    public void run() {
        try {
			app.handleMsg(null, 0, "restart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

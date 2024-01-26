package distibuted;

import java.io.IOException;

public interface Sensor extends MsgHandler {
    void localPredicateTrue(VectorClock vc) throws IOException;
}

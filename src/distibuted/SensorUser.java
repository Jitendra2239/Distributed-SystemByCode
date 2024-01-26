package distibuted;

public interface SensorUser extends MsgHandler {
    void globalPredicateTrue(int G[]);
    void globalPredicateFalse(int pid);
}

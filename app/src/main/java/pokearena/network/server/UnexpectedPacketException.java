package pokearena.network.server;

public class UnexpectedPacketException extends RuntimeException {

    public UnexpectedPacketException(ServerStateName serverStateName) {
        super("Unexpected packet received in state : " + serverStateName);
    }
}

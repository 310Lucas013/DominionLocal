package dominionshared.communication.websockets;

import seabattleshared.communication.messaging.EncapsulatingMessageGenerator;
import seabattleshared.communication.messaging.IEncapsulatingMessageGenerator;
import seabattleshared.serialization.ISerializer;
import seabattleshared.serialization.SerializationProvider;

public abstract class WebSocketBase {

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();

    public WebSocketBase()
    {

    }
    public abstract void start();

    public abstract void stop();

    public ISerializer<String> getSerializer() {
        return SerializationProvider.getSerializer();
    }
}

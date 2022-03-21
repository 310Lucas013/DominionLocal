package dominionshared.communication.websockets;

import dominionshared.communication.messaging.EncapsulatingMessageGenerator;
import dominionshared.communication.messaging.IEncapsulatingMessageGenerator;
import dominionshared.serialization.ISerializer;
import dominionshared.serialization.SerializationProvider;

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

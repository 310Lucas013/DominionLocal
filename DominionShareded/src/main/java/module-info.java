module dominionshared {
    requires gson;
    requires httpcore;
    requires httpclient;
    requires slf4j.api;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.desktop;

    exports dominionshared.models;
    exports dominionshared.communication.messaging;
    exports dominionshared.communication.messages;
    exports dominionshared.communication.websockets;
    exports dominionshared.logging;
    exports dominionshared.serialization;
    exports dominionshared.dominiongame;
    exports dominionshared.communication.rest;
    exports dominionshared.communication.rest.Shared;
    exports dominionshared.communication.rest.dto;

    opens dominionshared.models;
    opens dominionshared.communication.rest;
    opens dominionshared.communication.rest.Shared;
    opens dominionshared.communication.rest.dto;
    opens dominionshared.communication.messages;
    opens dominionshared.communication.messaging;
}
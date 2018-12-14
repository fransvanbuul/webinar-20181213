package com.example.giftcard;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GiftcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftcardApplication.class, args);
    }

    /*
     * The following is not really necessary for a demo app (and was not included
     * in the live demo), but it prevents an XStream security warning message.
     * In general, XStream serialization should usually be replaced by Jackson or
     * something else before going in production.
     */
    @Autowired
    public void config(Serializer serializer) {
        if (serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer) serializer).getXStream();
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[]{
                    "com.example.**",
                    "org.axonframework.**"});
        }
    }

}


package com.opencredo.examples.akkajava.simple;


import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;
import com.opencredo.examples.akkajava.Location;
import com.opencredo.examples.akkajava.Picture;

import java.util.Random;

class Camera extends UntypedActor {
    private final Uploader uploader ;
    private final Picture pic;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    // Take a picture and remember its' name
    public Picture takeAPic() {
        Random rand = new Random();
        int randomInt = rand.nextInt(1000);
        String name = "picture_" + randomInt;
        Picture pic = new Picture(name);
        return pic;
    }
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Location) {
            if (message instanceof Location) {
                Location loc = (Location) message;
                log.info("Location information: {} has been recorded", loc);

                Picture pic = takeAPic();
                log.info("A new picture {} has been taken", pic);
                uploader.upload(pic,loc);
                log.info("Picture {} has been uploaded", pic);
                getSender().tell(uploader, getSelf());
            } else {
                unhandled(message);
            }
        }
    }


    public static Props props() {
        return Props.create(new Creator<Camera>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Camera create() throws Exception {
                return new Camera();
            }
        });
    }

    public Camera() {
        uploader = new Uploader();
        pic = takeAPic();
        log.info("My camera has warmed up");

    }
}

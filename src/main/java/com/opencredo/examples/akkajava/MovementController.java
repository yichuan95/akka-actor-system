package com.opencredo.examples.akkajava;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static akka.japi.pf.ReceiveBuilder.match;

public class MovementController extends AbstractLoggingActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final ActorRef camera;
    private final Location loc;

    public static Props props(final ActorRef camera) {
        return Props.create(MovementController.class, () -> new MovementController(camera));
    }

    @Override
    public void preStart() throws Exception {
        getTaskStatus();
    }

    private MovementController(final ActorRef camera) {
        this.camera = camera;
        loc = getLocation();
        receive(
                match(Picture.class, pic -> {
                    log().info(" Movement to take picture: {} has succeed, ready to move to next destination", pic);
                })
                        .matchAny(this::unhandled)
                        .build()
        );
        log.info("My wheels are ready to work");
    }

    private void getTaskStatus() {
        log().info("At location:{}, Movement controller requests camera to take a picture", loc);
        camera.tell(loc, self());
    }


    public Location getLocation() {
        List<Integer> locationPath = new ArrayList<>();
        Random rand = new Random();
        int randomInt = rand.nextInt(10);
        for(int i=0;i<randomInt;i++){
            int ri = rand.nextInt(8);
            locationPath.add(ri);
        }
        return new Location(locationPath);
    }
}
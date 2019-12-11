package com.opencredo.examples.akkajava.simple;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.opencredo.examples.akkajava.MovementController;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SimpleBarMain {

    public static void main(String[] args) throws Exception {
        final Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("SimpleBarSystem", config);
        try {
            // Create a Barista
            ActorRef camera = system.actorOf(Camera.props(), "Camera");
//            ActorRef barista = system.actorOf(OldBarista.props(), "Old_Barista");

            // Spawn customers
            system.actorOf(MovementController.props(camera), "wheel0");
            system.actorOf(MovementController.props(camera), "wheel1");
            system.actorOf(MovementController.props(camera), "wheel2");

        } catch (Exception e) {
            system.terminate();
            throw e;
        }
    }
}

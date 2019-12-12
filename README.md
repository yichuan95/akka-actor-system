In this repository, an actor system based on akka is created and tested
-----------------------------------------------------------------------

This Akka system has created three actors:
1. Vehicle robot actor for moving:
    a. Vehicle robot actor is visialized by using the tortoise simulation libary
2. Camera robot actor for taking photo
3. message passing robot actor for sending picture to s3 bucket
---------------------------------------------------------------------

Then entrance of this project is in akka-actor-system/src/main/java/com/opencredo/examples/akkajava/simple/SimpleBarMain.java.
Once the program is on, multiple threads will be created and communicate with each other in an asynchronous way.
The movement of vehicle robot actor will be visiualized in tortoise simulation work pan.

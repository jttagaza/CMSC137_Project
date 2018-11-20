# CMSC137 Project (Pac-Man Reawakened)

* Members
  - Racasa, Marie Loraine
  - Tagaza, Joreyn Angelo
  - Valencia, Rafiel Timothy

* How to Compile and Run
    - Go to CMSC137_Project directory on the terminal
    - Run the following commands: 
        - protoc -I=. --java_out=. ./proto/player.proto
        - protoc -I=. --java_out=. ./proto/tcp_packet.proto 
        - export CLASSPATH=./compiler/protobuf-java-3.6.1.jar:$CLASSPATH
        - javac -d . *.java && java Main
    - Remove .class files
        - rm -r *.class && rm -rf ./packet && rm -r ./src/*.class


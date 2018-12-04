JC = javac
JPATH = -cp
JFLAGS = -d
RFLAGS = -rf

build:
	protoc -I=. --java_out=. ./proto/player.proto
	protoc -I=. --java_out=. ./proto/tcp_packet.proto
	$(JC) $(JPATH) ".:./compiler/protobuf.jar" $(JFLAGS) bin *.java

run:
	java $(JPATH) ".:./compiler/protobuf.jar":".:./bin" Main

clean:
	rm $(RFLAGS) bin && rm $(RFLAGS) src/gamePacket
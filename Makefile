JC = javac
JPATH = -cp
JFLAGS = -d
RFLAGS = -rf

server:
	protoc -I=. --java_out=. ./proto/player.proto
	protoc -I=. --java_out=. ./proto/tcp_packet.proto
	$(JC) $(JPATH) ".:./compiler/protobuf.jar" $(JFLAGS) bin src/gameServer/*.java
	java $(JPATH) ".:./compiler/protobuf.jar":".:./bin" src/gameServer/GameServer

client:
	protoc -I=. --java_out=. ./proto/player.proto
	protoc -I=. --java_out=. ./proto/tcp_packet.proto
	$(JC) $(JPATH) ".:./compiler/protobuf.jar" $(JFLAGS) bin *.java
	java $(JPATH) ".:./compiler/protobuf.jar":".:./bin" Main

clean:
	rm $(RFLAGS) bin && rm $(RFLAGS) src/gamePacket
	clear
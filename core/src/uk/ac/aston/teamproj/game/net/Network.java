package uk.ac.aston.teamproj.game.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import uk.ac.aston.teamproj.game.net.packet.CreateGameSession;
import uk.ac.aston.teamproj.game.net.packet.ErrorPacket;
import uk.ac.aston.teamproj.game.net.packet.JoinGameSession;
import uk.ac.aston.teamproj.game.net.packet.Login;
import uk.ac.aston.teamproj.game.net.packet.Movement;
import uk.ac.aston.teamproj.game.net.packet.PlayerInfo;
import uk.ac.aston.teamproj.game.net.packet.SessionInfo;
import uk.ac.aston.teamproj.game.net.packet.StartGame;
import uk.ac.aston.teamproj.game.net.packet.TerminateSession;

public class Network {

	public static final int TCP_PORT = 54555;
	public static final int UDP_PORT = 54556;
	
	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Login.class);
		kryo.register(CreateGameSession.class);
		kryo.register(JoinGameSession.class);
		kryo.register(Movement.class);
		kryo.register(SessionInfo.class);
		kryo.register(java.util.ArrayList.class);
		kryo.register(StartGame.class);
		kryo.register(PlayerInfo.class);
		kryo.register(ErrorPacket.class);
		kryo.register(TerminateSession.class);
	}
	
}

package src.gameServer;

import src.gameChat.User;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameState {
	private Map players = new HashMap();
	
	public void update(String name, User user){
		this.players.put(name,user);
	}
	
	public String toString(){
		String retval = "";
		for(Iterator ite = players.keySet().iterator();ite.hasNext();){
			String name = (String) ite.next();
			User player = (User) players.get(name);
			retval += "PLAYER " + player.getName() + " " + player.getX() + " " + player.getY() + " " + player.getScore() + ":";
		}
		return retval;
	}
	
	public Map getPlayers(){
		return this.players;
	}

	public User getMax(){
		User topnotch = null;
		int max = 0;
		for(Iterator ite = players.keySet().iterator();ite.hasNext();){
			String name = (String) ite.next();
			User player = (User) players.get(name);
			int score = player.getScore();
			if(score > max){
				max = score;
				topnotch = player;
			}
		}
		return topnotch;
	}
}

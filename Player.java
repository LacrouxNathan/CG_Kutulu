import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Survive the wrath of Kutulu
 * Coded fearlessly by JohnnyYuge & nmahoude (ok we might have been a bit scared by the old god...but don't say anything)
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        String map[] = new String[height];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < height; i++) {
            String line = in.nextLine();
			map[i] = line;
        }
        int sanityLossLonely = in.nextInt(); // how much sanity you lose every turn when alone, always 3 until wood 1
        int sanityLossGroup = in.nextInt(); // how much sanity you lose every turn when near another player, always 1 until wood 1
        int wandererSpawnTime = in.nextInt(); // how many turns the wanderer take to spawn, always 3 until wood 1
        int wandererLifeTime = in.nextInt(); // how many turns the wanderer is on map after spawning, always 40 until wood 1
        
        List<Entity> entityList = new ArrayList<>();
        
        // game loop
        while (true) {
            entityList.clear();
        	int entityCount = in.nextInt(); // the first given entity corresponds to your explorer
            for (int i = 0; i < entityCount; i++) {
                String entityType = in.next();
                int id = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                int param0 = in.nextInt();
                int param1 = in.nextInt();
                int param2 = in.nextInt();
                Entity e = new Entity(id, x, y, param0, param1, param2, entityType);
                entityList.add(e);
            }
            System.err.println("Nb entity :" + entityList.size());
            Entity myPlayer = entityList.get(0);
            Entity friend = null;
            for (Entity e : entityList) {
            	if (e.entityType.equals("EXPLORER")) friend = e;
            }
            int run = -1;
            		
            for (int i = -2; i <= 2; i++ ) {
            	for (int j = -2; i<= 2; i++) {
            		for (Entity e : entityList) {
            			if (e.x == myPlayer.x + i && e.y == myPlayer.y + j && e.id != myPlayer.id && e.entityType.equals("WANDERER")) {
            				run = e.id;
            				System.err.println("Entité détecter ! son id : " + run);
            			}
            		}
            	}
            }
            
            //Traitement de l'info
            if (run != -1) {
            	Entity enemie = null;
            	for (Entity e : entityList) {
            		if (e.id == run) {
            			enemie = e;
            		}
            	}
                int directionY = 0;// Up = -1, Down = 1;
                int directionX = 0;// Left = -1, Right = 1;
        		
                if (enemie.y >= myPlayer.y) directionY = -1;
        		else directionY = 1;
        		
        		if (enemie.x >= myPlayer.x) directionX = -1;
        		else directionX = 1;
        		
            	if (map[myPlayer.y+directionY].charAt(myPlayer.x) == 'W' || map[myPlayer.y+directionY].charAt(myPlayer.x) == '#' ) {
            		if (map[myPlayer.y+directionY].charAt(myPlayer.x + directionX) == 'W' || map[myPlayer.y+directionY].charAt(myPlayer.x + directionX) == '#' ) {
            			System.out.println("WAIT");
            		}
            		else {
            			int dirx = myPlayer.x + directionX;
            			int diry = myPlayer.y + directionY;
            			System.out.println("MOVE " + dirx + " " + diry);
            		}
            	}
            	else {
            		int diry = myPlayer.y + directionY;
            		System.out.println("MOVE " + myPlayer.x + " " + diry);
            	}
            }
            else {
            	System.out.println("MOVE " + friend.x + " " + friend.y);
            }

             // MOVE <x> <y> | WAIT
        }
    }
}
class Entity {
	
	int id,x,y;
	int param[] = new int[3];
	String entityType;
	
	public Entity(int id, int x, int y, int param, int param1, int param2, String entityType) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.param[0] = param;
		this.param[1] = param1;
		this.param[2] = param2;
		this.entityType = entityType;
	}
	
	
}
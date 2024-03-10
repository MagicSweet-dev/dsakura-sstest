package com.magicsweet.sstest.dungeon;

import java.util.ArrayList;
import java.util.Random;

public class DungeonGenerator {

	public static Dungeon generateCompatibleDungeon(Dungeon dungeon) {
		var blocks = new Dungeon.Block[4][7];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				blocks[i][j] = Dungeon.Block.GROUND;
			}
		}

		var entrances = new ArrayList<>(dungeon.getExits());
		var exits = new ArrayList<>(dungeon.getEntrances());
		var random = new Random();
		var entranceX = entrances.get(random.nextInt(entrances.size()));
		var exitX = exits.get(random.nextInt(exits.size()));

		blocks[entranceX][0] = Dungeon.Block.AIR;
		blocks[exitX][6] = Dungeon.Block.AIR;

		// generating entrances & exits
		for (int i = 0; i < random.nextInt(3); i++) {
			blocks[random.nextInt(4)][6] = Dungeon.Block.AIR;
		}

		for (int i = 0; i < random.nextInt(3); i++) {
			blocks[random.nextInt(4)][0] = Dungeon.Block.AIR;
		}

		// generating random holes (min 5)
		for (int i = 0; i < random.nextInt(5, 4 * 7); i++) {
			blocks[random.nextInt(4)][random.nextInt(7)] = Dungeon.Block.AIR;
		}

		// Random Walk Path
		int currentX = entranceX;
		int currentY = 0;

		while (currentY < 6) {
			currentY++;
			blocks[currentX][currentY] = Dungeon.Block.AIR;

			// Randomly move left or right if possible
			if (random.nextBoolean()) {
				if (currentX > 0) currentX--;
			} else {
				if (currentX < 3) currentX++;
			}
		}


		return new Dungeon(blocks);
	}

}

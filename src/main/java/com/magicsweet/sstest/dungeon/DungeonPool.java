package com.magicsweet.sstest.dungeon;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.magicsweet.sstest.dungeon.Dungeon.Block;

/**
 * Represents a pool of dungeons that can be used to create sequences of compatible dungeons.
 */
public class DungeonPool {
	private final List<Dungeon> dungeons;

	/**
	 * Creates a new DungeonPool with the specified list of dungeons.
	 * Only open dungeons are added to the pool. An IllegalArgumentException is thrown
	 * if there are not enough compatible dungeons to generate sequences.
	 *
	 * @param dungeons the list of dungeons to be added to the pool
	 * @throws IllegalArgumentException if there are insufficient compatible dungeons
	 */
	public DungeonPool(List<Dungeon> dungeons) {
		this.dungeons = new ArrayList<>();
		for (var dungeon : dungeons) {
			if (dungeon.isOpen()) {
				this.dungeons.add(dungeon);
			}
		}
		if (!hasCompatibleDungeons()) {
			throw new IllegalArgumentException("Insufficient dungeons for generating sequences.");
		}
	}

	/**
	 * Creates a sequence of new compatible dungeons with the specified length.
	 * The return sequence will extend the current pool.
	 *
	 * @param length the length of the sequence to create
	 * @return a list with newly created compatible dungeons
	 */
	public List<Dungeon> createXSequence(int length) {
		List<Dungeon> dungeons = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			dungeons.add(DungeonGenerator.generateCompatibleDungeon(this.dungeons.get(this.dungeons.size() - 1)));
			this.dungeons.add(dungeons.get(dungeons.size() - 1));
		}
		return dungeons;
	}

	/**
	 * Checks if there are any compatible dungeons in the pool.
	 *
	 * @return true if there are compatible dungeons, false otherwise
	 */
	private boolean hasCompatibleDungeons() {
		for (int i = 0; i < dungeons.size(); i++) {
			for (int j = 0; j < dungeons.size(); j++) {
				if (i != j && dungeons.get(i).isCompatibleWith(dungeons.get(j))) {
					return true;
				}
			}
		}
		return false;
	}
}

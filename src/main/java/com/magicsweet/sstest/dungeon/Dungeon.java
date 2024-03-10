package com.magicsweet.sstest.dungeon;

import java.util.*;

/**
 * Represents a 2D dungeon with blocks that can be either ground or air.
 */
public class Dungeon {
	private final Block[][] area;

	/**
	 * Constructs a new Dungeon with the specified area.
	 *
	 * @param area the 2D array representing the dungeon's layout
	 */
	public Dungeon(Block[][] area) {
		this.area = area;
	}

	/**
	 * Represents the types of blocks in the dungeon.
	 */
	public enum Block {
		GROUND,
		AIR
	}

	/**
	 * Returns the set of entrance indices (Y-coordinates) where the block at X=0 is air.
	 *
	 * @return a set of entrance indices
	 */
	public Set<Integer> getEntrances() {
		return getOpenings(0);
	}

	/**
	 * Returns the set of exit indices (Y-coordinates) where the block at X=6 is air.
	 *
	 * @return a set of exit indices
	 */
	public Set<Integer> getExits() {
		return getOpenings(6);
	}

	/**
	 * Returns the set of indices (Y-coordinates) where the block at the specified X-coordinate is air.
	 *
	 * @param x the X-coordinate to check for openings
	 * @return a set of indices where the block is air
	 */
	private Set<Integer> getOpenings(int x) {
		Set<Integer> openings = new HashSet<>();
		for (int y = 0; y < area.length; y++) {
			if (area[y][x] == Block.AIR) {
				openings.add(y);
			}
		}
		return openings;
	}

	/**
	 * Returns true if the dungeon has at least one entrance and one exit.
	 *
	 * @return true if the dungeon is open, false otherwise
	 */
	public boolean isOpen() {
		return !getEntrances().isEmpty() && !getExits().isEmpty();
	}

	/**
	 * Returns true if this dungeon is compatible with another dungeon, i.e.,
	 * if at least one of this dungeon's exits matches one of the other dungeon's entrances.
	 *
	 * @param other the other dungeon to check compatibility with
	 * @return true if the dungeons are compatible, false otherwise
	 */
	public boolean isCompatibleWith(Dungeon other) {
		Set<Integer> exits = getExits();
		Set<Integer> otherEntrances = other.getEntrances();
		for (var exit : exits) {
			if (otherEntrances.contains(exit)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dungeon dungeon = (Dungeon) o;
		return Arrays.deepEquals(area, dungeon.area);
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(area);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dungeon {\n");
		for (var row : area) {
			for (var block : row) {
				sb.append(block == Block.GROUND ? "X" : " ");
				sb.append(" ");
			}
			sb.append("\n");
		}
		sb.append("}");
		return sb.toString();
	}

}

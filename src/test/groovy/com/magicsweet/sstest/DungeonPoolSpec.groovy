package com.magicsweet.sstest

import com.magicsweet.sstest.dungeon.Dungeon
import com.magicsweet.sstest.dungeon.DungeonPool
import spock.lang.Specification
import spock.lang.Specification

class DungeonPoolSpec extends Specification {
	def "DungeonPool should filter out closed dungeons"() {
		given:
		List<Dungeon> dungeons = [
			new Dungeon([
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
				[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND],
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR]
			] as Dungeon.Block[][]),
			new Dungeon([
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR],
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND]
			] as Dungeon.Block[][]),
			new Dungeon([
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND]
			] as Dungeon.Block[][])
		]
		
		when:
		DungeonPool pool = new DungeonPool(dungeons)
		
		then:
		pool.dungeons.size() == 2
	}
	
	def "DungeonPool should throw IllegalArgumentException if there are no compatible dungeons"() {
		given:
		List<Dungeon> dungeons = [
			new Dungeon([
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
				[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND],
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR]
			] as Dungeon.Block[][])
		]
		
		when:
		DungeonPool pool = new DungeonPool(dungeons)
		
		then:
		thrown(IllegalArgumentException)
	}
	
	def "createXSequence should return a valid sequence of dungeons"() {
		given:
		List<Dungeon> dungeons = [
			new Dungeon([
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
				[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND],
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR]
			] as Dungeon.Block[][]),
			new Dungeon([
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR],
				[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND],
				[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND]
			] as Dungeon.Block[][])
		]
		DungeonPool pool = new DungeonPool(dungeons)
		
		when:
		List<Dungeon> sequence = pool.createXSequence(5)
		
		sequence.each { println it }
		
		then:
		sequence.size() == 5
		sequence[0] != sequence[1]
		sequence[0].isCompatibleWith(sequence[1])
		sequence[1].isCompatibleWith(sequence[2])
		sequence[2].isCompatibleWith(sequence[3])
	}
}

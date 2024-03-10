package com.magicsweet.sstest

import com.magicsweet.sstest.dungeon.Dungeon
import spock.lang.Specification

class DungeonSpec extends Specification {
	def "getEntrances should return the correct set of entrances"() {
		given:
		Dungeon.Block[][] area = [
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
			[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR]
		]
		Dungeon dungeon = new Dungeon(area)
		
		expect:
		dungeon.getEntrances() == [0, 2, 3] as Set
	}
	
	def "getExits should return the correct set of exits"() {
		given:
		Dungeon.Block[][] area = [
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
			[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR]
		]
		Dungeon dungeon = new Dungeon(area)
		
		expect:
		dungeon.getExits() == [1, 3] as Set
	}
	
	def "isOpen should return true if the dungeon has at least one entrance and one exit"() {
		given:
		Dungeon.Block[][] area = [
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
			[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.AIR]
		]
		Dungeon dungeon = new Dungeon(area)
		
		expect:
		dungeon.isOpen()
	}
	
	def "isCompatibleWith should return true if two dungeons are compatible"() {
		given:
		Dungeon.Block[][] area1 = [
			[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND],
			[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR],
			[Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR],
			[Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND]
		]
		Dungeon dungeon1 = new Dungeon(area1)
		
		Dungeon.Block[][] area2 = [
			[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR],
			[Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR, Dungeon.Block.AIR],
			[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.AIR, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND],
			[Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND, Dungeon.Block.GROUND]
		]
		Dungeon dungeon2 = new Dungeon(area2)
		
		expect:
		dungeon1.isCompatibleWith(dungeon2)
	}
}



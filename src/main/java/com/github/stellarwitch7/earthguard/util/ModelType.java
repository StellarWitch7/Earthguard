package com.github.stellarwitch7.earthguard.util;

public class ModelType {
	private final String id;
	private final String path;
	
	public ModelType(String id, String path) {
		this.id = id;
		this.path = path;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPath() {
		return path;
	}
	
	public static final ModelType ENTITY = new ModelType("entity", "entity/");
	public static final ModelType BOSS = new ModelType("boss", "entity/boss/");
	public static final ModelType BLOCK = new ModelType("block", "block/");
	public static final ModelType ITEM = new ModelType("item", "item/");
	public static final ModelType ARMOUR = new ModelType("armour", "armour/");
	public static final ModelType PROJECTILE = new ModelType("projectile", "entity/projectile/");
}

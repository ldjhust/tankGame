package com.ldj.entity;

/**
 * 此类用于存放游戏中所使用到的常量
 * 此类设计为public、abstract
 * @author 冬杰
 *
 */
public abstract class GameConst {
	// 坦克相关常量
	public static enum DIRECTION {up, down, left, right}; // 定义坦克的方向，用枚举常量
	public static final int TANK_SPEED = 5; // 定义坦克的速度
	public static final int TANK_WEEL_LENGTH = 30; // 定义坦克轮子的长度
	public static final int TANK_WELL_WIDTH = 10;  // 定义坦克轮子的宽度
	public static final int TANK_SHOOT_LENGTH = 15; // 定义坦克炮筒的长度
	public static final int TANK_MAX_BOMB_NUMBER = 5; // 坦克可以发射的最大炮弹数
	
	// 炮弹相关常量
	public static final int BOMB_SPEED = 10; // 定义炮弹的速度
	
	// 游戏面板相关常量
	public static final int GAME_AREA_WIDTH = 640; // 游戏区域的宽度
	public static final int GAME_AREA_HEIGHT = 480; // 游戏区域的高度
}

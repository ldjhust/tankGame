package com.ldj.entity;

/**
 * �������ڴ����Ϸ����ʹ�õ��ĳ���
 * �������Ϊpublic��abstract
 * @author ����
 *
 */
public abstract class GameConst {
	public static enum DIRECTION {up, down, left, right}; // ����̹�˵ķ�����ö�ٳ���
	public static final int TANK_SPEED = 5; // ����̹�˵��ٶ�
	public static final int TANK_WEEL_LENGTH = 30; // ����̹�����ӵĳ���
	public static final int TANK_WELL_WIDTH = 10;  // ����̹�����ӵĿ��
	public static final int TANK_SHOOT_LENGTH = 15; // ����̹����Ͳ�ĳ���
	public static final int GAME_AREA_WIDTH = 640; // ��Ϸ����Ŀ��
	public static final int GAME_AREA_HEIGHT = 480; // ��Ϸ����ĸ߶�
}

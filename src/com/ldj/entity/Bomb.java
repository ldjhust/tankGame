package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 坦克的炮弹类
 * @author 冬杰
 *
 */
public class Bomb {
	private Point bombCenter = null; // 坦克的中心坐标
	private final int radius = 2; // 设计炮弹为圆形，炮弹半径为2
	
	public Bomb(Point bombCenter) {
		this.bombCenter = bombCenter;
	}
	
	/**
	 * 在游戏面板上画出炮弹
	 */
	public void draw(Graphics g) {
		// 设置炮弹的颜色
		g.setColor(Color.CYAN);
		// 画出一个圆形表示炮弹
		g.fillArc(this.bombCenter.getX() - this.radius, this.bombCenter.getY() - this.radius, this.radius << 1, this.radius << 1, 0, 360);
	}
}

package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.ldj.entity.GameConst.DIRECTION;

/**
 * 坦克的炮弹类
 * @author 冬杰
 *
 */
public class Bomb {
	private Point bombCenter = null; // 坦克的中心坐标
	private final int radius = 2; // 设计炮弹为圆形，炮弹半径为2
	private DIRECTION direction = null; // 炮弹出生时的方向
	private boolean isAlive = false;
	private boolean isHeroBomb = false; // 标志这颗炮弹是敌方还是英雄方发射的
	
	public Bomb(Point bombCenter, DIRECTION direction, boolean isHeroBomb) {
		this.bombCenter = bombCenter;
		this.direction = direction;
		this.isAlive = true;
		this.isHeroBomb = isHeroBomb;
		
		// 炮弹一出生就应该开始运动
		new BombMoveDriver().start();
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
	
	/**
	 * 处理炮弹的运动
	 */
	public void move() {

		switch (this.direction) {
			case up: {
				// 设置炮弹运动后的坐标
				this.bombCenter.setY(this.bombCenter.getY() - GameConst.BOMB_SPEED);
				// 判断炮弹运动后是否还在游戏区域内，若不在，则判断炮弹死亡
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// 炮弹跑出去了，设定炮弹死亡
					this.isAlive = false;
				}
				break;
			}
			case down: {
				// 设置炮弹运动后的坐标
				this.bombCenter.setY(this.bombCenter.getY() + GameConst.BOMB_SPEED);
				// 判断炮弹运动后是否还在游戏区域内，若不在，则判断炮弹死亡
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// 炮弹跑出去了，设定炮弹死亡
					this.isAlive = false;
				}
				break;
			}
			case left: {
				// 设置炮弹运动后的坐标
				this.bombCenter.setX(this.bombCenter.getX() - GameConst.BOMB_SPEED);
				// 判断炮弹运动后是否还在游戏区域内，若不在，则判断炮弹死亡
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// 炮弹跑出去了，设定炮弹死亡
					this.isAlive = false;
				}
				break;
			}
			case right: {
				// 设置炮弹运动后的坐标
				this.bombCenter.setX(this.bombCenter.getX() + GameConst.BOMB_SPEED);
				// 判断炮弹运动后是否还在游戏区域内，若不在，则判断炮弹死亡
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// 炮弹跑出去了，设定炮弹死亡
					this.isAlive = false;
				}
				break;
			}
			default: {
				break;
			}
		}
	}
	
	/**
	 * 判断炮弹是否还在游戏区域内
	 * @param x 炮弹的X坐标
	 * @param y 炮弹的Y坐标
	 * @return true ，表示炮弹已不在游戏区域内；false，表示炮弹还在游戏区域
	 */
	private boolean isOutOfBound(int x, int y) {
		if (x < 0 || x > GameConst.GAME_AREA_WIDTH) {
			return true;
		}
		
		if (y < 0 || y > GameConst.GAME_AREA_HEIGHT) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 见一个内部内线程驱动炮弹运动
	 * @author 冬杰
	 *
	 */
	private class BombMoveDriver extends Thread {
		public void run() {
			// 运动到炮弹消失为止
			while (Bomb.this.isAlive) {
				Bomb.this.move();
				
				try {
					// 每隔一百毫秒驱动炮弹运动一次
					this.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.toString());
				}
			}
		}
	}

	
	
	public boolean isAlive() {
		return isAlive;
	}

	public boolean isHeroBomb() {
		return isHeroBomb;
	}
}

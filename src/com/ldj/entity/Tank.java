package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.ldj.entity.GameConst.DIRECTION;

/**
 * 坦克的祖先类，所有的坦克包括我方坦克和地方坦克都需要从他继承
 * @author 冬杰
 *
 */
public class Tank {
	private Point tankCenter = null; // 坦克的中心坐标
	private DIRECTION direction = null; // 坦克的方向
	private Bomb bomb = null; // 坦克的炮弹
	
	public Tank(Point tankCenter) {
		this.tankCenter = tankCenter;
		this.direction = DIRECTION.right;
	}
	
	/**
	 * 画出坦克
	 * @param g 画坦克用的画笔
	 */
	public void draw(Graphics g) {
		// 设置坦克的颜色
		g.setColor(Color.CYAN);
		
		// 画出坦克的炮盖
		g.drawArc(this.tankCenter.getX() - 10, this.tankCenter.getY() - 10, 20, 20, 0, 360);
		
		// 剩下的坦克组件都与坦克的方向相关
		switch (this.direction) {
			case up: 
			case down: {
					// 画出炮筒
					// 坦克上下方向只有坦克的炮筒方向需要改变
					if (this.direction == DIRECTION.up) {
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() - GameConst.TANK_SHOOT_LENGTH);
					} else {
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() + GameConst.TANK_SHOOT_LENGTH);
					}
					
					// 画出轮子
					g.drawRect(this.tankCenter.getX() - 20, this.tankCenter.getY() - 15, 10, 30);
					g.drawRect(this.tankCenter.getX() + 10, this.tankCenter.getY() - 15, 10, 30);
					
					break;
			}
			case left:
			case right: {
				// 画出炮筒
				// 坦克在左右方向只有坦克的炮筒方向需要改变
				if (this.direction == DIRECTION.left) {
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() - GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY());
				} else {
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() + GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY());
				}
				
				// 画出轮子
				g.drawRect(this.tankCenter.getX() - 15, this.tankCenter.getY() - 20, 30, 10);
				g.drawRect(this.tankCenter.getX() - 15, this.tankCenter.getY() + 10, 30, 10);
				
				break;
			}
			default: {
				System.exit(1); // 坦克方向有错误，推出程序
				break;
			}
		}
	}

	/**
	 * 处理坦克的移动
	 * @param direction 给坦克指定的移动方向
	 */
	public void move(DIRECTION direction) {
		if (this.direction != direction) {
			// 如果指定坦克运动的方向与坦克本身的方向不一致，那么本次就只改变坦克的方向，
			// 并不改变坦克的坐标
			this.direction = direction;
			
			return;
		}
		
		switch (direction) {
			case up: {
				int deltaY = this.tankCenter.getY() - GameConst.TANK_SPEED;
				if ((deltaY - (GameConst.TANK_WELL_WIDTH >> 1)) < (GameConst.TANK_WEEL_LENGTH >> 1)) {
					// 空出坦克轮子宽度的一半，不然当坦克顶着墙转向时轮子会跑出一半到游戏区域外
					// 如果坦克走了这一步跑出游戏区域外了，这一步就不走
					return;
				}
				
				this.tankCenter.setY(deltaY);
				break;
			}
			case down: {
				int deltaY = this.tankCenter.getY() + GameConst.TANK_SPEED;
				if (((deltaY + (GameConst.TANK_WELL_WIDTH >> 1)) + (GameConst.TANK_WEEL_LENGTH >> 1)) > GameConst.GAME_AREA_HEIGHT) {
					// 空出坦克轮子宽度的一半，不然当坦克顶着墙转向时轮子会跑出一半到游戏区域外
					// 如果坦克走了这一步跑出游戏区域外了，这一步就不走
					return;
				}
				
				this.tankCenter.setY(deltaY);
				break;
			}
			case left: {
				int deltaX = this.tankCenter.getX() - GameConst.TANK_SPEED;
				if ((deltaX - (GameConst.TANK_WELL_WIDTH >> 1)) < (GameConst.TANK_WEEL_LENGTH >> 1)) {
					// 空出坦克轮子宽度的一半，不然当坦克顶着墙转向时轮子会跑出一半到游戏区域外
					// 如果坦克走了这一步跑出游戏区域外了，这一步就不走
					return;
				}
				
				this.tankCenter.setX(deltaX);
				break;
			}
			case right: {
				int deltaX = this.tankCenter.getX() + GameConst.TANK_SPEED;
				if (((deltaX + (GameConst.TANK_WELL_WIDTH >> 1)) + (GameConst.TANK_WEEL_LENGTH >> 1)) > GameConst.GAME_AREA_WIDTH) {
					// 空出坦克轮子宽度的一半，不然当坦克顶着墙转向时轮子会跑出一半到游戏区域外
					// 如果坦克走了这一步跑出游戏区域外了，这一步就不走
					return;
				}
				
				this.tankCenter.setX(deltaX);
				break;
			}
			default: {
				break;
			}
		}
	}
	
	/**
	 * 处理坦克的射击
	 */
	public Bomb shoot() {
		// 在坦克的炮口生成一颗炮弹
		switch (this.direction) {
			case up: {
				this.bomb = new Bomb(new Point(this.tankCenter.getX(), this.tankCenter.getY() - GameConst.TANK_SHOOT_LENGTH), this.direction);
				break;
			}
			case down: {
				this.bomb = new Bomb(new Point(this.tankCenter.getX(), this.tankCenter.getY() + GameConst.TANK_SHOOT_LENGTH), this.direction);
				break;
			}
			case left: {
				this.bomb = new Bomb(new Point(this.tankCenter.getX() - GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY()), this.direction);
				break;
			}
			case right: {
				this.bomb = new Bomb(new Point(this.tankCenter.getX() + GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY()), this.direction);
				break;
			}
			default: {
				break;
			}
		}
		
		return this.bomb;  // 返回生成的导弹，这颗导弹一旦生成就是面板上一个独立的个体，不再受坦克控制
	}
	
	public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
}

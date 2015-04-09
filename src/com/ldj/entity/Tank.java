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
	private DIRECTION direction = null;
	
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
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() - 15);
					} else {
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() + 15);
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
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() - 15, this.tankCenter.getY());
				} else {
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() + 15, this.tankCenter.getY());
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
		
	}
	
	public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
}

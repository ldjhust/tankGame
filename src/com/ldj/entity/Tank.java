package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.ldj.entity.GameConst.DIRECTION;

/**
 * ̹�˵������࣬���е�̹�˰����ҷ�̹�˺͵ط�̹�˶���Ҫ�����̳�
 * @author ����
 *
 */
public class Tank {
	private Point tankCenter = null; // ̹�˵���������
	private DIRECTION direction = null;
	
	public Tank(Point tankCenter) {
		this.tankCenter = tankCenter;
		this.direction = DIRECTION.right;
	}
	
	/**
	 * ����̹��
	 * @param g ��̹���õĻ���
	 */
	public void draw(Graphics g) {
		// ����̹�˵���ɫ
		g.setColor(Color.CYAN);
		
		// ����̹�˵��ڸ�
		g.drawArc(this.tankCenter.getX() - 10, this.tankCenter.getY() - 10, 20, 20, 0, 360);
		
		// ʣ�µ�̹���������̹�˵ķ������
		switch (this.direction) {
			case up: 
			case down: {
					// ������Ͳ
					// ̹�����·���ֻ��̹�˵���Ͳ������Ҫ�ı�
					if (this.direction == DIRECTION.up) {
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() - 15);
					} else {
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() + 15);
					}
					
					// ��������
					g.drawRect(this.tankCenter.getX() - 20, this.tankCenter.getY() - 15, 10, 30);
					g.drawRect(this.tankCenter.getX() + 10, this.tankCenter.getY() - 15, 10, 30);
					
					break;
			}
			case left:
			case right: {
				// ������Ͳ
				// ̹�������ҷ���ֻ��̹�˵���Ͳ������Ҫ�ı�
				if (this.direction == DIRECTION.left) {
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() - 15, this.tankCenter.getY());
				} else {
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() + 15, this.tankCenter.getY());
				}
				
				// ��������
				g.drawRect(this.tankCenter.getX() - 15, this.tankCenter.getY() - 20, 30, 10);
				g.drawRect(this.tankCenter.getX() - 15, this.tankCenter.getY() + 10, 30, 10);
				
				break;
			}
			default: {
				System.exit(1); // ̹�˷����д����Ƴ�����
				break;
			}
		}
	}

	/**
	 * ����̹�˵��ƶ�
	 * @param direction ��̹��ָ�����ƶ�����
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

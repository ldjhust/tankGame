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
	private DIRECTION direction = null; // ̹�˵ķ���
	private Bomb bomb = null; // ̹�˵��ڵ�
	
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
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() - GameConst.TANK_SHOOT_LENGTH);
					} else {
						g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX(), this.tankCenter.getY() + GameConst.TANK_SHOOT_LENGTH);
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
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() - GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY());
				} else {
					g.drawLine(this.tankCenter.getX(), this.tankCenter.getY(), this.tankCenter.getX() + GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY());
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
		if (this.direction != direction) {
			// ���ָ��̹���˶��ķ�����̹�˱���ķ���һ�£���ô���ξ�ֻ�ı�̹�˵ķ���
			// �����ı�̹�˵�����
			this.direction = direction;
			
			return;
		}
		
		switch (direction) {
			case up: {
				int deltaY = this.tankCenter.getY() - GameConst.TANK_SPEED;
				if ((deltaY - (GameConst.TANK_WELL_WIDTH >> 1)) < (GameConst.TANK_WEEL_LENGTH >> 1)) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
					return;
				}
				
				this.tankCenter.setY(deltaY);
				break;
			}
			case down: {
				int deltaY = this.tankCenter.getY() + GameConst.TANK_SPEED;
				if (((deltaY + (GameConst.TANK_WELL_WIDTH >> 1)) + (GameConst.TANK_WEEL_LENGTH >> 1)) > GameConst.GAME_AREA_HEIGHT) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
					return;
				}
				
				this.tankCenter.setY(deltaY);
				break;
			}
			case left: {
				int deltaX = this.tankCenter.getX() - GameConst.TANK_SPEED;
				if ((deltaX - (GameConst.TANK_WELL_WIDTH >> 1)) < (GameConst.TANK_WEEL_LENGTH >> 1)) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
					return;
				}
				
				this.tankCenter.setX(deltaX);
				break;
			}
			case right: {
				int deltaX = this.tankCenter.getX() + GameConst.TANK_SPEED;
				if (((deltaX + (GameConst.TANK_WELL_WIDTH >> 1)) + (GameConst.TANK_WEEL_LENGTH >> 1)) > GameConst.GAME_AREA_WIDTH) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
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
	 * ����̹�˵����
	 */
	public Bomb shoot() {
		// ��̹�˵��ڿ�����һ���ڵ�
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
		
		return this.bomb;  // �������ɵĵ�������ŵ���һ�����ɾ��������һ�������ĸ��壬������̹�˿���
	}
	
	public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
}

package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import com.ldj.entity.GameConst.DIRECTION;

/**
 * ̹�˵������࣬���е�̹�˰����ҷ�̹�˺͵ط�̹�˶���Ҫ�����̳�
 * @author ����
 *
 */
public class Tank {
	private Point tankCenter = null; // ̹�˵���������
	private DIRECTION direction = null; // ̹�˵ķ���
	private boolean isAlived = false; // ������boolean��ʾ������������һ��������ʾ����������ֵ
	
	public Tank(Point tankCenter) {
		this.tankCenter = tankCenter;
		this.direction = DIRECTION.right;
		this.isAlived = true;
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
				if (isOutofBound(this.tankCenter.getX(), deltaY)) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
					return;
				}
				
				this.tankCenter.setY(deltaY);
				break;
			}
			case down: {
				int deltaY = this.tankCenter.getY() + GameConst.TANK_SPEED;
				if (isOutofBound(this.tankCenter.getX(), deltaY)) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
					return;
				}
				
				this.tankCenter.setY(deltaY);
				break;
			}
			case left: {
				int deltaX = this.tankCenter.getX() - GameConst.TANK_SPEED;
				if (isOutofBound(deltaX, this.tankCenter.getY())) {
					// �ճ�̹�����ӿ�ȵ�һ�룬��Ȼ��̹�˶���ǽת��ʱ���ӻ��ܳ�һ�뵽��Ϸ������
					// ���̹��������һ���ܳ���Ϸ�������ˣ���һ���Ͳ���
					return;
				}
				
				this.tankCenter.setX(deltaX);
				break;
			}
			case right: {
				int deltaX = this.tankCenter.getX() + GameConst.TANK_SPEED;
				if (this.isOutofBound(deltaX, this.tankCenter.getY())) {
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
		Bomb bomb = null;
		// ��̹�˵��ڿ�����һ���ڵ�
		switch (this.direction) {
			case up: {
				bomb = new Bomb(new Point(this.tankCenter.getX(), this.tankCenter.getY() - GameConst.TANK_SHOOT_LENGTH), this.direction, false);
				break;
			}
			case down: {
				bomb = new Bomb(new Point(this.tankCenter.getX(), this.tankCenter.getY() + GameConst.TANK_SHOOT_LENGTH), this.direction, false);
				break;
			}
			case left: {
				bomb = new Bomb(new Point(this.tankCenter.getX() - GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY()), this.direction, false);
				break;
			}
			case right: {
				bomb = new Bomb(new Point(this.tankCenter.getX() + GameConst.TANK_SHOOT_LENGTH, this.tankCenter.getY()), this.direction, false);
				break;
			}
			default: {
				break;
			}
		}

		// ���ŷ����ڵ�������
		new PlaySound("sound/shoot.wav").start();
		
		return bomb;  // �������ɵĵ�������ŵ���һ�����ɾ��������һ�������ĸ��壬������̹�˿���
	}
	
	public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
	
	public boolean isOutofBound(int x, int y) {
		switch (this.direction) {
			case up: {
				if (y - (GameConst.TANK_WEEL_LENGTH >> 1) < 5) {
					return true;
				}
				
				break;
			}
			case down: {
				if (y + (GameConst.TANK_WEEL_LENGTH >> 1) > GameConst.GAME_AREA_HEIGHT - 5) {
					return true;
				}
				
				break;
			}
			case left: {
				if (x - (GameConst.TANK_WEEL_LENGTH >> 1) < 5) {
					return true;
				}
				
				break;
			}
			case right: {
				if (x + (GameConst.TANK_WEEL_LENGTH >> 1) > GameConst.GAME_AREA_WIDTH - 5) {
					return true;
				}
				
				break;
			}
			default: {
				break;
			}
		}
		
		return false;
	}
	
	public boolean isAlived() {
		return isAlived;
	}

	public void setAlived(boolean isAlived) {
		this.isAlived = isAlived;
	}
	
	public Point getTankCenter() {
		return this.tankCenter;
	}
}

package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.ldj.entity.GameConst.DIRECTION;

/**
 * ̹�˵��ڵ���
 * @author ����
 *
 */
public class Bomb {
	private Point bombCenter = null; // ̹�˵���������
	private final int radius = 2; // ����ڵ�ΪԲ�Σ��ڵ��뾶Ϊ2
	private DIRECTION direction = null; // �ڵ�����ʱ�ķ���
	private boolean isAlive = false;
	private boolean isHeroBomb = false; // ��־����ڵ��ǵз�����Ӣ�۷������
	
	public Bomb(Point bombCenter, DIRECTION direction, boolean isHeroBomb) {
		this.bombCenter = bombCenter;
		this.direction = direction;
		this.isAlive = true;
		this.isHeroBomb = isHeroBomb;
		
		// �ڵ�һ������Ӧ�ÿ�ʼ�˶�
		new BombMoveDriver().start();
	}
	
	/**
	 * ����Ϸ����ϻ����ڵ�
	 */
	public void draw(Graphics g) {
		// �����ڵ�����ɫ
		g.setColor(Color.CYAN);
		// ����һ��Բ�α�ʾ�ڵ�
		g.fillArc(this.bombCenter.getX() - this.radius, this.bombCenter.getY() - this.radius, this.radius << 1, this.radius << 1, 0, 360);
	}
	
	/**
	 * �����ڵ����˶�
	 */
	public void move() {

		switch (this.direction) {
			case up: {
				// �����ڵ��˶��������
				this.bombCenter.setY(this.bombCenter.getY() - GameConst.BOMB_SPEED);
				// �ж��ڵ��˶����Ƿ�����Ϸ�����ڣ������ڣ����ж��ڵ�����
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// �ڵ��ܳ�ȥ�ˣ��趨�ڵ�����
					this.isAlive = false;
				}
				break;
			}
			case down: {
				// �����ڵ��˶��������
				this.bombCenter.setY(this.bombCenter.getY() + GameConst.BOMB_SPEED);
				// �ж��ڵ��˶����Ƿ�����Ϸ�����ڣ������ڣ����ж��ڵ�����
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// �ڵ��ܳ�ȥ�ˣ��趨�ڵ�����
					this.isAlive = false;
				}
				break;
			}
			case left: {
				// �����ڵ��˶��������
				this.bombCenter.setX(this.bombCenter.getX() - GameConst.BOMB_SPEED);
				// �ж��ڵ��˶����Ƿ�����Ϸ�����ڣ������ڣ����ж��ڵ�����
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// �ڵ��ܳ�ȥ�ˣ��趨�ڵ�����
					this.isAlive = false;
				}
				break;
			}
			case right: {
				// �����ڵ��˶��������
				this.bombCenter.setX(this.bombCenter.getX() + GameConst.BOMB_SPEED);
				// �ж��ڵ��˶����Ƿ�����Ϸ�����ڣ������ڣ����ж��ڵ�����
				if (this.isOutOfBound(this.bombCenter.getX(), this.bombCenter.getY())) {
					// �ڵ��ܳ�ȥ�ˣ��趨�ڵ�����
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
	 * �ж��ڵ��Ƿ�����Ϸ������
	 * @param x �ڵ���X����
	 * @param y �ڵ���Y����
	 * @return true ����ʾ�ڵ��Ѳ�����Ϸ�����ڣ�false����ʾ�ڵ�������Ϸ����
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
	 * ��һ���ڲ����߳������ڵ��˶�
	 * @author ����
	 *
	 */
	private class BombMoveDriver extends Thread {
		public void run() {
			// �˶����ڵ���ʧΪֹ
			while (Bomb.this.isAlive) {
				Bomb.this.move();
				
				try {
					// ÿ��һ�ٺ��������ڵ��˶�һ��
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

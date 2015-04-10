package com.ldj.entity;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ̹�˵��ڵ���
 * @author ����
 *
 */
public class Bomb {
	private Point bombCenter = null; // ̹�˵���������
	private final int radius = 2; // ����ڵ�ΪԲ�Σ��ڵ��뾶Ϊ2
	
	public Bomb(Point bombCenter) {
		this.bombCenter = bombCenter;
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
}

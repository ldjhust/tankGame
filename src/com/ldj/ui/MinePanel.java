package com.ldj.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;

import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import com.ldj.entity.Bomb;
import com.ldj.entity.GameConst;
import com.ldj.entity.Point;
import com.ldj.entity.Tank;

public class MinePanel extends JPanel {
	private Tank tank = null; //̹���Ǵ�����Ϸ���֮��
	private Bomb bomb = null; // �ڵ�һ����̹�˷�������ʹ�����Ϸ���֮��
	
	public MinePanel() {
		this.setPreferredSize(new Dimension(800, 600)); // �����������Ŵ�С
	}
	
	@Override
	public void paint(Graphics g) {
		// �ػ���Ϸ����
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConst.GAME_AREA_WIDTH, GameConst.GAME_AREA_HEIGHT);
		
		// ����Ϸ����ϵ�̹�˻�����
		if (this.tank != null) {
			this.tank.draw(g);
		}
		
		// ����Ϸ����ϵ��ڵ�������
		if (this.bomb != null) {
			this.bomb.draw(g);
		}
	}

	/******************************setter��getter***********************************/
	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}
}

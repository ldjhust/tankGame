package com.ldj.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import com.ldj.entity.Bomb;
import com.ldj.entity.GameConst;
import com.ldj.entity.HeroTank;
import com.ldj.entity.Point;
import com.ldj.entity.Tank;

public class MinePanel extends JPanel {
	private ArrayList<Tank> tanks = new ArrayList<Tank>();  // ArrayList�������е�̹��
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>(); // ������ϵ������ڵ�������һ����������
	
	public MinePanel() {
		this.setPreferredSize(new Dimension(800, 600)); // �����������Ŵ�С
	}
	
	@Override
	public void paint(Graphics g) {
		// �ػ���Ϸ����
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConst.GAME_AREA_WIDTH, GameConst.GAME_AREA_HEIGHT);
		
		// ����Ϸ����ϵ�̹�˻�����
		for (Tank tank : tanks) {
			if (tank != null) {
				tank.draw(g);
			}
		}
		
		// ����Ϸ���������Ҫ���ڵ�������
		for (int i = bombs.size() - 1; i >= 0; i--) {
			if (bombs.get(i) != null && bombs.get(i).isAlive()) {
				bombs.get(i).draw(g);
			} else {
				if (bombs.get(i) != null && bombs.get(i).isHeroBomb()) {
					HeroTank hero = (HeroTank)(this.tanks.get(0));
					hero.subOneHasShooted();  // Ӣ��̹����Զ����tanks�ĵ�һ��
					// ��ΪҪ��̬ɾ�������Դ�β��ͷѭ��
					this.bombs.remove(i);
				}
			}
		}
	}
	
	/**
	 * ���ڵ���ӵ������
	 * @param bomb ����ӵ��ڵ�
	 */
	public void addBomb(Bomb bomb) {
		if (bomb != null) {
			this.bombs.add(bomb);
		}
	}
	
	public void addTank(Tank tank) {
		if (this.tanks != null) {
			this.tanks.add(tank);
		}
	}

	public ArrayList<Tank> getTanks() {
		return tanks;
	}
}

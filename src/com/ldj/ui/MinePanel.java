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
import com.ldj.entity.Point;
import com.ldj.entity.Tank;

public class MinePanel extends JPanel {
	private Tank tank = null; //̹���Ǵ�����Ϸ���֮��
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
		if (this.tank != null) {
			this.tank.draw(g);
		}
		
		// ����Ϸ���������Ҫ���ڵ�������
		for (int i = bombs.size() - 1; i >= 0; i--) {
			if (bombs.get(i) != null && bombs.get(i).isAlive()) {
				bombs.get(i).draw(g);
			} else {
				if (bombs.get(i) != null && bombs.get(i).isHeroBomb()) {
					this.tank.subOneHasShooted();
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
	
	/******************************setter��getter***********************************/
	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}
}

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
	private Tank tank = null; //坦克是处于游戏面板之中
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>(); // 把面板上的所有炮弹都放在一个链表里面
	
	public MinePanel() {
		this.setPreferredSize(new Dimension(800, 600)); // 设置面板的最优大小
	}
	
	@Override
	public void paint(Graphics g) {
		// 重绘游戏区域
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConst.GAME_AREA_WIDTH, GameConst.GAME_AREA_HEIGHT);
		
		// 将游戏面板上的坦克画出来
		if (this.tank != null) {
			this.tank.draw(g);
		}
		
		// 将游戏面板上有需要的炮弹画出来
		for (int i = bombs.size() - 1; i >= 0; i--) {
			if (bombs.get(i) != null && bombs.get(i).isAlive()) {
				bombs.get(i).draw(g);
			} else {
				if (bombs.get(i) != null && bombs.get(i).isHeroBomb()) {
					this.tank.subOneHasShooted();
					// 因为要动态删除，所以从尾到头循环
					this.bombs.remove(i);
				}
			}
		}
	}
	
	/**
	 * 将炮弹添加到面板上
	 * @param bomb 待添加的炮弹
	 */
	public void addBomb(Bomb bomb) {
		if (bomb != null) {
			this.bombs.add(bomb);
		}
	}
	
	/******************************setter和getter***********************************/
	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}
}

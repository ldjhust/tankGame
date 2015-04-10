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
	private Tank tank = null; //坦克是处于游戏面板之中
	private Bomb bomb = null; // 炮弹一旦由坦克发射出来就处于游戏面板之中
	
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
		
		// 将游戏面板上的炮弹画出来
		if (this.bomb != null) {
			this.bomb.draw(g);
		}
	}

	/******************************setter和getter***********************************/
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

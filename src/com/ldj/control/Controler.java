package com.ldj.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.ldj.entity.Bomb;
import com.ldj.entity.EnemyTank;
import com.ldj.entity.GameConst.DIRECTION;
import com.ldj.entity.HeroTank;
import com.ldj.entity.Point;
import com.ldj.ui.MineFrame;
import com.ldj.ui.MinePanel;

/**
 * 游戏的控制器类
 * @author 冬杰
 *
 */
public class Controler extends KeyAdapter {
	private MinePanel minePanel = null;
	private MineFrame mineFrame = null;
	
	public Controler() {
	}
	
	/**
	 * 开始一个新游戏，并对游戏进行初始化操作
	 */
	public void start() {
		// 首先新建一个游戏面板
		this.minePanel = new MinePanel();
		// 再新建一个游戏主窗口
		this.mineFrame = new MineFrame(minePanel);
		// 给游戏主窗口添加键盘监听器
		this.mineFrame.addKeyListener(this);
		
		// 创建一个英雄并添加到面板中
		this.minePanel.addTank(new HeroTank(new Point(100, 100)));
		
		// 创建一个地方坦克
		this.minePanel.addTank(new EnemyTank(new Point(500, 300)));
		
		// 启动一个面板定时刷新的线程
		new RefreshPanel().start();
	}
	
	/**
	 * 一个内部类，用于定时刷新游戏面板
	 * @author 冬杰
	 *
	 */
	private class RefreshPanel extends Thread {
		
		@Override
		public void run() {
			while (true) {
				Controler.this.minePanel.repaint();  // 重绘游戏面板
				
				try {
					Thread.sleep(100); // 每隔100ms定时刷新
				} catch (InterruptedException e) {
					System.exit(1);
				}
			}
		}
	}

	/**
	 * 实现按键单机方法
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// 监听键盘，按照指示改变坦克的方向, wasd 上左下右
		switch (e.getKeyChar()) {
			case 'a':
			case 'A': {
				if (this.minePanel != null && this.minePanel.getTanks() != null) {
					this.minePanel.getTanks().get(0).move(DIRECTION.left);
				}
				
				break;
			}
			case 'w':
			case 'W': {
				if (this.minePanel != null && this.minePanel.getTanks() != null) {
					this.minePanel.getTanks().get(0).move(DIRECTION.up);
				}
				
				break;
			}
			case 's':
			case 'S': {
				if (this.minePanel != null && this.minePanel.getTanks() != null) {
					this.minePanel.getTanks().get(0).move(DIRECTION.down);
				}
				
				break;
			}
			case 'd':
			case 'D': {
				if (this.minePanel != null && this.minePanel.getTanks() != null) {
					this.minePanel.getTanks().get(0).move(DIRECTION.right);
				}
				
				break;
			}
			case 'j':
			case 'J': {
				// 坦克发射一颗炮弹，并将这可炮弹添加到面板上
				Bomb bomb = this.minePanel.getTanks().get(0).shoot();
				if (bomb != null) {
					this.minePanel.addBomb(bomb);
				}
			}
			default:
				break;
		}
	}
}

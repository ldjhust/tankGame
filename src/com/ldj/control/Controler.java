package com.ldj.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import com.ldj.entity.GameConst.DIRECTION;
import com.ldj.entity.Point;
import com.ldj.entity.Tank;
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
		
		if (this.minePanel.getTank() == null) {
			// 如果面板上没有坦克就创建一个坦克
			this.minePanel.setTank(new Tank(new Point(100, 100)));
		}
		
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
				if (this.minePanel != null && this.minePanel.getTank() != null) {
					this.minePanel.getTank().move(DIRECTION.left);
				}
				
				break;
			}
			case 'w':
			case 'W': {
				if (this.minePanel != null && this.minePanel.getTank() != null) {
					this.minePanel.getTank().move(DIRECTION.up);
				}
				
				break;
			}
			case 's':
			case 'S': {
				if (this.minePanel != null && this.minePanel.getTank() != null) {
					this.minePanel.getTank().move(DIRECTION.down);
				}
				
				break;
			}
			case 'd':
			case 'D': {
				if (this.minePanel != null && this.minePanel.getTank() != null) {
					this.minePanel.getTank().move(DIRECTION.right);
				}
				
				break;
			}
			case 'j':
			case 'J': {
				// 坦克发射一颗炮弹，并将这可炮弹添加到面板上
				this.minePanel.setBomb(this.minePanel.getTank().shoot());
			}
			default:
				break;
		}
	}
}

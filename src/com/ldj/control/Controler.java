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
 * ��Ϸ�Ŀ�������
 * @author ����
 *
 */
public class Controler extends KeyAdapter {
	private MinePanel minePanel = null;
	private MineFrame mineFrame = null;
	
	public Controler() {
	}
	
	/**
	 * ��ʼһ������Ϸ��������Ϸ���г�ʼ������
	 */
	public void start() {
		// �����½�һ����Ϸ���
		this.minePanel = new MinePanel();
		// ���½�һ����Ϸ������
		this.mineFrame = new MineFrame(minePanel);
		// ����Ϸ��������Ӽ��̼�����
		this.mineFrame.addKeyListener(this);
		
		// ����һ��Ӣ�۲���ӵ������
		this.minePanel.addTank(new HeroTank(new Point(100, 100)));
		
		// ����һ���ط�̹��
		this.minePanel.addTank(new EnemyTank(new Point(500, 300)));
		
		// ����һ����嶨ʱˢ�µ��߳�
		new RefreshPanel().start();
	}
	
	/**
	 * һ���ڲ��࣬���ڶ�ʱˢ����Ϸ���
	 * @author ����
	 *
	 */
	private class RefreshPanel extends Thread {
		
		@Override
		public void run() {
			while (true) {
				Controler.this.minePanel.repaint();  // �ػ���Ϸ���
				
				try {
					Thread.sleep(100); // ÿ��100ms��ʱˢ��
				} catch (InterruptedException e) {
					System.exit(1);
				}
			}
		}
	}

	/**
	 * ʵ�ְ�����������
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// �������̣�����ָʾ�ı�̹�˵ķ���, wasd ��������
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
				// ̹�˷���һ���ڵ�����������ڵ���ӵ������
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

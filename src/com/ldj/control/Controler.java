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
		
		if (this.minePanel.getTank() == null) {
			// ��������û��̹�˾ʹ���һ��̹��
			this.minePanel.setTank(new Tank(new Point(100, 100)));
		}
		
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
				// ̹�˷���һ���ڵ�����������ڵ���ӵ������
				this.minePanel.setBomb(this.minePanel.getTank().shoot());
			}
			default:
				break;
		}
	}
}

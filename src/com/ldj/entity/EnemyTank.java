package com.ldj.entity;

import com.ldj.entity.GameConst.DIRECTION;
import com.ldj.ui.MinePanel;

public class EnemyTank extends Tank {
	private MinePanel minePanel = null; // �������Ϸ��������
	
	public EnemyTank(Point EnemyTankPosition, MinePanel minePanel) {
		super(EnemyTankPosition);
		this.minePanel = minePanel;
		
		// һ�����ɾͿ�ʼ�˶�
		new MoveDriver().start();
	}
	
	@Override
	public void move(DIRECTION direction) {
		super.move(direction);
		
		if (this.needChangeDirection()) {
			this.changeDirection();
		}
	}
	
	/**
	 * ����ı�̹�˵ķ���
	 */
	public void changeDirection() {
		// ��ǽ���ˣ����������
		int d = (int) (Math.random() * 4);
		switch (d) {
		case 0: {
			EnemyTank.this.setDirection(GameConst.DIRECTION.up);
			break;
		}
		case 1: {
			EnemyTank.this.setDirection(GameConst.DIRECTION.down);
			break;
		}
		case 2: {
			EnemyTank.this.setDirection(GameConst.DIRECTION.left);
			break;
		}
		case 3: {
			EnemyTank.this.setDirection(GameConst.DIRECTION.right);
			break;
		}
		default:
			break;
		}
	}
	
	/**
	 * �ж�̹���Ƿ���Ҫ������
	 */
	public boolean needChangeDirection() {
		switch (this.getDirection()) {
			case up: {
				if (EnemyTank.this.isOutofBound(EnemyTank.this.getTankCenter().getX(), EnemyTank.this.getTankCenter().getY() - GameConst.TANK_SPEED)) {
					return true;
				}
				
				break;
			}
			case down: {
				if (EnemyTank.this.isOutofBound(EnemyTank.this.getTankCenter().getX(), EnemyTank.this.getTankCenter().getY() + GameConst.TANK_SPEED)) {
					return true;
				}
				
				break;
			}
			case left: {
				if (EnemyTank.this.isOutofBound(EnemyTank.this.getTankCenter().getX() - GameConst.TANK_SPEED, EnemyTank.this.getTankCenter().getY())) {
					return true;
				}
				
				break;
			}
			case right: {
				if (EnemyTank.this.isOutofBound(EnemyTank.this.getTankCenter().getX() + GameConst.TANK_SPEED, EnemyTank.this.getTankCenter().getY())) {
					return true;
				}
				
				break;
			}
			default: {
				break;
			}
		}
		
		return false;
	}
	
	/**
	 * �з�̹�����ҷ�̹��һ��������ǵз�̹�����Լ�������ʻ�ģ�������Ҫһ���ڲ����������
	 */
	private class MoveDriver extends Thread {
		public void run() {
			int moveFlag = 0;
			int shootFlag = 0;
			while (EnemyTank.this.isAlived()) {			
				EnemyTank.this.move(EnemyTank.this.getDirection());
				if (moveFlag < 50) {
					moveFlag++;
				} else {
					// ÿ��5000ms�ı�һ�η���
					moveFlag = 0;
					EnemyTank.this.changeDirection();
				}
				
				if (shootFlag < 10) {
					shootFlag++;
				} else {
					// ÿ��1000ms���
					shootFlag = 0;
					int shoot = (int)(Math.random() * 3); // ÿ��1s������֮һ�ĸ������
					if (shoot == 0 && EnemyTank.this.minePanel != null) {
						EnemyTank.this.minePanel.addBomb(EnemyTank.this.shoot());
					}
				}
				try {
					Thread.sleep(100);  
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

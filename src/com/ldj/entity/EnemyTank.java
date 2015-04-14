package com.ldj.entity;

import com.ldj.entity.GameConst.DIRECTION;

public class EnemyTank extends Tank {
	public EnemyTank(Point EnemyTankPosition) {
		super(EnemyTankPosition);
		
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
			int flag = 0;
			while (EnemyTank.this.isAlived()) {			
				EnemyTank.this.move(EnemyTank.this.getDirection());
				if (flag < 50) {
					flag++;
				} else {
					// ÿ��5000ms�ı�һ�η���
					flag = 0;
					EnemyTank.this.changeDirection();
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

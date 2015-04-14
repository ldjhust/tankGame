package com.ldj.entity;

import com.ldj.entity.GameConst.DIRECTION;

public class EnemyTank extends Tank {
	public EnemyTank(Point EnemyTankPosition) {
		super(EnemyTankPosition);
		
		// 一旦生成就开始运动
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
	 * 随机改变坦克的方向
	 */
	public void changeDirection() {
		// 碰墙壁了，换个方向吧
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
	 * 判断坦克是否需要换方向
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
	 * 敌方坦克与我方坦克一个区别就是敌方坦克是自己驱动行驶的，所以需要一个内部类进行驱动
	 */
	private class MoveDriver extends Thread {
		public void run() {
			int flag = 0;
			while (EnemyTank.this.isAlived()) {			
				EnemyTank.this.move(EnemyTank.this.getDirection());
				if (flag < 50) {
					flag++;
				} else {
					// 每隔5000ms改变一次方向
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

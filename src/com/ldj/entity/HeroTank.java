package com.ldj.entity;

public class HeroTank extends Tank {
	private int hasShooted = 0; // 坦克已经发射了多少颗炮弹，还在面板上的
	
	public HeroTank(Point heroPosition) {
		// 调用父类的构造函数
		super(heroPosition);
	}
	
	@Override
	public Bomb shoot() {
		if (this.hasShooted >= GameConst.TANK_MAX_BOMB_NUMBER) {
			// 一旦已经发射数量达到最大就不能在设计，除非有炮弹已经灭亡
			return null;
		}
		
		Bomb bomb = null;
		// 在坦克的炮口生成一颗炮弹
		switch (this.getDirection()) {
			case up: {
				bomb = new Bomb(new Point(this.getTankCenter().getX(), this.getTankCenter().getY() - GameConst.TANK_SHOOT_LENGTH), this.getDirection(), true);
				break;
			}
			case down: {
				bomb = new Bomb(new Point(this.getTankCenter().getX(), this.getTankCenter().getY() + GameConst.TANK_SHOOT_LENGTH), this.getDirection(), true);
				break;
			}
			case left: {
				bomb = new Bomb(new Point(this.getTankCenter().getX() - GameConst.TANK_SHOOT_LENGTH, this.getTankCenter().getY()), this.getDirection(), true);
				break;
			}
			case right: {
				bomb = new Bomb(new Point(this.getTankCenter().getX() + GameConst.TANK_SHOOT_LENGTH, this.getTankCenter().getY()), this.getDirection(), true);
				break;
			}
			default: {
				break;
			}
		}
		if (this.hasShooted == 0) {
			System.out.println(this.hasShooted);
		}
		// 坦克又成功发射一颗炮弹
		this.hasShooted += 1;
		// 播放发射炮弹的声音
		new PlaySound("sound/shoot.wav").start();
		
		return bomb;  // 返回生成的导弹，这颗导弹一旦生成就是面板上一个独立的个体，不再受坦克控制
	}
	
	public void subOneHasShooted() {
		if (this.hasShooted >= 1) {
			this.hasShooted -= 1;
		}
		
		System.out.println(this.hasShooted);
	}
}

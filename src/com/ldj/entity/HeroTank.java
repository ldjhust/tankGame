package com.ldj.entity;

public class HeroTank extends Tank {
	private int hasShooted = 0; // ̹���Ѿ������˶��ٿ��ڵ�����������ϵ�
	
	public HeroTank(Point heroPosition) {
		// ���ø���Ĺ��캯��
		super(heroPosition);
	}
	
	@Override
	public Bomb shoot() {
		if (this.hasShooted >= GameConst.TANK_MAX_BOMB_NUMBER) {
			// һ���Ѿ����������ﵽ���Ͳ�������ƣ��������ڵ��Ѿ�����
			return null;
		}
		
		Bomb bomb = null;
		// ��̹�˵��ڿ�����һ���ڵ�
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
		// ̹���ֳɹ�����һ���ڵ�
		this.hasShooted += 1;
		// ���ŷ����ڵ�������
		new PlaySound("sound/shoot.wav").start();
		
		return bomb;  // �������ɵĵ�������ŵ���һ�����ɾ��������һ�������ĸ��壬������̹�˿���
	}
	
	public void subOneHasShooted() {
		if (this.hasShooted >= 1) {
			this.hasShooted -= 1;
		}
		
		System.out.println(this.hasShooted);
	}
}

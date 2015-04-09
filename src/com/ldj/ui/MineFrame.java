package com.ldj.ui;

import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

public class MineFrame extends JFrame {
	
	public MineFrame(MinePanel minePanel) {
				
		// ���������屾�������
		this.setTitle("Tank Game ���� Version 0.1");  // ������Ϸ�����ڵı���
		this.setResizable(false); // ������ı䴰��Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ���õ�����ڵĹرվ����Ƴ���Ϸ
		this.setVisible(true); // ������Ϸ������Ŀɼ��ԣ������޷�������Ϸ����
		
		// ����������������齨
		this.add(minePanel);  // ����Ϸ��������ӻ���
		
		this.pack();  // ����Ϸ����������ӿؼ��Ĵ�С�Զ�������С
	}
}

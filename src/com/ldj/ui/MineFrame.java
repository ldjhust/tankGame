package com.ldj.ui;

import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

public class MineFrame extends JFrame {
	
	public MineFrame(MinePanel minePanel) {
				
		// 设置主窗体本身的属性
		this.setTitle("Tank Game —— Version 0.1");  // 设置游戏主窗口的标题
		this.setResizable(false); // 不允许改变窗体的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 设置点击窗口的关闭就能推出游戏
		this.setVisible(true); // 设置游戏主窗体的可见性，否则将无法看到游戏窗口
		
		// 给主窗体添加其他组建
		this.add(minePanel);  // 给游戏主窗体添加画布
		
		this.pack();  // 让游戏主窗体根据子控件的大小自动调整大小
	}
}

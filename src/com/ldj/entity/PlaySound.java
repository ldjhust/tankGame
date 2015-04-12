package com.ldj.entity;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * �����������࣬����������
 * @author ����
 *
 */
public class PlaySound extends Thread {
	private String filename = null; // �����ŵ��ļ���·��
	
	public PlaySound(String filename) {
		this.filename = filename;
	}
	
	@Override
	public void run() {
		File soundFile = new File(this.filename);
		
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auLine = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		
		try {
			auLine = (SourceDataLine)AudioSystem.getLine(info);
			auLine.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		auLine.start();
		int nBytesRead = 0;
		// ���ǻ���
		byte[] abData = new byte[512];
		
		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0) {
					auLine.write(abData, 0, nBytesRead);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auLine.drain();
			auLine.close();
		}
	}
}

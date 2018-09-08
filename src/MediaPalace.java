/*
  *    Copyright (c) The League of Amazing Programmers 2013-2018
 *    Level 1
 */



import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MediaPalace {

	public JLabel loadImageFromTheInternet(String imageURL) throws MalformedURLException {
		URL url = new URL(imageURL);
		Icon icon = new ImageIcon(url);
		return new JLabel(icon);
	}

	/*
	 * To use this method, the image must be placed in your Eclipse project in the same package as this class.
	 */
	public JLabel loadImageFromWithinProject(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}

	/*
	 * To use this method, pass in the full path of the image.
	 */
	public JLabel loadImageFromHardDrive(String filePath) {
		Icon icon = new ImageIcon(filePath);
		return new JLabel(icon);
	}

	

	 
	 private void playMp3FromComputer(String fileName) throws JavaLayerException {
	FileInputStream songStream;
	try {
		songStream = new FileInputStream(fileName);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	 final Player playMp3 = new Player(songStream);
	
	 Thread t = new Thread() {
	 public void run() {
	 try {
	 playMp3.play();
	 } catch (JavaLayerException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 }
	 };
	 t.start();
	 }

	/* This method will use your default mp3 player to play the song */
	public void playMusicOnComputer(String fileName) {
		File fileToPlay = new File(fileName);
		try {
			java.awt.Desktop.getDesktop().open(fileToPlay);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/* If you want to use an mp3, you must first convert it to a .wav file on http://media.io */
	public AudioClip loadSound(String fileName) {
		return JApplet.newAudioClip(getClass().getResource(fileName));
	}

	public void playSoundFromInternet(String soundURL) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new URL(soundURL));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(500);
		} catch (Exception ex) {
			System.out.println("Problem playing sound: " + soundURL);
			ex.printStackTrace();
		}
	}

	void speak(String words) {
		try {
			Runtime.getRuntime().exec("say " + words).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}



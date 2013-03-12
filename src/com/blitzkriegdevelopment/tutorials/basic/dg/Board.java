package com.blitzkriegdevelopment.tutorials.basic.dg;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener,KeyListener{
	
	private Player p;
	private Image bgimg;
	private Timer time;
	private ArrayList weaps;
	private int enemyspawner = 100;
	private int gamecounter = 0;
	private ArrayList<Enemy> enemies = new ArrayList();
	
	Board() {
		weaps = new ArrayList();
		p = new Player(this);
		
		spawnEnemy();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		ImageIcon i = new ImageIcon(getClass().getResource("/bgstatic.png"));
		bgimg = i.getImage();
		
		time = new Timer(5,this);
		time.start();
	}
	
	public void spawnEnemy() {
		Enemy enemy = new Enemy(this,1);
		enemies.add(enemy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		p.move();
		for(int i =0; i < enemies.size(); i++){
			Enemy enemy = enemies.get(i);
			enemy.move();
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bgimg,0,0,null);
		g2d.drawImage(p.getImage(),p.getX(),p.getY(),null);
		
		for (int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			g2d.drawImage(e.getImage(),e.getX(),e.getY(),null);
		}
		
		for (int i = 0 ; i < weaps.size(); i++){
			BaseWeapon weap = ((BaseWeapon)weaps.get(i));
			g2d.drawImage(weap.getImage(),weap.getX(),weap.getY(),null);
			
			if(weap.getX() >= 700){
				weaps.remove(weap);
			}
			
			for(int j=0; j < enemies.size();j++){
				Enemy e = enemies.get(j);
				if(weap.getBounds().intersects(e.getBounds())){
					//enemy at j was hit by current weapon, remove both bullet and enemy
					weaps.remove(weap);
					enemies.remove(e);
				}
			}
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		p.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		p.keyReleased(e);
	}
	
	public void addBullit(BaseWeapon b) {
		weaps.add(b);
	}
	
}

package com.blitzkriegdevelopment.tutorials.basic.dg;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public abstract class AbstractEntity implements ActionListener,IEntity{
	
	// local properties
	protected Image _gfx;
	protected Board _board;
	protected int _x;
	protected int _y;
	protected int _width;
	protected int _height;
	protected int _speed;
	protected int _jumpspeed = 2;
	protected int _direction;
	protected int _dy;
	protected int _dx;
	protected int _yground;
	protected int _jumplimit = 70;
	protected int curimgindex;
	protected int _cyclespeed;
	protected boolean _inair;
	protected Timer _walkcycletimer;
	protected ArrayList<ImageIcon> _staticimg;
	protected ArrayList<ImageIcon> _cycleimgLeft;
	protected ArrayList<ImageIcon> _cycleimgRight;
	private int curhealth;
	private int maxhealth;
	
	public AbstractEntity(Board brd, int yground, int cyclestart, int cycleend, String staticl, String staticr, String preleft, String preright, String suffix, int cyclespeed, int dir, int speed){
		_cyclespeed = cyclespeed;
		_walkcycletimer = new Timer(_cyclespeed,this);
		_staticimg = new ArrayList<ImageIcon>();
		_cycleimgLeft = new ArrayList<ImageIcon>();
		_cycleimgRight = new ArrayList<ImageIcon>();
		_staticimg.add(new ImageIcon(getClass().getResource("/"+staticl)));
		_staticimg.add(new ImageIcon(getClass().getResource("/"+staticr)));
		_gfx = _staticimg.get(1).getImage();
		_speed = speed;
		fillCycleImages(cyclestart, cycleend, preleft, preright, suffix);
		setYground(yground);
		setDirection(dir);
		_inair=false;
		_board = brd;
	}
	
	protected void fillCycleImages(int start,int end, String preleft, String preright, String suffix){
		for(int i = start; i < end; i++){
			_cycleimgLeft.add(new ImageIcon(getClass().getResource("/"+preleft+i+suffix)));
			_cycleimgRight.add(new ImageIcon(getClass().getResource("/"+preright+i+suffix)));
		}
	}
	
	@Override
	public int getX() {
		return _x;
	}

	@Override
	public int getY() {
		return _y;
	}

	@Override
	public int getWidth() {
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}

	@Override
	public int getSpeed() {
		return _speed;
	}

	@Override
	public int getDirection() {
		return _direction;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(_x,_y,_width,_height);
	}

	@Override
	public void setX(int x) {
		x = x;
	}

	@Override
	public void setY(int y) {
		y = y;
	}

	@Override
	public void setWidth(int w) {
		_width = w;
	}

	@Override
	public void setHeight(int h) {
		_height = h;
	}

	@Override
	public void setSpeed(int s) {
		_speed = s;
	}

	@Override
	public void setDirection(int dir) {
		_direction = dir;
	}
	
	@Override
	public void setStaticImg(ArrayList al){
		_staticimg = al;
	}
	
	@Override
	public void setCycleImg(ArrayList all,ArrayList alr){
		_cycleimgLeft = all;
		_cycleimgRight = alr;
	}
	
	@Override
	public int getHealth() {
		return curhealth;
	}

	@Override
	public int getMaxHealth() {
		return maxhealth;
	}

	@Override
	public void setHealth(int h) {
		curhealth=h;
	}

	@Override
	public void setMaxHealth(int mh) {
		maxhealth = mh;
	}
	
	@Override
	public Image getImage() {
		return _gfx;
	}
	
	@Override
	public ArrayList getCycleImg(int i){
		if(i == 0){
			return _cycleimgLeft;
		} else if(i == 1){
			return _cycleimgRight;
		}
		return null;
	}
	
	@Override
	public int getYground() {
		return _yground;
	}

	@Override
	public void setYground(int y) {
		_yground = y;
	}
	
}

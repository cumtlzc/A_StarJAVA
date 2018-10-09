package aStar;

import java.awt.Color;
import java.awt.Graphics;

public class ObstacleObject{
	int x;
	int y;
	int w;
	int h;
	public ObstacleObject(int x, int y, int w, int h) {
		super();
		this.y = y;
		this.x = x;
		this.w = w;
		this.h = h;
	}

	public ObstacleObject() {
		x=(int)(Constant.FRAME_WIDTH*Math.random());
		y=(int)(Constant.FRAME_HEIGHT*Math.random());
		if(y<Constant.TITLE_WIDTH) {
			y=Constant.TITLE_WIDTH;
		}
		w=(int)((Constant.FRAME_WIDTH-x)*Math.random()+10);
		h=(int)((Constant.FRAME_HEIGHT-x)*Math.random()+10);	
	}

	public void drawSelf(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.GREEN);	
		g.fillRect(x, y,w,h);
		g.setColor(c);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
}

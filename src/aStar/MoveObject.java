package aStar;

import java.awt.Color;
import java.awt.Graphics;


public class MoveObject {
	int x=0;
	int y=0;
	
	public MoveObject(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public MoveObject() {
		x=(int)(Constant.FRAME_WIDTH*Math.random());
		y=(int)(Constant.FRAME_HEIGHT*Math.random());
		if(x>Constant.FRAME_WIDTH-Constant.MOVEOBJECT_WIDTH) {
			x=Constant.FRAME_WIDTH-Constant.MOVEOBJECT_WIDTH-10;
		}
		if(y>Constant.FRAME_HEIGHT-Constant.MOVEOBJECT_HEIGHT) {
			y=Constant.FRAME_HEIGHT-Constant.MOVEOBJECT_HEIGHT-10;
		}
		if(y<Constant.TITLE_WIDTH+60) {
			y=Constant.TITLE_WIDTH+60;
		}
		while((x<450&&x>140&&y<220&&y>190)
				||(x<250&&x>40&&y<370&&y>340)
				||(x<300&&x>90&&y<290&&y>240)
				||(x<250&&x>10&&y<170&&y>140)
				||(x<320&&x>290&&y<180&&y>80)
				||(x<370&&x>340&&y<480&&y>370)) {
			x=(int)(Constant.FRAME_WIDTH*Math.random());
			y=(int)(Constant.FRAME_HEIGHT*Math.random());
			if(x>Constant.FRAME_WIDTH-Constant.MOVEOBJECT_WIDTH) {
				x=Constant.FRAME_WIDTH-Constant.MOVEOBJECT_WIDTH-10;
			}
			if(y>Constant.FRAME_HEIGHT-Constant.MOVEOBJECT_HEIGHT) {
				y=Constant.FRAME_HEIGHT-Constant.MOVEOBJECT_HEIGHT-10;
			}
			if(y<Constant.TITLE_WIDTH+60) {
				y=Constant.TITLE_WIDTH+60;
			}
		}
		
			
	}
	
	public void drawSelf(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.RED);	
		g.fillRect(x, y, Constant.GOALOBJECT_WIDTH,  Constant.GOALOBJECT_HEIGHT);
		g.setColor(c);
	}
	public void drawSelf(Graphics g,int nodeX,int nodeY) {
		Color c=g.getColor();
		g.setColor(Color.RED);	
		g.fillRect(nodeX, nodeY, Constant.GOALOBJECT_WIDTH,  Constant.GOALOBJECT_HEIGHT);
		g.setColor(c);
	}
	
	
}

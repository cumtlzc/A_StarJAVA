package aStar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;




public class MyFrame extends Frame {
	
	Date startTime=new Date();
	Date endTime;
	int period;
	
	MoveObject mo=new MoveObject();
	GoalObject go=new GoalObject();
	ObstacleObject oo1=new ObstacleObject(150,200,300,20);
	ObstacleObject oo2=new ObstacleObject(50,350,200,20);
	ObstacleObject oo3=new ObstacleObject(100,250,200,40);
	ObstacleObject oo4=new ObstacleObject(20,150,200,20);
	ObstacleObject oo5=new ObstacleObject(300,90,20,90);
	ObstacleObject oo6=new ObstacleObject(350,380,20,100);
	int num=0;
	ArrayList<Node> arrayList = new ArrayList<Node>();
	/**
	 * 初始化窗口
	 */
	public void launchFrame() {
		this.setTitle("模拟A*算法寻找路径");
		this.setVisible(true);
		this.setSize(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
		this.setLocation(100, 50);
		this.setBackground(Color.WHITE);
		
		
		new PaintThread().start();//启动重画窗口线程
		
		Node startNode = new Node(mo.x, mo.y);
        Node endNode = new Node(go.x, go.y);
        
        Node parent = new AStar().findPath(startNode, endNode,oo1);
       
        endTime =new Date();
		period=(int)((endTime.getTime()-startTime.getTime()));
		System.out.println(period);
        while (parent!=null) {
			arrayList.add(new Node(parent.x, parent.y));
            parent=parent.parent;
        }
       
        Collections.reverse(arrayList);
        for(int i=0;i<arrayList.size();i++) {
        	System.out.println(arrayList.get(i).x+","+arrayList.get(i).y);	
        }
        System.out.println(arrayList.size());
		/**
		 * 关闭窗口时
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	/**
	 * 画笔
	 * @param args
	 */
	@Override
	public void paint(Graphics g) {
		
		Color c=g.getColor();
		Font f=g.getFont();
		g.setFont(new Font("宋体",Font.PLAIN,14));
		g.setColor(Color.RED);
		g.drawString(" ■：起始点",0, Constant.TITLE_WIDTH);
		g.setColor(Color.ORANGE);
		g.drawString("■：目标点",80, Constant.TITLE_WIDTH);
		g.setColor(Color.GREEN);
		g.drawString("■：障碍物",160, Constant.TITLE_WIDTH);
		g.setColor(Color.BLACK);
		g.fillRect(0, 50, Constant.FRAME_WIDTH, 10);
		mo.drawSelf(g);
		go.drawSelf(g);
		oo1.drawSelf(g);
		oo2.drawSelf(g);
		oo3.drawSelf(g);
		oo4.drawSelf(g);
		oo5.drawSelf(g);
		oo6.drawSelf(g);
		
		if(num<arrayList.size()){
			mo.drawSelf(g,arrayList.get(num).x,arrayList.get(num).y);
			num++;
			if(num==arrayList.size()-10) {
				g.setColor(Color.BLUE);
				g.drawString("完成路径寻找任务，用时"+period+"ms",280,Constant.TITLE_WIDTH);	
			}
			
		}
	
		

		g.setColor(c);
		g.setFont(f);
	

		
	}
	/**
	 * 定义一个内部的重画线程
	 * @author liuzhaochao
	 *
	 */
	class PaintThread extends Thread{
		
	    @Override
	    public void run() {
	    	while(true) {
	    		repaint();//重画

	    		try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
	public static void main(String[] args) {
		MyFrame mf=new MyFrame();
		mf.launchFrame();
		
	}

	
	//双缓冲,解决窗口闪动问题
		private Image offScreenImage = null;
		 
		public void update(Graphics g) {
		    if(offScreenImage == null)
		        offScreenImage = this.createImage(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);//这是游戏窗口的宽度和高度
		     
		    Graphics gOff = offScreenImage.getGraphics();
		    paint(gOff);
		    g.drawImage(offScreenImage, 0, 0, null);
		}   
}

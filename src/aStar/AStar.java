package aStar;
import java.util.ArrayList;
import java.util.List;
public class AStar {
		private ArrayList<Node> openList = new ArrayList<Node>();
		private ArrayList<Node> closeList=new ArrayList<Node>();
		/*
		public Node findMinFNodeInOpenList() {
			Node tempNode = openList.get(0);
	        for (Node node:openList) {
	            if (node.F < tempNode.F) {
	                tempNode = node;
	            }
	        }
	        return tempNode;
		}*/
		
		//在表中寻找指定的节点的坐标返回该节点
		public static Node find(List<Node> nodes, Node point) {
	        for (Node n : nodes)
	            if ((n.x == point.x) && (n.y == point.y)) {
	                return n;
	            }
	        return null;
	    }
		//判断在表中是否存在指定的节点
	    public static boolean exists(List<Node> nodes, Node node) {
	        for (Node n : nodes) {
	            if ((n.x == node.x) && (n.y == node.y)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    //判断在表中是否存在指定的节点
	    public static boolean exists(List<Node> nodes, int x, int y) {
	        for (Node n : nodes) {
	            if ((n.x == x) && (n.y == y)) {
	                return true;
	            }
	        }
	        return false;
	    }
		//通过当前节点寻找邻居节点
		public ArrayList<Node> findNeighborNodes(Node currentNode,ObstacleObject oo) {
	        ArrayList<Node> arrayList = new ArrayList<Node>();
	        // 只考虑上下左右，不考虑斜对角
	        int topX = currentNode.x;
	        int topY = currentNode.y-1;
	        if (topY>50&&!exists(closeList, topX, topY)&&notWall(topX,topY)) {
	            arrayList.add(new Node(topX, topY,0));
	        }
	        int bottomX = currentNode.x;
	        int bottomY = currentNode.y+1;
	        if (bottomY<Constant.FRAME_HEIGHT&&!exists(closeList, bottomX, bottomY)&&notWall(bottomX,bottomY)) {
	            arrayList.add(new Node(bottomX, bottomY,0));
	        }

	        int leftX = currentNode.x-1;
	        int leftY = currentNode.y;
	        if (leftX>0&&!exists(closeList, leftX, leftY)&&notWall(leftX,leftY)) {
	            arrayList.add(new Node(leftX, leftY,0));
	        }
	        int rightX = currentNode.x+1;
	        int rightY = currentNode.y;
	        if (rightX<Constant.FRAME_WIDTH&&!exists(closeList, rightX, rightY)&&notWall(rightX,rightY)) {
	            arrayList.add(new Node(rightX, rightY,0));
	        }
	        
	        // 考虑斜对角
	        int top_leftX = currentNode.x-1;
	        int top_leftY = currentNode.y-1;
	        if (top_leftY>Constant.TITLE_WIDTH&&top_leftX>0
	        	&&!exists(closeList, top_leftX, top_leftY)
	        	&&notWall(top_leftX,top_leftY)) {
	            arrayList.add(new Node(top_leftX, top_leftY,1));
	        }
	        int bottom_leftX = currentNode.x-1;
	        int bottom_leftY = currentNode.y+1;
	        if (bottom_leftY<Constant.FRAME_HEIGHT&&bottom_leftX>0
        		&&!exists(closeList, bottom_leftX, bottom_leftY)
        		&&notWall(bottom_leftX, bottom_leftY)) {
	            arrayList.add(new Node(bottom_leftX, bottom_leftY,1));
	        }

	        int top_rightX = currentNode.x+1;
	        int top_rightY = currentNode.y-1;
	        if (top_rightX<Constant.FRAME_WIDTH&&top_rightY>Constant.TITLE_WIDTH
	        	&&!exists(closeList, top_rightX, top_rightY)
	        	&&notWall(top_rightX, top_rightY)) {
	            arrayList.add(new Node(top_rightX, top_rightY,1));
	        }
	        int bottom_rightX = currentNode.x+1;
	        int bottom_rightY = currentNode.y+1;
	        if (bottom_rightX<Constant.FRAME_WIDTH&&bottom_rightY<Constant.FRAME_HEIGHT
	        	&&!exists(closeList, bottom_rightX, bottom_rightY)
	        	&&notWall(bottom_rightX, bottom_rightY)) {
	            arrayList.add(new Node(bottom_rightX, bottom_rightY,1));
	        }
	        return arrayList;
	    }
		//判断是否为障碍物
		private boolean notWall(int x, int y) {
			if((x<450&&x>140&&y<220&&y>190)
				||(x<250&&x>40&&y<370&&y>340)
				||(x<300&&x>90&&y<290&&y>240)
				||(x<250&&x>10&&y<170&&y>140)
				||(x<320&&x>290&&y<180&&y>80)
				||(x<370&&x>340&&y<480&&y>370)) {
				return false;	
			}
			return true;
		}
		public Node findPath(Node startNode, Node endNode,ObstacleObject oo) {
	        // 把起点加入 open list
	        openList.add(startNode);
	        while (openList.size() > 0) {
	 
	        	 // 遍历 open list ，查找 F值最小的节点，把它作为当前要处理的节点
	            Node tempNode = openList.get(0);
	            for (Node node:openList) {
	                if (node.F < tempNode.F) {
	                    tempNode = node;
	                }
	            }
	            Node currentNode = tempNode;
	            // 从open list中移除
	            openList.remove(currentNode);
	            // 把这个节点移到 close list
	            closeList.add(currentNode);
	            ArrayList<Node> neighborNodes = findNeighborNodes(currentNode,oo);
	            for (Node node : neighborNodes) {
	                if (exists(openList, node)) {
	                    foundPoint(currentNode, node);
	                } else {
	                    notFoundPoint(currentNode, endNode, node);
	                }
	            }

	            if (find(openList, endNode)!= null) {
	                return find(openList, endNode);
	            }
	        }
	        return find(openList, endNode);
	    }

	    private void foundPoint(Node tempStart, Node node) {
	        int G = calcG(tempStart, node,node.symble);
	        if (G < node.G) {
	            node.parent = tempStart;
	            node.G = G;
	            node.calcF();
	        }
	    }
	    private void notFoundPoint(Node tempStart, Node end, Node node) {
	        node.parent = tempStart;
	        node.G = calcG(tempStart, node,node.symble);
	        node.H = calcH(end, node);
	        node.calcF();
	        openList.add(node);
	    }

	    private int calcG(Node start, Node node,int Value) {
	       int G;
	       
	    	if(Value==0) {
	    	 G = 10;  
	       }else {
	    	 G = 14;  
	       }
	       int parentG=node.parent!= null?node.parent.G:0;
	       return G+parentG;
	    }

	    
	    private int calcH(Node end, Node node) {
	        //int step = Math.abs(node.x - end.x) + Math.abs(node.y - end.y);//使用曼哈顿距离计算H
	    	int step =(node.x-end.x)*(node.x-end.x)+(node.y-end.y)*(node.y-end.y);
	    	//int step =Math.max(Math.abs(node.x-end.x), Math.abs(node.y-end.y));
	    	return step*Constant.STEP;
	    }
	

}


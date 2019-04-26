package Draw;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

public class RoundRect implements IShape {
	
	private int top;
	private int left;
	private int width;
	private int height;
	private GC gcMain;
	private int color;
	private int line;
	Display display = Display.getDefault();
	public RoundRect() {
		// TODO Auto-generated constructor stub
	}
	public RoundRect(int top,int left,int width,int height,GC gcMain) {
		this.top=top;
		this.left=left;		
		this.width=width;
		this.height=height;
		this.gcMain=gcMain;
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		gcMain.setLineStyle(line);
		gcMain.setForeground(display.getCurrent().getSystemColor(color));
		gcMain.drawRoundRectangle(top, left, width, height,30,30);
	}
	public static String getToolText() {
		return"Ô²½Ç¾ØÐÎ";
	}

	@Override
	public void setTop(int top) {
		// TODO Auto-generated method stub
		this.top=top;
	}

	@Override
	public void setLeft(int left) {
		// TODO Auto-generated method stub
		this.left=left;
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width=width;
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height=height;
	}

	@Override
	public void setGcMain(GC gcMain) {
		// TODO Auto-generated method stub
		this.gcMain=gcMain;
	}
	@Override
	public void setColor(int color) {
		// TODO Auto-generated method stub
		this.color=color;
	}
	public void setLine(int line) {
		this.line=line;
	}
	@Override
	public int getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public int getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public GC getGcMain() {
		// TODO Auto-generated method stub
		return gcMain;
	}
	public int getColor() {
		return color;
	}
	public int getLine() {
		return line;
	}
}

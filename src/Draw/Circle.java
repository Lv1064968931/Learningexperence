package Draw;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

public class Circle implements IShape {
	private int top;
	private int left;
	private int width;
	private int height;
	private GC gcMain;
	private int color;
	private int line;
	Display display = Display.getDefault();
	public Circle(int top,int left,int width,int height,GC gcMain) {
		this.top=top;
		this.left=left;
		this.height=left;
		this.width=width;
		this.gcMain=gcMain;
	}
	public Circle() {
	
	}
	public void setTop(int top) {
		this.top = top;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setGcMain(GC gcMain) {
		this.gcMain = gcMain;
	}
	public void setColor(int color) {
		// TODO Auto-generated method stub
		this.color=color;
	}
	public void setLine(int line) {
		this.line=line;
	}
	public int getTop() {
		return top;
	}
	public int getLeft() {
		return left;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public GC getGcMain() {
		return gcMain;
	}
	public int getColor() {
		return color;
	}
	public int getLine() {
		return line;
	}
	public static String getToolText() {
		return"Õ÷‘≤";
	}
	@Override
	public void draw() {
		gcMain.setLineStyle(line);
		gcMain.setForeground(display.getCurrent().getSystemColor(color));
		gcMain.drawOval(top, left, width, height);
	}
	
}

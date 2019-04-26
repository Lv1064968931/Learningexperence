package Draw;


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public interface IShape {
	void draw();

	void setTop(int i);

	void setLeft(int i);

	void setWidth(int i);

	void setHeight(int i);

	void setGcMain(GC gcMain);
	
	void setColor(int color);
	
	void setLine(int line);
    
	int getTop();
	
	int getLeft();
	
	int getWidth();
	
	int getHeight();
	
	GC getGcMain();
	
	int getLine();
	
	int getColor(); 
}

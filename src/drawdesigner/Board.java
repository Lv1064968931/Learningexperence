package drawdesigner;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.PublicKey;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;

import com.ibm.icu.impl.locale.StringTokenIterator;

import org.eclipse.swt.graphics.Point;

import Draw.Circle;
import Draw.IShape;

public class Board {
	private Shell shell;
	private GC _gcMain;
	private ArrayList<IShape> shapes;
    
	public Board(Shell shell,GC gcMain){
		shapes=new ArrayList<IShape>();
		this.shell=shell;
		_gcMain=gcMain;
	}
	public void insertShape(IShape shape) {
		shapes.add(shape);
	}
	
	public void refresh() {
		Point pt=shell.getSize();
//        _gcMain.fillRectangle(0, 0, pt.x, pt.y);;
		for(IShape shape:shapes) {
			shape.draw();
			}
	}
	public void Save(String filename)throws IOException{
		PrintStream out=new PrintStream(new File(filename));
		out.println(shapes.size());
		for(IShape shape:shapes) {
			writeIShape(out,shape);
		}
		out.close();
	}
	public void Open(String filename)throws IOException{
		shapes.clear();
		String line;
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		line=reader.readLine();
		int ShapeCount=Integer.parseInt(line);
		for(int i=0;i<ShapeCount;i++) {
			IShape shape=readShape(reader);
			insertShape(shape);
		}
		reader.close();
		refresh();
	}
	private void writeIShape(PrintStream out,IShape shape)throws IOException {
		out.println(shape.getClass().getName());
		out.println(shape.getTop());
		out.println(shape.getLeft());
		out.println(shape.getHeight());
		out.println(shape.getWidth());
		out.println(shape.getColor());
		out.println(shape.getLine());
	}
	private IShape readShape(BufferedReader in)throws IOException{
		String className=in.readLine();
		String top=in.readLine();
		String left=in.readLine();
		String width=in.readLine();
		String height=in.readLine();
		String color=in.readLine();
		String line=in.readLine();
		
		int l = Integer.parseInt(left);
		int t = Integer.parseInt(top);
		int w = Integer.parseInt(width);
		int h = Integer.parseInt(height);
		
		int c=Integer.parseInt(color);
		int li=Integer.parseInt(line);
		
		IShape shape=null;
		try {
			Class<?>shapeClass=Class.forName(className);
			Object oShape=shapeClass.newInstance();
			shape=(IShape)oShape;
			shape.setTop(t);
			shape.setLeft(l);
			shape.setHeight(h);
			shape.setWidth(w);
			shape.setGcMain(_gcMain);
			shape.setColor(c);
			shape.setLine(li);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return shape;
	}
	
}

package drawdesigner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import Draw.IShape;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class draw{

	/**
	 * Launch the application.
	 * @param args
	 */
	private static boolean leftButtonDown=false;
	private static GC gcMain=null;
	private static int startX = 0;
	private static int startY = 0;
	private static int lastWidth=0;
	private static int lastHeight=0;
	private static String shapeType="Rect";
	private static int color;
	private static int line;
	public static void main(String[] args) {
		
		List listClass=null;
		String pkg="Draw";
		listClass=ClassUtil.getClassList(pkg, true, null);
		ArrayList<String>shapeTypes=new ArrayList<String>();
		for(Object object:listClass) {
			String name=((Class<?>)object).getName();
			if(!name.equals("Draw.IShape")) {
				shapeTypes.add(name);
			}
			
		}
		Display display = Display.getDefault();
		Shell shell = new Shell();
		gcMain=new GC(shell);
		Board board=new Board(shell,gcMain);
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				board.refresh();
			}
		});
		shell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if(e.button==1) {
				shell.setCursor(new Cursor(null, SWT.CURSOR_CROSS));
				leftButtonDown=false;
				int width=e.x-startX;
				System.out.println(width);
				int height=e.y-startY;
				System.out.println(height);
		        //erase rect range//
				gcMain.setLineStyle(SWT.LINE_DOT);
				gcMain.setForeground(shell.getBackground());
				gcMain.drawRectangle(startX, startY,width,height);
				gcMain.setLineStyle(SWT.LINE_SOLID);
				/////////////////////////////
				
				
				
//					switch(shapeType) {
//					case 1:
//						shape=new Rect(startX, startY, width, height, gcMain);
//						break;
//					case 2:
//						shape=new Circle(startX, startY, width, height, gcMain);
//						break;
//					default:
//							shape=new Rect(startX, startY, width, height, gcMain);
//							break;
//					}
//					
//					board.insertShape(shape);
//					board.refresh();
				IShape shape;
					try {
						Class shapeClass=Class.forName(shapeType);
						Object oShape=shapeClass.newInstance();
						shape=(IShape)oShape;
						shape.setColor(color);
						shape.setLine(line);
						shape.setTop(startX);
						shape.setLeft(startY);
						shape.setWidth(e.x-startX);
						shape.setHeight(e.y-startY);
						shape.setGcMain(gcMain);
					} catch (Exception e2) {
						// TODO: handle exception
						shape=null;
					}
					if(shape!=null) {
					board.insertShape(shape);
					board.refresh();
					}
					shell.setCursor(new Cursor(null, 0));
				}
			}
			public void mouseDown(MouseEvent e) {
				if(e.button==1) {
				leftButtonDown=true;
				startX = e.x;
				System.out.println(startX);
				startY = e.y;
				System.out.println(startY);
				shell.setCursor(new Cursor(null, SWT.CURSOR_CROSS));
				}
			}
		});
		shell.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if(leftButtonDown) {
					gcMain.setLineStyle(SWT.LINE_DOT);
					gcMain.setForeground(shell.getBackground());
					gcMain.drawRectangle(startX, startY, lastWidth, lastHeight);
					gcMain.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
					gcMain.drawRectangle(startX, startY, arg0.x-startX, arg0.y-startY);
					lastWidth=arg0.x-startX;
					lastHeight=arg0.y-startY;
					gcMain.setLineStyle(SWT.LINE_SOLID);
				}
			}
		});
		shell.setSize(542, 414);
		shell.setText("\u7ED8\u56FE\u677F");
		shell.setLayout(null);
		
	    Group group1 = new Group(shell, SWT.NONE);
		group1.setBounds(334,41,176,98);
		
		Button btnRadioButton = new Button(group1, SWT.RADIO);
		btnRadioButton.setSelection(false);
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				color=SWT.COLOR_BLACK;
			}
		});
		btnRadioButton.setBounds(10, 10, 119, 20);
		btnRadioButton.setText("black");
		
		Button btnRadioButton_1 = new Button(group1, SWT.RADIO);
		btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				color=SWT.COLOR_GREEN;
			}
		});
		btnRadioButton_1.setBounds(10, 36, 119, 20);
		btnRadioButton_1.setText("green");
		
		Button btnRadioButton_2 = new Button(group1, SWT.RADIO);
		btnRadioButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				color=SWT.COLOR_RED;
			}
		});
		btnRadioButton_2.setBounds(10, 62, 119, 20);
		btnRadioButton_2.setText("red");
		
		Group group2=new Group(shell, SWT.NONE);
		group2.setBounds(334,166,176,71);
		
		Button btnRadioButton_3 = new Button(group2, SWT.RADIO);
		btnRadioButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				line=SWT.LINE_SOLID;
			}
		});
		btnRadioButton_3.setBounds(10, 20, 119, 20);
		btnRadioButton_3.setText("solidline");
		
		Button btnRadioButton_4 = new Button(group2, SWT.RADIO);
		btnRadioButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				line=SWT.LINE_DOT;
			}
		});
		btnRadioButton_4.setBounds(10, 42, 119, 20);
		btnRadioButton_4.setText("dotline");
		
		
		Button btnOPen=new Button(shell,SWT.NONE);
		btnOPen.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					System.out.println("xxxx");
					board.Open("D:\\aaa.shape");
					MessageDialog.openInformation(shell, "提示", "打开成功");
				}catch (Exception ex) {
					// TODO: handle exception
					return;
				}
			}
		});
		btnOPen.setBounds(330, 325, 80, 27);
		btnOPen.setText("Open");
		
		Button btnSave=new Button(shell, SWT.NONE);
		
		btnSave.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
				
					board.Save("D:\\aaa.shape");
					MessageDialog.openInformation(shell, "提示", "保存成功");
					System.out.println("保存成功");
					
				} catch (Exception ex) {
					// TODO: handle exception
					System.out.println("保存失败"+ex.getMessage());
					return;
				}
			}
		});
		btnSave.setBounds(430, 325, 80, 27);
		btnSave.setText("Save");
// add button by shapetypes//
		int indexButton=0;
		for(String strClass:shapeTypes) {
			Button btn=new Button(shell, SWT.NONE);
			btn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					shapeType=strClass;
				}
			});
			btn.setBounds(84*indexButton,0,80,27);
			indexButton++;
			try {
				Class<?>shapeClass=Class.forName(strClass);
				Method method=shapeClass.getMethod("getToolText");
				btn.setText(method.invoke(null, null).toString());
				btn.setData("shapeType",strClass);
			}catch(Exception e) {
				btn.setText(strClass);
				btn.setData("shapeType",strClass);
			}
		}
//		Button btnRect = new Button(shell, SWT.NONE);
//		btnRect.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
				
//				shapeType=1;
				
//				try {
//					Class shapeClass=Class.forName("drawdesigner.Rect");
//					Object oshape=shapeClass.newInstance();
//					IShape shape=(IShape)oshape;
//					shape.setTop(100);
//					shape.setLeft(200);
//					shape.setWidth(300);
//					shape.setHeight(400);
//					shape.setGcMain(gcMain);		
//					System.out.println("sssss"+shape.getWidth());
//				}
//				catch(Exception e1) {
//					
//				}
//				shell.setCursor(new Cursor(null, SWT.CURSOR_HELP));
//				shapeType="Rect";
//				List listClass=null;
//				String pkg="drawdesigner";
//				listClass=ClassUtil.getClassList(pkg, true, null);
//				
//				ArrayList<String>shapeTypes=new ArrayList<String>();
//				for(Object object:listClass) {
//					String name=((Class<?>)object).getName();
//					if(!name.equals("drawdesigner.IShape")) {
//						shapeTypes.add(name);
//					}
//				}
//				for(String str:shapeTypes) {
//					System.out.println(str);
//				}
//			}
//		});
//		btnRect.setBounds(10, 10, 98, 30);
//		btnRect.setText("Rect");
		
//		Button btnCircle = new Button(shell, SWT.NONE);
//		btnCircle.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
////				shapeType=2;
//				try {
//					Class shapeClass=Class.forName("Circle");
//					Object oShape=shapeClass.newInstance();
//					IShape shape=(IShape)oShape;
//					shape.setTop(100);
//					shape.setLeft(200);
//					shape.setWidth(300);
//					shape.setHeight(400);
//					shape.setGcMain(gcMain);
//					System.out.println(shape.getWidth());
//					
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//				shell.setCursor(new Cursor(null, SWT.CURSOR_HELP));
//				shapeType="Circle";
//			}
//		});
//		btnCircle.setBounds(210, 10, 98, 30);
//		btnCircle.setText("Circle");
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}

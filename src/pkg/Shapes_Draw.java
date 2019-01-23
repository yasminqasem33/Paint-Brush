/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Shapes_Draw extends Applet {

    Color colorState;
    int counter = 0;
    String flagShape = null;
    Button btnOval;
    Button btnRect;
    Button btnLine;
    Button btnRed;
    Button btnGreen;
    Button btnBlue;
    Button clearAll;
    Button delete;
    Button undo;
    Button freeDraw;
    Checkbox checkBox1;

    //ArrayList<Oval> listOvalFill = new ArrayList<>();
    ArrayList<Oval> listOval = new ArrayList<>();
    ArrayList<Rect> listRect = new ArrayList<>();
    ArrayList<Line> listLine = new ArrayList<>();
    ArrayList<Shape> listShapes = new ArrayList<>();
    // ArrayList<Rect> listRectFill = new ArrayList<>();

    Oval oval;
    Line line;
    Rect rect;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    class Shape {

        int x1, y1;
        int order;
        Color colorShape;

    }

    class Line extends Shape {

        int x2, y2;

    }

    class Oval extends Shape {

        int w, h;
        String isFill;

    }

    class Rect extends Shape {

        int w, h;
        String isFill;

    }

    public void init() {

        btnRed = new Button("red");
        btnGreen = new Button("Green");
        btnBlue = new Button("Blue");
        btnOval = new Button("Oval");
        btnRect = new Button("Rectangle");
        btnLine = new Button("Line");
        clearAll = new Button("Clear All");
        checkBox1 = new Checkbox();
        delete = new Button("Delete");
        undo = new Button("Undo");
        freeDraw = new Button("Free Draw");
        checkBox1.setLabel("Filled Checkbox");
        freeDraw.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                flagShape = "lineFree";
                System.out.println(flagShape);

            }

        });
        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Shape poped = listShapes.remove(listShapes.size() - 1);

                if (poped instanceof Line == true) {

                    listLine.remove(listLine.size() - 1);
                } else if (poped instanceof Oval == true) {
                    listOval.remove(listOval.size() - 1);

                } else if (poped instanceof Rect == true) {
                    listRect.remove(listRect.size() - 1);

                }
                repaint();

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                flagShape = "rectDel";
            }

        });
        clearAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listOval.removeAll(listOval);
                listRect.removeAll(listRect);
                listLine.removeAll(listLine);

                repaint();

            }

        });
//        btnFree = new Button("Free Draw");
        btnGreen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                colorState = Color.GREEN;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        btnBlue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                colorState = Color.BLUE;
            }

        }
        );
        btnRed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                colorState = Color.RED;

            }

        }
        );

        btnOval.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                flagShape = "oval";
                //counter++;
            }

        }
        );
        btnRect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                flagShape = "rect";
                //counter++;
            }

        }
        );
        btnLine.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                flagShape = "line";
                //counter++;
            }

        }
        );
        add(btnRed);
        add(btnBlue);
        add(btnGreen);
        add(btnOval);
        add(btnRect);
        add(btnLine);
        add(clearAll);
        add(checkBox1);
        add(delete);
        add(undo);
      add(freeDraw);

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(flagShape);

            try {
                if (flagShape.equals("oval") || flagShape.equals("rect") || flagShape.equals("line")) {
                    counter++;
                    System.out.println("first");

                    if (flagShape.equals("oval")) {

                        oval = new Oval();
                        oval.x1 = e.getX();
                        oval.y1 = e.getY();
                        oval.colorShape = colorState;
                        oval.order = counter;
                        if (checkBox1.getState() == false) {
                            oval.isFill = "false";

                        } else {
                            oval.isFill = "true";
                        }
                        listOval.add(oval);
                        listShapes.add(oval);

                    }
                    if (flagShape.equals("line")) {

                        line = new Line();
                        line.x1 = e.getX();
                        line.y1 = e.getY();
                        line.colorShape = colorState;
                        line.order = counter;
                        listLine.add(line);
                        listShapes.add(line);

                    }
                    if (flagShape.equals("rect")) {

                        rect = new Rect();
                        rect.x1 = e.getX();
                        rect.y1 = e.getY();
                        rect.colorShape = colorState;
                        rect.order = counter;
                        if (checkBox1.getState() == false) {
                            rect.isFill = "false";

                        } else {
                            rect.isFill = "true";

                        }
                        listRect.add(rect);
                        listShapes.add(rect);

                    }

                } else if (flagShape.equals("rectDel")) {

                    counter++;

                    rect = new Rect();
                    rect.x1 = e.getX();
                    rect.y1 = e.getY();
                    rect.w = 50;
                    rect.h = 50;
                    rect.isFill = "true";
                    rect.colorShape = Color.WHITE;
                    rect.order = counter;
                    listRect.add(rect);
                    listShapes.add(rect);
                } else if (flagShape.equals("lineFree")) {
                                      

                     line = new Line();
                    line.x1 = line.x2 = e.getX();
                    line.y1 = line.y2 = e.getY();
                    line.colorShape = colorState;
                    line.order = counter;
                    listLine.add(line);
                    listShapes.add(line);
                }
                
               

//                } catch (NullPointerException ex) {
//                    System.out.println("you should chose shape to draw");
//                }
            
        } catch (NullPointerException x){
                    System.out.println("tou should choose shape to draw");
        }
            
            
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        }
        );
        this.addMouseMotionListener(new MouseMotionListener() {
           
          
            @Override
            public void mouseDragged(MouseEvent e) {
                
                 try{
             if (flagShape.equals("oval")) {
                    oval.w = e.getX();
                    oval.h = e.getY();

                }
                if (flagShape.equals("rect")) {
                    rect.w = e.getX();
                    rect.h = e.getY();

                }
                if (flagShape.equals("line")) {
                    line.x2 = e.getX();
                    line.y2 = e.getY();

                }
                if (flagShape.equals("rectDel")) {

                    //counter++;
                    rect = new Rect();
                    rect.x1 = e.getX();
                    rect.y1 = e.getY();
                    rect.w = 50;
                    rect.h = 50;
                    rect.isFill = "true";
                    rect.colorShape = Color.WHITE;
                    rect.order = counter;
                    listRect.add(rect);
                    listShapes.add(rect);

                }
                if (flagShape.equals("lineFree")) {
                       counter++;
                    line = new Line();
                    line.x1 = line.x2 = e.getX();
                    line.y1 = line.y2 = e.getY();
                    line.colorShape = colorState;
                    line.order = counter;
                    listLine.add(line);
                    listShapes.add(line);
                }

                repaint();
                
             } catch (NullPointerException x){
                    System.out.println("yu should choose shape to draw");
        }
               
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        }
        );

        // TODO start asynchronous download of heavy resources
    }
    int gg = 0;

    public void paint(Graphics g) {

        Line currentLine;
        Oval currentOval;
        Rect currentRect;

        int i = 0;

        for (int o = 1; o <= counter; o++) {

            for (i = 0; i < listOval.size(); i++) {

                g.setColor(Color.BLACK);
                currentOval = (Oval) (listOval.get(i));
                if (currentOval.order == o) {
                    g.setColor(currentOval.colorShape);
                    if (currentOval.isFill.equals("true")) {
                        g.fillOval(currentOval.x1, currentOval.y1, currentOval.w, currentOval.h);
                    } else {
                        g.drawOval(currentOval.x1, currentOval.y1, currentOval.w, currentOval.h);
                    }

                }

            }

            for (i = 0; i < listLine.size(); i++) {
                g.setColor(Color.BLACK);
                currentLine = (Line) (listLine.get(i));
                if (currentLine.order == o) {
                    g.setColor(currentLine.colorShape);

                    g.drawLine(currentLine.x1, currentLine.y1, currentLine.x2, currentLine.y2);

                }

            }

            for (i = 0; i < listRect.size(); i++) {
                g.setColor(Color.BLACK);
                currentRect = (Rect) (listRect.get(i));
                if (currentRect.order == o) {

                    g.setColor(currentRect.colorShape);

                    if (currentRect.isFill.equals("false")) {

                        g.drawRect(currentRect.x1, currentRect.y1, currentRect.w, currentRect.h);

                    } else {

                        g.fillRect(currentRect.x1, currentRect.y1, currentRect.w, currentRect.h);

                    }

                }

            }
        }

//System.out.println(listRect.size());
    }

}

    // TODO overwrite start(), stop() and destroy() methods


package org.gamelib.core.utils;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {

    private int width, height;
    private int cellSize;
    private Color lineColor = Color.GRAY,
                  cellColor = Color.BLACK,
                  fillColor = Color.DARKGRAY;

    public Grid(int width, int height, int cellSize){
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }



    public void render(GraphicsContext g){
        g.setFill(fillColor);
        g.fillRect(0, 0, width, height);
        g.setStroke(lineColor);
        if (cellSize > 4) {
            for (int i = 0; i < width; i += cellSize) {
                g.strokeLine(i, 0, i, height);
            }

            for (int i = 0; i < height; i += cellSize) {
                g.strokeLine(0, i, width, i);

            }
        }
    }

    public void drawOnGrid(GraphicsContext g, int x, int y, Color color){
        g.setFill(color);
        if(cellSize > 2)
            g.fillRect(x * cellSize + 1, y * cellSize + 1, cellSize - 2, cellSize - 2);

        if (cellSize <= 2)
            g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    public int getGridSize(){
        return (width * height) / cellSize * cellSize;
    }

    public int getGridWidth(){
        return width / cellSize;
    }

    public int getGridHeight(){
        return height / cellSize;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Color getCellColor() {
        return cellColor;
    }

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public int getCellSize() {
        return cellSize;
    }
}
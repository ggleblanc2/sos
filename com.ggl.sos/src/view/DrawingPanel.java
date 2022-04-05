package com.ggl.sos.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import com.ggl.sos.controller.GameboardListener;
import com.ggl.sos.model.Coordinate;
import com.ggl.sos.model.LineSegment;
import com.ggl.sos.model.SOSModel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final int gridWidth;
	private int margin;
	
	private Rectangle[][] cells;
	
	private final SOSFrame view;
	
	private final SOSModel model;

	public DrawingPanel(SOSFrame view, SOSModel model) {
		this.view = view;
		this.model = model;
		this.gridWidth = 32;
		
		int margin = 20;
		int gridCells = model.getMaximumWidth();
		int width = gridCells * gridWidth + 2 * margin;
		this.setPreferredSize(new Dimension(width, width));
		
		GameboardListener listener = new GameboardListener(view, model);
		this.addMouseListener(listener);
		calculateCells();
	}
	
	public void calculateCells() {
		int gridCells = model.getGameboardWidth();
		int width = gridCells * gridWidth;
		margin = (getPreferredSize().width - width) / 2;
		cells = new Rectangle[gridCells][gridCells];
		
		int x = margin;
		int y = margin;
		for (int row = 0; row < gridCells; row++) {
			for (int column = 0; column < gridCells; column++) {
				cells[row][column] = new Rectangle(x, y, gridWidth, gridWidth);
				x += gridWidth;
			}
			x = margin;
			y += gridWidth;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		g2d.setStroke(new BasicStroke(3f));
		g2d.setColor(Color.BLACK);
		g2d.setFont(AppFonts.generateGridFont());
		char[][] gameboard = model.getGameboard();
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[row].length; column++) {
				Rectangle r = cells[row][column];
				g2d.drawRect(r.x, r.y, r.width, r.height);
				
				String text = Character.toString(gameboard[row][column]);
				FontMetrics fm = g2d.getFontMetrics();
		        Rectangle2D r2d = fm.getStringBounds(text, g2d);
		        int x = (r.width - (int) r2d.getWidth()) / 2 + r.x;
		        int y = (r.height - (int) r2d.getHeight()) / 2 + fm.getAscent() + r.y;
		        g.drawString(text, x, y);
			}
		}
		
		List<LineSegment> lineSegments = model.getLineSegments();
		for (LineSegment segment : lineSegments) {
			Coordinate start = segment.getStartCoordinate();
			Coordinate end = segment.getEndCoordinate();
			Rectangle sr = cells[start.getRow()][start.getColumn()];
			Rectangle er = cells[end.getRow()][end.getColumn()];
			int spx = sr.x + sr.width / 2;
			int spy = sr.y + sr.height / 2;
			int epx = er.x + er.width / 2;
			int epy = er.y + er.height / 2;
			g2d.setColor(segment.getLineColor());
			g2d.drawLine(spx, spy, epx, epy);
		}
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getMargin() {
		return margin;
	}

	public Rectangle[][] getCells() {
		return cells;
	}
	
}

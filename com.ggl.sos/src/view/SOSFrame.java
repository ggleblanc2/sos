package com.ggl.sos.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ggl.sos.model.SOSModel;

public class SOSFrame {
	
	private final ControlPanel controlPanel;
	
	private final DrawingPanel drawingPanel;
	
	private final JFrame frame;
	
	private final SOSModel model;

	public SOSFrame(SOSModel model) {
		this.model = model;
		this.controlPanel = new ControlPanel(this, model);
		this.drawingPanel = new DrawingPanel(this, model);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("SOS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.add(drawingPanel, BorderLayout.CENTER);
		frame.add(controlPanel.getPanel(), BorderLayout.EAST);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}
	
	public void shutdown() {
		frame.dispose();
		System.exit(0);
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public void updateScore() {
		controlPanel.updateScore();
	}
	
	public void updatePlayersTurn() {
		controlPanel.updatePlayersTurn();
	}
	
	public int getGridWidth() {
		return drawingPanel.getGridWidth();
	}

	public int getMargin() {
		return drawingPanel.getMargin();
	}
	
	public void calculateCells() {
		drawingPanel.calculateCells();
	}

	public void repaint() {
		drawingPanel.repaint();
	}
	
}

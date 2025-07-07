/*
 * Basic Discrete Fourier Transform (DFT) calculating application for Java Runtime.
 * Version 1.2
 *
 * Author: Rafael Sabe
 * Email: rafaelmsabe@gmail.com
 */

import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;

public class Main
{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int DFT_BUFFER_SIZE = 128;
	public static final Color DFTGRAPH_BKCOLOR = Color.BLACK;

	public static JFrame appWindow = new JFrame();

	public static DFT dft = new DFT(DFT_BUFFER_SIZE);
	public static Canvas dftGraph = new DFTGraph(WIDTH, HEIGHT, dft);

	public static void main(String[] args)
	{
		loadInput();
		dft.runCalc();
		dftGraph.setBackground(DFTGRAPH_BKCOLOR);

		appWindow.setTitle("JRE DFT v1.2");
		appWindow.setSize(WIDTH, HEIGHT);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.add(dftGraph);
		appWindow.setVisible(true);
	}

	public static void loadInput()
	{
		double[] input = null;
		double x = 0.0;
		double xStep = 0.0;

		int nIndex = 0;
		int nStop = 0;

		input = dft.getBufferInput();
		nStop = input.length;
		xStep = dft.getXStep();

		x = 0.0;
		for(nIndex = 0; nIndex < nStop; nIndex++)
		{
			/*Just a basic input wave with some in-phase harmonics and some out-of-phase harmonics. Feel free to change it.*/

			input[nIndex] = Math.sin(x) + Math.sin(3.0*x)/3.0 + Math.sin(5.0*x)/5.0 + Math.sin(7.0*x)/7.0 + Math.sin(2.0*(x + 2.14))/4.0 + Math.sin(4.0*(x + 4.22))/8.0;
			x += xStep;
		}
	}
}


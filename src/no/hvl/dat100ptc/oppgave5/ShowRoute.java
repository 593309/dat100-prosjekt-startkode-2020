package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 700;
	private static int MAPYSIZE = 500;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		double maxlat=GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat=GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		double ystep = MAPYSIZE /(Math.abs(maxlat-minlat));
		return ystep;
	}

	public void showRouteMap(int ybase) {

	int x = MARGIN,y = 0;
	
	double xstep = xstep();
	double ystep = ystep();
	double minx = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
	double miny = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
	
	int prevx=-1, prevy=-1;	
		
		for (int i = 0; i < gpspoints.length; i++) {
			final GPSPoint p = gpspoints[i];
			double px = p.getLongitude();
			double py = p.getLatitude();
			x = (int)((px-minx)*xstep) + MARGIN;
			y = (int)(ybase - (py-miny)*ystep);
			if (prevx == -1) {
				prevx = x;
				prevy = y;
			}
			drawLine(prevx,prevy, x, y);
			setColor(64, 128, 64);
			fillCircle(x, y, 3);
			prevx = x;
			prevy = y;
		}
	}

	public void showStatistics() {

		double[] speed =  gpscomputer.speeds();
		double topfart= GPSUtils.findMax(speed);
		double avg = 0; 
		for(double s: speed)
			avg+=s;
		avg/=speed.length;
		double l= gpscomputer.totalKcal(70);
		
		
		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		drawString("Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime()), MARGIN, MARGIN/2);
		drawString("Total distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance()) + " km", MARGIN, MARGIN/2+TEXTDISTANCE);
		drawString("Total elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", MARGIN, MARGIN/2+2*TEXTDISTANCE);
		drawString("Max speed      :" + GPSUtils.formatDouble(topfart)+ " km/t", MARGIN, MARGIN/2+3*TEXTDISTANCE);
		drawString("Average speed  :" + GPSUtils.formatDouble(avg)+ " km/t", MARGIN, MARGIN/2+4*TEXTDISTANCE);
		drawString("Energy         :" + GPSUtils.formatDouble(l) + " kcal", MARGIN, MARGIN/2+5*TEXTDISTANCE);
		
	
	}

}

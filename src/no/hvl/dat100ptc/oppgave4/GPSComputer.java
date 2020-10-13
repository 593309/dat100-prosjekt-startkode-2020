package no.hvl.dat100ptc.oppgave4;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

        double total = 0;
        for (int i = 0; i < gpspoints.length-1;i++) {
        double x = GPSUtils.distance(gpspoints[i],gpspoints[i+1] );
        total = total+x;

        }

        return total;

  

    }
	public double totalElevation() {

        double elevation = 0;



       for(int i = 0; i < gpspoints.length-1;) {
           double elev1 = gpspoints[i].getElevation();
           double elev2 = gpspoints[i+1].getElevation();
           if (Math.max(elev2, elev1) == elev2) {
               elevation = elevation + elev2-elev1;
               i++;
           } else {
              i++;
           }
       }

       return elevation;




    }

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

        int secs = gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();

        return secs;

    }
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double [] speeds = {};
		
	for(int i = 0; i <= gpspoints.length; i++) {
		
		int secs = gpspoints[i].getTime() - gpspoints[i-1].getTime();
		double dist = GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
		
		double speed = dist/secs ;
		
		double speedkm = speed * 3.6;
		double[] speeds1 = {speedkm};
		speeds1 = speeds;
		
		
		
	}   return  speeds;
	       
	 }
        


		// TODO - SLUTT

	
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		
		// TODO - START
		
		for (int i = 1; i< speeds().length; i++) {
			if (speeds()[i]> maxspeed)
				maxspeed = speeds()[i];
		}
		
		
		return maxspeed;
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
         int secs = totalTime();
         
        double dist = totalDistance();
		
		double speed = dist/secs; //m/s
		
		speed *= 3.6; //km/t
		
		return speed;
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		
		// Definere met ut ifra farten:
		
		if (speedmph<10) {
        	met = 4.0;
        }
        else if (speedmph>=10 && speedmph<12) {
        	met = 6.0;
        }
        
        else if (speedmph>=12 && speedmph<14) {
        	met = 8.0;
        }
        
        else if (speedmph>=14 && speedmph<16) {
        	met = 10.0;
        }
        
        else if (speedmph>=16 && speedmph<20) {
        	met = 12.0;
        }
        
        else if (speedmph>=20) {
        	met = 16.0;
        }
        
		double h = secs/3600;

		// TODO - START
        
		
		kcal = met * weight * h;
		
		System.out.println(kcal);
		return kcal;

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
        double met = 0;
		// TODO - START
		
		totalkcal = met * weight * totalTime(); 
		
		return totalkcal;
				
				
		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		
		System.out.println("Total time : " + totalTime());
		System.out.println("Total distance : " + totalDistance());
		System.out.println("Total elevation : " + totalElevation());
		System.out.println("Max speed : " + maxSpeed());
		System.out.println("Average Speed : " + averageSpeed());
		System.out.println("Energy : " + totalKcal(WEIGHT));
		
		
		
		// TODO - SLUTT
		System.out.println("==============================================");
	}

}

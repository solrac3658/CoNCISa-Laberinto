package ve.ucv.ciens.cicore.concisa.control;

import lejos.nxt.Motor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.DifferentialPilot;
import ve.ucv.ciens.cicore.concisa.subsumption.SolveLabyrinthBehavior;
import ve.ucv.ciens.cicore.concisa.utils.CompassAbstraction;

@SuppressWarnings("deprecation")
public class LabyrinthPilot {
	public static final float WHEEL_DIAMETER_MM = 56.0f;
	public static final float TRACK_WIDTH_MM = 60.0f;
	public static final float TRAVEL_DISTANCE_MM = 300.0f;
	public float Grados;
	
	public enum Orientation {
		NORTH, SOUTH, EAST, WEST, INVALID
	}
	
	//private CompassPilot m_cpPilot;
	
	private DifferentialPilot pilot;
	private CompassAbstraction m_caCompass;
	//private CompassHTSensor m_csCompass;
	private Orientation m_eoOrientation;
	
	public LabyrinthPilot(CompassHTSensor compass) {
		/*m_cpPilot = new CompassPilot(compass, WHEEL_DIAMETER_MM, TRACK_WIDTH_MM, Motor.C, Motor.A);
		m_cpPilot.setRotateSpeed(20);
		m_cpPilot.setTravelSpeed(600)*/;
		m_caCompass = new CompassAbstraction(compass);
		pilot = new DifferentialPilot(WHEEL_DIAMETER_MM, TRACK_WIDTH_MM, Motor.C, Motor.A);
		pilot.setAcceleration(3);
		pilot.setTravelSpeed(150.0f);
		pilot.setRotateSpeed(20);
		//m_csCompass= compass;
		
	}
	
	/*public void calibrate() {
		//m_cpPilot.calibrate();
	}*/
	
	public void setNorth() {
		//m_cpPilot.resetCartesianZero();
		m_caCompass.setNorth();
	}

	public void Mover(Orientation target) {
		switch (target) {
		case NORTH:
			//m_cpPilot.rotate(0, false);
			Grados =(m_caCompass.getDegreesCartesian());
			System.out.println(Grados);
			while(Math.abs(Grados)>5) {
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian());
			}
			pilot.travel(-300);
			SolveLabyrinthBehavior.FN++;
			break;
		case WEST:
			//m_cpPilot.rotate(90, false);
			//m_cpPilot.travel(TRAVEL_DISTANCE_MM);
			Grados =(m_caCompass.getDegreesCartesian()-90);
			System.out.println(Grados);
			if (Grados<-250) {
				Grados = Grados+360;
			}
			
			while(Math.abs(Grados)>5) {
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian()-90);
			}
			
			pilot.travel(-300);
			SolveLabyrinthBehavior.FO++;
			break;
		case SOUTH:
			//m_cpPilot.rotate(180, false);
			//m_cpPilot.travel(TRAVEL_DISTANCE_MM);
			Grados =(m_caCompass.getDegreesCartesian()-180);
			System.out.println(Grados);
			while(Math.abs(Grados)>5) {
				if (Grados<-250) {
					Grados = Grados+360;
				}
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian()-180);
			}
			pilot.travel(-300);
			SolveLabyrinthBehavior.FN--;
			break;
		case EAST:
			//m_cpPilot.rotate(270, false);
			//m_cpPilot.travel(TRAVEL_DISTANCE_MM);
			Grados =(m_caCompass.getDegreesCartesian()+90);
			System.out.println(Grados);
			if (Grados>250) {
				Grados = Grados-360;
			}
			while(Math.abs(Grados)>5) {
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian()+90);
			}
			pilot.travel(-300);
			SolveLabyrinthBehavior.FO--;
			break;
		case INVALID: /* Fall through */
		default:
			System.out.println("Inv. Heading");
			break;
		}
		
	}
	
	public void Regresar(Orientation direction, char Corregir) throws Exception {
		if (direction == Orientation.INVALID)
			throw new Exception("exPilRev: INV");
		
		pilot.travel(300);
		
		switch (direction) {
		case NORTH:
			SolveLabyrinthBehavior.FN--;
			break;
		case WEST:
			SolveLabyrinthBehavior.FO--;
			break;
		case SOUTH:
			SolveLabyrinthBehavior.FN++;
			break;
		case EAST:
			SolveLabyrinthBehavior.FO++;
			break;
		case INVALID: /* Fall through */
		default:
			System.out.println("Inv. Heading");
			break;
		}
		switch (Corregir) {
		case 'N':
			Grados =(m_caCompass.getDegreesCartesian());
			System.out.println(Grados);
			while(Math.abs(Grados)>5) {
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian());
			}
			break;
		case 'O':
			Grados =(m_caCompass.getDegreesCartesian()-90);
			System.out.println(Grados);
			if (Grados<-250) {
				Grados = Grados+360;
			}
			
			while(Math.abs(Grados)>5) {
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian()-90);
			}
			break;
		case 'S':
			//m_cpPilot.rotate(180, false);
			//m_cpPilot.travel(TRAVEL_DISTANCE_MM);
			Grados =(m_caCompass.getDegreesCartesian()-180);
			System.out.println(Grados);
			while(Math.abs(Grados)>5) {
				if (Grados<-250) {
					Grados = Grados+360;
				}
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian()-180);
			}
			break;
		case 'E':
			Grados =(m_caCompass.getDegreesCartesian()+90);
			System.out.println(Grados);
			if (Grados>250) {
				Grados = Grados-360;
			}
			while(Math.abs(Grados)>5) {
				System.out.println(Grados);
				//Button.waitForAnyPress();
				pilot.rotate(Grados);
				Grados =(m_caCompass.getDegreesCartesian()+90);
			}
			break;
		}
		
		
	}
	
	private String orientationToString(Orientation o) {
		String s = "NONE";
		switch (o) {
		case EAST:
			s = "EAST";
			break;
		case INVALID:
			s = "INVALID";
			break;
		case NORTH:
			s = "NORTH";
			break;
		case SOUTH:
			s = "SOUTH";
			break;
		case WEST:
			s = "WEST";
			break;
		}
		return s;
	}
	
}

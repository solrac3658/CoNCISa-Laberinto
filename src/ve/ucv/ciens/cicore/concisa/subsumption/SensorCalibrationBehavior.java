package ve.ucv.ciens.cicore.concisa.subsumption;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.navigation.DifferentialPilot;
import ve.ucv.ciens.cicore.concisa.control.LabyrinthPilot;

public class SensorCalibrationBehavior extends BaseBehavior {
	private boolean sensorsCalibrated;

	public SensorCalibrationBehavior(UltrasonicSensor front, UltrasonicSensor right, UltrasonicSensor left, CompassHTSensor compass) {
		super(front, right, left, compass);
		sensorsCalibrated = false;
	}

	@Override
	public boolean takeControl() {
		return !sensorsCalibrated;
	}

	@Override
	public void action() {
		System.out.println("To calibrate press enter");
		Button.ENTER.waitForPress();
		
		/* Calibrate the compass by turning slowly by 720 degrees. */
		System.out.println("Calib. compass");
		DifferentialPilot p = new DifferentialPilot(LabyrinthPilot.WHEEL_DIAMETER_MM, LabyrinthPilot.TRACK_WIDTH_MM, Motor.C, Motor.A);
		p.setRotateSpeed(20);
		m_csCompass.startCalibration();
		p.rotate(855, false);
		m_csCompass.stopCalibration();
		
		System.out.println("Face north and press enter");
		Button.ENTER.waitForPress();
		m_csCompass.resetCartesianZero();

		sensorsCalibrated = true;
		
		System.out.println("Calib. ready.");
		Button.waitForAnyPress();
		
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
			System.out.println(3 - i);
		}
	}

	@Override
	public void suppress() { }
}

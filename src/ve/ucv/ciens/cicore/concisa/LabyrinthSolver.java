package ve.ucv.ciens.cicore.concisa;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import ve.ucv.ciens.cicore.concisa.subsumption.SensorCalibrationBehavior;
import ve.ucv.ciens.cicore.concisa.subsumption.SolveLabyrinthBehavior;

public class LabyrinthSolver {
	private UltrasonicSensor m_usFront;
	private UltrasonicSensor m_usRight;
	private UltrasonicSensor m_usLeft;
	private static final CompassHTSensor m_csCompass = new CompassHTSensor(SensorPort.S3);
	
	
	private Behavior[] m_aBehaviors;
	private Arbitrator m_saArbitrator;
	
	public LabyrinthSolver() {
		m_usFront = new UltrasonicSensor(SensorPort.S2);
		m_usRight = new UltrasonicSensor(SensorPort.S1);
		m_usLeft  = new UltrasonicSensor(SensorPort.S4);
		// m_csCompass = new CompassHTSensor(SensorPort.S3);
		
		m_usFront.setMode(UltrasonicSensor.MODE_PING);
		m_usRight.setMode(UltrasonicSensor.MODE_PING);
		m_usLeft.setMode(UltrasonicSensor.MODE_PING);
		
		m_aBehaviors = new Behavior[2];
		m_aBehaviors[0] = new SolveLabyrinthBehavior(m_usFront, m_usRight, m_usLeft, m_csCompass);
		m_aBehaviors[1] = new SensorCalibrationBehavior(m_usFront, m_usRight, m_usLeft, m_csCompass);
		
		m_saArbitrator = new Arbitrator(m_aBehaviors, true);
	}
	
	public void start() {
		m_saArbitrator.start();
	}
}

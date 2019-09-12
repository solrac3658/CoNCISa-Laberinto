package ve.ucv.ciens.cicore.concisa.subsumption;

import java.util.Stack;

import lejos.nxt.Button;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.CompassHTSensor;
import ve.ucv.ciens.cicore.concisa.control.LabyrinthPilot;
import ve.ucv.ciens.cicore.concisa.control.UltraSoundChecker;


public class SolveLabyrinthBehavior extends BaseBehavior {
	private UltraSoundChecker m_scSensors;
	private LabyrinthPilot m_lpMonkey;
	private Stack<Casilla> Pila;
	private byte PosX;
	private byte PosY;
	public static int FN;
	public static int FO;
	public char R_orientacion;
	public LabyrinthPilot.Orientation or; 

	public SolveLabyrinthBehavior(UltrasonicSensor front, UltrasonicSensor right, UltrasonicSensor left, CompassHTSensor compass) {
		super(front, right, left, compass);
		m_scSensors = new UltraSoundChecker(front, right, left);
		m_lpMonkey = new LabyrinthPilot(compass);
		Pila = new Stack<Casilla>();
		PosX = 1;
		PosY = 1;
		FN = 0;
		FO = 0;
		R_orientacion='0';
		or = LabyrinthPilot.Orientation.INVALID;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		/*try {
			//System.out.println("Frente " + m_scSensors.Frente());
			//System.out.println("Izquierda " + m_scSensors.Izquierda());
			//System.out.println("Derecha " + m_scSensors.Derecha());
			System.out.println(m_caCompass.getDegreesCartesian());
		} catch (Exception e) {	
			e.printStackTrace();
		}*/
		try {
			char Orientacion = 'N';
			Casilla Actual = new Casilla(m_scSensors.Frente(), m_scSensors.Izquierda(), m_scSensors.Derecha(), false, PosX, PosY, Orientacion);
			Casilla Siguiente = new Casilla(false, false, false, false, (byte)0, (byte)0, 'N');
			Pila.push(Actual);

			while (FN != 9 || FO != 4) {


				Actual = Pila.peek();

				System.out.println(Actual.Pmovimiento[0]);
				System.out.println(Actual.Pmovimiento[1]);
				System.out.println(Actual.Pmovimiento[2]);
				System.out.println(Actual.Pmovimiento[3]);
				System.out.println(Actual.Orientacion);
				Button.waitForAnyPress();
				if (Actual.Pmovimiento[0]) {
					m_lpMonkey.Mover(LabyrinthPilot.Orientation.NORTH);
					Orientacion = 'N';
					Pila.peek().Pmovimiento[0] = false;
//					Siguiente.Pmovimiento[0] = m_scSensors.Frente();
//					Siguiente.Pmovimiento[1] = m_scSensors.Izquierda();
//					Siguiente.Pmovimiento[2] = m_scSensors.Derecha();
//					Siguiente.Pmovimiento[3] = false;
//					Siguiente.PosXCa = PosX;
//					Siguiente.PosYca = PosY;
//					Siguiente.Orientacion = Orientacion;
					Siguiente = new Casilla(m_scSensors.Frente(), m_scSensors.Izquierda(), m_scSensors.Derecha(), false, PosX, PosY, Orientacion);
					Pila.push(Siguiente);
				} else {
					if (Actual.Pmovimiento[1]) {
						m_lpMonkey.Mover(LabyrinthPilot.Orientation.WEST);
						Orientacion = 'O';
						Pila.peek().Pmovimiento[1] = false;
//						Siguiente.Pmovimiento[0] = m_scSensors.Derecha();
//						Siguiente.Pmovimiento[1] = m_scSensors.Frente();
//						Siguiente.Pmovimiento[2] = false;
//						Siguiente.Pmovimiento[3] = m_scSensors.Izquierda();
//						Siguiente.PosXCa = PosX;
//						Siguiente.PosYca = PosY;
//						Siguiente.Orientacion = Orientacion;
						Siguiente = new Casilla(m_scSensors.Derecha(), m_scSensors.Frente(), false, m_scSensors.Izquierda(), PosX, PosY, Orientacion);
						Pila.push(Siguiente);
					} else {
						if (Actual.Pmovimiento[2]) {
							m_lpMonkey.Mover(LabyrinthPilot.Orientation.EAST);
							Orientacion = 'E';
							Pila.peek().Pmovimiento[2] = false;
//							Siguiente.Pmovimiento[0] = m_scSensors.Izquierda();
//							Siguiente.Pmovimiento[1] = false;
//							Siguiente.Pmovimiento[2] = m_scSensors.Frente();
//							Siguiente.Pmovimiento[3] = m_scSensors.Derecha();
//							Siguiente.PosXCa = PosX;
//							Siguiente.PosYca = PosY;
//							Siguiente.Orientacion = Orientacion;
							Siguiente = new Casilla(m_scSensors.Izquierda(),false, m_scSensors.Frente(), m_scSensors.Derecha(), PosX, PosY, Orientacion);
							Pila.push(Siguiente);
						} else {
							if (Actual.Pmovimiento[3]) {
								m_lpMonkey.Mover(LabyrinthPilot.Orientation.SOUTH);
								Orientacion = 'S';
								Pila.peek().Pmovimiento[3] = false;
//								Siguiente.Pmovimiento[0] = false;
//								Siguiente.Pmovimiento[1] = m_scSensors.Derecha();
//								Siguiente.Pmovimiento[2] = m_scSensors.Izquierda();
//								Siguiente.Pmovimiento[3] = m_scSensors.Frente();
//								Siguiente.PosXCa = PosX;
//								Siguiente.PosYca = PosY;
//								Siguiente.Orientacion = Orientacion;
								Siguiente = new Casilla(false,m_scSensors.Derecha(), m_scSensors.Izquierda(), m_scSensors.Frente(), PosX, PosY, Orientacion);
								Pila.push(Siguiente);
							} else {
								Orientacion = Actual.Orientacion;
								System.out.println(Pila.size());
								Actual = Pila.pop();
								//Actual = Pila.pop();
								System.out.println(Pila.size());
								R_orientacion = Pila.peek().Orientacion;

								if (Orientacion == 'N') or = LabyrinthPilot.Orientation.NORTH;
								else if (Orientacion == 'E') or = LabyrinthPilot.Orientation.EAST;
								else if (Orientacion == 'O') or = LabyrinthPilot.Orientation.WEST;
								else if (Orientacion == 'S') or = LabyrinthPilot.Orientation.SOUTH;
								System.out.println("AQUI" + R_orientacion);
								m_lpMonkey.Regresar(or, R_orientacion);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Button.waitForAnyPress();
			System.exit(1);
		} 
	}

	@Override
	public void suppress() {
		/* Nothing */
	}

	class Casilla {
		public boolean Pmovimiento[];
		public byte PosXCa, PosYca;
		public char Orientacion;

		public Casilla(boolean norte, boolean oeste, boolean este, boolean sur, byte x, byte y, char direccion) {
			Pmovimiento = new boolean[4];

			Pmovimiento[0] = norte;
			Pmovimiento[1] = oeste;
			Pmovimiento[2] = este;
			Pmovimiento[3] = sur;
			PosXCa = x;
			PosYca = y;
			Orientacion = direccion;
		}
	}
}

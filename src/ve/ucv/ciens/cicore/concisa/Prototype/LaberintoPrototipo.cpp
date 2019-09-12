#include <iostream>
#include <stdlib.h>
#include <stack> 
#include <stdio.h> 
using namespace std;

	class Casilla{
	    public:
	   	bool Pmovimiento[4];
	    int PosXCa,PosYca;
	    char Orientacion;
	    
	    Casilla (bool norte, bool oeste, bool este, bool sur, int x, int y, char direccion){
	         
	         Pmovimiento[0] = norte;
	         Pmovimiento[1] = oeste;
	         Pmovimiento[2] = este;
	         Pmovimiento[3] = sur;
	         PosXCa = x;
	         PosYca = y;
	         Orientacion = direccion;
	    }
  	};	
	
  	stack <Casilla> Pila;	

	int PosX = 1;
	int PosY = 1;
	int FN =0;
	int FO =0;
	char laberinto [21][11] = { {'0','0','0','0','0','0','0','0','0','0','0'},
								{'0',' ','0',' ','0',' ',' ',' ','0',' ','0'},//1
								{'0',' ','0',' ','0',' ','0','0','0',' ','0'},//1
								{'0',' ','0',' ','0',' ','0',' ',' ',' ','0'},//2																
								{'0',' ','0',' ','0',' ','0',' ','0','0','0'},//2							
								{'0',' ',' ',' ',' ',' ','0',' ',' ',' ','0'},//3
								{'0',' ','0','0','0','0','0',' ','0','0','0'},//3
								{'0',' ','0',' ',' ',' ',' ',' ',' ',' ','0'},//4
								{'0',' ','0',' ',' ',' ','0','0','0','0','0'},//4
								{'0',' ','0',' ','0',' ',' ',' ',' ',' ','0'},//5
								{'0',' ','0',' ','0','0','0','0','0',' ','0'},//5
								{'0',' ',' ',' ','0',' ','0',' ','0',' ','0'},//6
								{'0','0','0',' ','0',' ','0',' ','0',' ','0'},//6
								{'0',' ',' ',' ',' ',' ',' ',' ','0',' ','0'},//7
								{'0',' ','0','0','0',' ','0','0','0','0','0'},//7
								{'0',' ','0',' ','0',' ','0',' ',' ',' ','0'},//8
								{'0',' ','0',' ','0','0','0',' ','0',' ','0'},//8
								{'0',' ','0',' ',' ',' ',' ',' ','0',' ','0'},//9
								{'0',' ','0',' ','0','0','0','0','0','0','0'},//9
								{'0',' ',' ',' ',' ',' ',' ',' ',' ',' ','0'},//10
								{'0','0','0','0','0','0','0','0','0','0','0'}
							};

	void Imprimir() {

		for (int i=0; i<21; i++){
			for (int j = 0; j < 11; j++)
			{
				cout << laberinto[i][j] << " ";
			}
			cout << "\n";
		}
	}

	bool Frente(char Or){
		if(Or == 'N'){
			if(laberinto[PosX+1][PosY]==' '){
				return true;
			} else {
				return false;
			}
		} else {
			if(Or == 'O'){
				if(laberinto[PosX][PosY+1]==' '){
					return true;
				}else{
					return false;
				}

			} else{
				if (Or=='E'){
					if(laberinto[PosX][PosY-1]==' '){
					return true;
				}else{
					return false;
				}

				}else{
					if(Or=='S'){
						if(laberinto[PosX-1][PosY]==' '){
				return true;
			} else {
				return false;
			}

					}
				}
			}
		}
	}

	bool Izquierda (char Or){
		if(Or == 'N'){
			if (laberinto[PosX][PosY+1]==' ')
			{
				return true;
			}else {
				return false;
			}
		} else {
			if(Or == 'O'){
				if(laberinto[PosX-1][PosY]==' '){
					return true;
				}else{
					return false;
				}

			} else{
				if (Or=='E'){
					if(laberinto[PosX+1][PosY]==' '){
					return true;
				}else{
					return false;
				}

				}else{
					if(Or=='S'){
						if(laberinto[PosX][PosY-1]==' '){
				return true;
			} else {
				return false;
			}

					}
				}
			}
		}
	}



	bool Derecha (char Or){
		if(Or == 'N'){
			if(laberinto[PosX][PosY-1]==' '){
				return true;
			}else {
				return false;
			}
		} else {
			if(Or == 'O'){
				if(laberinto[PosX+1][PosY]==' '){
					return true;
				}else{
					return false;
				}

			} else{
				if (Or=='E'){
					if(laberinto[PosX-1][PosY]==' '){
					return true;
				}else{
					return false;
				}

				}else{
					if(Or=='S'){
						if(laberinto[PosX][PosY+1]==' '){
				return true;
			} else {
				return false;
			}

					}
				}
			}
		}
	}

void Mover(char Or){
		if (Or == 'N'){
			PosX +=2;
			laberinto[PosX][PosY]= 'V';
			FN++;
		}else {
			if(Or == 'O'){
				PosY +=2;
				laberinto[PosX][PosY]= '>';
				FO++;
			}else{
				if(Or == 'E'){
					PosY -=2;
					laberinto[PosX][PosY]= '<';
					FO--;
				}else{
					if(Or == 'S'){
						PosX -=2;
						laberinto[PosX][PosY]= 'A';
						FN--;
					}
				}
			}
		}

	}

void Regresar(int x, int y, char direccion){
	PosX=x;
	PosY=y;
	laberinto[PosX][PosY]='R';
	if(direccion=='N'){
		FN--;
	}
	if(direccion=='O'){
		FO--;
	}
	if(direccion=='E'){
		FO++;
	}
	if(direccion=='S'){
		FN++;
	}
	

}


int main (){

	laberinto[PosX][PosY]='V';
	char Orientacion = 'N'; // solo puede tomar valores de N S E O
	Casilla Actual (Frente(Orientacion), Izquierda(Orientacion), Derecha(Orientacion), false, PosX, PosY, Orientacion);
	Casilla Siguiente (Frente(Orientacion), Izquierda(Orientacion), Derecha(Orientacion), false, PosX, PosY, Orientacion);
	Pila.push(Actual);
	Imprimir();
	getchar();
	cout << endl << endl;
	while (FN!=9 || FO!=4){

		Actual = Pila.top();
		
		if(Actual.Pmovimiento[0]){
			laberinto[PosX][PosY]=' ';
			Mover('N');
			Orientacion ='N';
			Pila.top().Pmovimiento[0] = false;
			Siguiente.Pmovimiento[0] = Frente(Orientacion);
			Siguiente.Pmovimiento[1] = Izquierda(Orientacion);
			Siguiente.Pmovimiento[2] = Derecha(Orientacion);
			Siguiente.Pmovimiento[3] =  false;
			Siguiente.PosXCa = PosX;
			Siguiente.PosYca = PosY;
			Siguiente.Orientacion = Orientacion;
			Pila.push(Siguiente);
		}else {
			if(Actual.Pmovimiento[1]){
				laberinto[PosX][PosY]=' ';
				Mover('O');
				Orientacion ='O';
				Pila.top().Pmovimiento[1] = false;
				Siguiente.Pmovimiento[0] = Derecha(Orientacion);
				Siguiente.Pmovimiento[1] = Frente(Orientacion);
				Siguiente.Pmovimiento[2] = false;
				Siguiente.Pmovimiento[3] = Izquierda(Orientacion);
				Siguiente.PosXCa = PosX;
				Siguiente.PosYca = PosY;
				Siguiente.Orientacion = Orientacion;
				Pila.push(Siguiente);

			} else{
				if(Actual.Pmovimiento[2]){
					laberinto[PosX][PosY]=' ';
					Mover('E');
					Orientacion ='E';
					Pila.top().Pmovimiento[2] = false;
					Siguiente.Pmovimiento[0] = Izquierda(Orientacion);
					Siguiente.Pmovimiento[1] = false;
					Siguiente.Pmovimiento[2] = Frente(Orientacion);
					Siguiente.Pmovimiento[3] = Derecha(Orientacion);
					Siguiente.PosXCa = PosX;
					Siguiente.PosYca = PosY;
					Siguiente.Orientacion = Orientacion;
					Pila.push(Siguiente);
				}else{
					if(Actual.Pmovimiento[3]){
						laberinto[PosX][PosY]=' ';
						Mover('S');
						Orientacion ='S';
						Pila.top().Pmovimiento[3] = false;
						Siguiente.Pmovimiento[0] = false;
						Siguiente.Pmovimiento[1] = Derecha(Orientacion);
						Siguiente.Pmovimiento[2] = Izquierda(Orientacion);
						Siguiente.Pmovimiento[3] = Frente(Orientacion);
						Siguiente.PosXCa = PosX;
						Siguiente.PosYca = PosY;
						Siguiente.Orientacion = Orientacion;
						Pila.push(Siguiente);
					} else {
						laberinto[Actual.PosXCa][Actual.PosYca]= ' ';
						Orientacion = Actual.Orientacion;
						Pila.pop();
						Regresar(Pila.top().PosXCa,Pila.top().PosYca, Orientacion);
							
					}
				}
			}
		}
		Imprimir();
	cout << endl << endl;
	getchar();
	}
	Imprimir();
return 0;}

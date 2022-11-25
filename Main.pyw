import sys				#Siempre incluirlo

from Trapecioo import * #Debe modificar según su caso
from PyQt5.QtWidgets import * #Siempre incluirlo

class TRAPECIOMULTIPLE(QtWidgets.QMainWindow): #Siempre incluirlo
	def __init__(self):						#Siempre incluirlo
		super().__init__()					#Siempre incluirlo
		self.ui = Ui_Dialog()				#Siempre incluirlo
		self.ui.setupUi(self)				#Siempre incluirlo

		#AQUÍ COLOCA LOS EVENTOS
		self.ui.btnintegrar.clicked.connect(self.TrapecioMultiple) #A MODO DE EJEMPLO

	def TrapecioMultiple(self):
		a=float(self.ui.TXTA.text())
		b=float(self.ui.TXTB.text())
		n=5

		h=(b-a)/n
		
		self.ui.txth.setText(str(h))

		x0=a
		x1=x0+h
		x2=x1+h
		x3=x2+h
		x4=x3+h
		x5=b

		self.ui.txtx0.setText(str(round(x0,4)))
		self.ui.txtx1.setText(str(round(x1,4)))
		self.ui.txtx2.setText(str(round(x2,4)))
		self.ui.txtx3.setText(str(round(x3,4)))
		self.ui.txtx4.setText(str(round(x4,4)))
		self.ui.txtx5.setText(str(round(x5,4)))

		

		Fx0=15**(2*(x0))
		Fx1=15**(2*(x1))
		Fx2=15**(2*(x2))
		Fx3=15**(2*(x3))
		Fx4=15**(2*(x4))
		Fx5=15**(2*(x5))

		self.ui.txtFx0.setText(str(round(Fx0,4)))
		self.ui.txtFx1.setText(str(round(Fx1,4)))
		self.ui.txtFx2.setText(str(round(Fx2,4)))
		self.ui.txtFx3.setText(str(round(Fx3,4)))
		self.ui.txtFx4.setText(str(round(Fx4,4)))
		self.ui.txtFx5.setText(str(round(Fx5,4)))
		

		I= (h/2)*(Fx0+2*(Fx1)+2*(Fx2)+2*(Fx3)+2*(Fx4)+Fx5)

		self.ui.txtintegral.setText(str(round(I,4)))
		


		#AQUÍ VAN TODOS LOS PROCESOS QUE DESENCADENA EL EVENTO


if __name__=="__main__":					#Siempre incluirlo
	app=QtWidgets.QApplication(sys.argv)	#Siempre incluirlo
	myapp=TRAPECIOMULTIPLE()					#Siempre incluirlo, intanciar clase según su caso
	myapp.show()							#Siempre incluirlo
	sys.exit(app.exec_())					#Siempre incluirlo
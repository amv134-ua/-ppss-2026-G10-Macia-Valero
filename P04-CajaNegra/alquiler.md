**-ENTRADAS**

**entrada 1 TipoCoche (C):** tipo de coche

&emsp;**clases válidas:**

&emsp;&emsp;**C1:** TipoCoche = "TURISMO"

&emsp;&emsp;**C2:** TipoCoche = "DEPORTIVO"

&emsp;**clases no validas:**

&emsp;&emsp;**NC1:** TipoCoche = null


**entrada 2-3-4 (FND):** fecha_inicio (F): de tipo LocalDate + num_dias (N): entero + Disponible (D): Booleano que define si el vehículo está disponible o no.

&emsp;**clases válidas:**

&emsp;&emsp;**FND1:** F > fecha_actual ^ N=1..2+D= true

&emsp;&emsp;**FND2:** F > fecha_actual ^ N = 3..30 + D = true

&emsp;**clases no válidas**

&emsp;&emsp;**NFND1:** F <= fecha_actual ^ N=1..30^D=true

&emsp;&emsp;**NFND2:** F = null ^ N=1..30^D=true

&emsp;&emsp;**NFND3:** F > fecha_actual ^ N < 1 ^ D = true

&emsp;&emsp;**NFND4:** F > fecha_actual ^ N>30^D= true

&emsp;&emsp;**NFND5:** F > fecha_actual ^ N=1..30^ D = false


**-SALIDAS**

&emsp;**clases válidas**

&emsp;&emsp;**S1:** importe =100*N

&emsp;&emsp;**S1:** importe =50*N

&emsp;**clases no válidas**

&emsp;&emsp;**NS1:** ReservaException, mensaje: "Fecha no correcta"

&emsp;&emsp;**NS2:** Reserva Exception, mensaje: "Reserva no Disponible"

&emsp;&emsp;**NS3:** ?


| **Particiones** | **Identificador** | **Datos de Entrada** (tipo, fecha_inicio, num_dias, disponible) | **Resultado Esperado** |
| :--- | :--- | :--- | :--- |
| C1-FND1-S1 | C1 | tipo = "TURISMO", fecha_inicio = 15/03/2026, num_dias = 2, disponibilidad = true | 200 |
| C2-FND2 - S2 | C2 | tipo = DEPORTIVO, fecha_inicio = 20/03/2026, num_dias = 10, disponibilidad = true | 500 |
| NC1-FND1 - NS3 | C3 | tipo null, fecha_inicio = 15/03/2026, num_dias = 2, disponibilidad = true | ? |
| C1-NFND1 - NS1 | C4 | tipo = TURISMO, fecha_inicio = 05/03/2026, num_dias = 2, disponibilidad = true | Reserva Exception ("Fecha no correcta") |
| C1-NFND2 - NS3 | C5 | tipo = TURISMO, fecha_inicio = null, num_dias = 2, disponibilidad = true | ? |
| C1-NFND3 - NS2 | C6 | tipo = TURISMO, fecha_inicio = 15/03/2026, num_dias = 35, disponibilidad = true | Reserva Exception ("Reserva no posible") |
| C1-NFND4 - NS3 | C7 | tipo = TURISMO, fecha_inicio = 15/03/2026, num_dias = 0, disponibilidad = true | ? |
| C1-NFND5 - NS2 | C8 | tipo = TURISMO, fecha_inicio = 15/03/2026, num_dias = 2, disponibilidad = false | Reserva Exception ("Reserva no posible") |

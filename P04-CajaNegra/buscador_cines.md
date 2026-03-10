**-ENTRADAS**

**entrada 1 cod_postal (C):** código postal de tipo String

&emsp;**clases válidas:**

&emsp;&emsp;**C1:** cod_postal = cadena de texto válida

&emsp;**clases no válidas:**

&emsp;&emsp;**NC1:** cod_postal = null


**entradas 2-3 (D):** dist_min (float) + dist_max (float)

&emsp;**clases válidas:**

&emsp;&emsp;**D1:** 0 <= dist_min <= dist_max

&emsp;**clases no válidas:**

&emsp;&emsp;**ND1:** dist_min < 0 ^ dist_max >= 0

&emsp;&emsp;**ND2:** dist_min >= 0 ^ dist_max < 0

&emsp;&emsp;**ND3:** 0 <= dist_max < dist_min


**-SALIDAS**

&emsp;**clases válidas**

&emsp;&emsp;**S1:** Mensaje.listaCines = {cines encontrados} ^ Mensaje.texto = ""

&emsp;&emsp;**S2:** Mensaje.listaCines = null ^ Mensaje.texto = "no se ha encontrado nada"

&emsp;**clases no válidas**

&emsp;&emsp;**NS1:** Mensaje.listaCines = null ^ Mensaje.texto = "cambie el rango de búsqueda"

&emsp;&emsp;**NS2:** ?


| **Particiones** | **Identificador** | **Datos de Entrada** (cod_postal, dist_min, dist_max) | **Resultado Esperado** (Mensaje.listaCines, Mensaje.texto) |
| :--- | :--- | :--- | :--- |
| C1-D1-S1 | C1 | cod_postal = "03000", dist_min = 0.0f, dist_max = 10.0f | {("Cine ABC", 5.0f)}, "" |
| C1-D1-S2 | C2 | cod_postal = "03000", dist_min = 50.0f, dist_max = 100.0f | null, "no se ha encontrado nada" |
| NC1-D1-NS2 | C3 | cod_postal = null, dist_min = 0.0f, dist_max = 10.0f | ? |
| C1-ND1-NS1 | C4 | cod_postal = "03000", dist_min = -5.0f, dist_max = 10.0f | null, "cambie el rango de búsqueda" |
| C1-ND2-NS1 | C5 | cod_postal = "03000", dist_min = 5.0f, dist_max = -10.0f | null, "cambie el rango de búsqueda" |
| C1-ND3-NS1 | C6 | cod_postal = "03000", dist_min = 20.0f, dist_max = 10.0f | null, "cambie el rango de búsqueda" |

echo off

echo  -----------------------------------------------
echo    Fase 1: Preparación entorno de ejecución
echo  -----------------------------------------------
pause

rmdir /S /Q .\uoc1
rmdir /S /Q .\doc

echo  -----------------------------------------------
echo    Clases y docs antiguos borrados
echo  -----------------------------------------------

echo  -----------------------------------------------
echo    Fase 2: Creacion de la documentacion
echo  -----------------------------------------------


echo  -----------------------------------------------
echo    Documentación generada
echo  -----------------------------------------------

echo  -----------------------------------------------
echo    Fase 3: Compilación
echo  -----------------------------------------------
echo    Beans:
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\beans\*.java 
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\utils\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\resources\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\excepciones\*.java 
echo  -----------------------------------------------
echo    DTOS:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\dtos\*.java 
echo  -----------------------------------------------
echo    DAOS:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\daos\*.java
echo  -----------------------------------------------
echo    GESTORES:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorDisco.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorEntidad.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorPais.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorContacto.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorCentroDocente.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorTipoDocumento.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorTipoRol.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorUniversidad.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorDatosBancarios.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorDocumentoIdentificacion.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorUsuario.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\GestorRMI.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\*.java 
echo  -----------------------------------------------
echo    IFACE:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\iface\*.java
echo  -----------------------------------------------
echo    PANTALLAS:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\pantallas\*.java 
echo  -----------------------------------------------
echo    SERVIDOR:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\servidor\*.java 
echo  -----------------------------------------------
echo    CLIENTE:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\cliente\*.java 


echo  -----------------------------------------------
echo    Fase 4: Generación de los stubs y skeletons
echo  -----------------------------------------------


echo Generando stubs...

rmic uoc.edu.tds.pec4.iface.RemotoImpl

echo  -----------------------------------------------
echo    Ya puede ejecutar servidor.bat!
echo  -----------------------------------------------
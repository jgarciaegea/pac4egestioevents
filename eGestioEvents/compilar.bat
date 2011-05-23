echo off

echo  -----------------------------------------------
echo    PREPARACION DEL ENTORNO DE EJECUCION
echo  -----------------------------------------------
pause
echo  -----------------------------------------------
echo    BORRAR FICHERS .CLASS
echo  -----------------------------------------------
rmdir /s bin
mkdir bin
echo  -----------------------------------------------
echo    CONFIGURACION FICHERO IDIOMAS Y PROPERTIES
echo  -----------------------------------------------
mkdir bin\i18n
copy src\i18n bin\i18n
mkdir bin\conf
copy conf bin\conf
mkdir bin\images
copy images bin\images
mkdir bin\imagen
copy imagen bin\imagen
echo  -----------------------------------------------
echo    Fase 2: Creacion de la documentacion
echo  -----------------------------------------------
echo     PENDIENTE
echo  -----------------------------------------------
echo    Fase 3: Compilación
echo  -----------------------------------------------
echo    BEANS:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\resources\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\beans\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\excepciones\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\utils\*.java 
echo  -----------------------------------------------
echo    DTOS:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\dtos\*.java 
echo  -----------------------------------------------
echo    DAOS:
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\daos\*.java
echo  -----------------------------------------------
echo    GESTORES & echo    IFACE::
echo  -----------------------------------------------
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\iface\RemoteInterface.java
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\*.java 
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
echo  -----------------------------------------------

echo  -----------------------------------------------
echo    Ya puede ejecutar La aplicacion
echo  -----------------------------------------------
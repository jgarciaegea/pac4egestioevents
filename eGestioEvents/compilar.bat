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


echo Compilando el codigo...

javac -d bin  -cp  bin src\uoc\edu\tds\pec4\beans\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\utils\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\resources\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\excepciones\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\iface\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\daos\*.java
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\dtos\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\gestores\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\pantallas\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\servidor\*.java 
javac -d bin  -cp  bin src\uoc\edu\tds\pec4\cliente\*.java 
javac src\i18n\*.java -d .

echo  -----------------------------------------------
echo    Fase 4: Generación de los stubs y skeletons
echo  -----------------------------------------------


echo Generando stubs...

rmic uoc.edu.tds.pec4.iface.RemotoImpl

echo  -----------------------------------------------
echo    Ya puede ejecutar servidor.bat!
echo  -----------------------------------------------
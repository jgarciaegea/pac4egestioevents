echo off

echo  -----------------------------------------------
echo    Fase 1: Preparaci�n entorno de ejecuci�n
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
pause

javadoc src\uoc\edu\tds\pec4\beans\*.java -d doc
javadoc src\uoc\edu\tds\pec4\utils\*.java -d doc
javadoc src\uoc\edu\tds\pec4\resources\*.java -d .
javadoc src\uoc\edu\tds\pec4\excepciones\*.java -d doc
javadoc src\uoc\edu\tds\pec4\iface\*.java -d doc
javadoc src\uoc\edu\tds\pec4\client\*.java -d doc
javadoc src\uoc\edu\tds\pec4\servidor\*.java -d doc
javadoc src\uoc\edu\tds\pec4\pantallas\*.java -d doc
javadoc src\uoc\edu\tds\pec4\daos\*.java -d doc
javadoc src\uoc\edu\tds\pec4\dtos\*.java -d doc
javadoc src\uoc\edu\tds\pec4\gestores\*.java -d doc

echo  -----------------------------------------------
echo    Documentaci�n generada
echo  -----------------------------------------------

echo  -----------------------------------------------
echo    Fase 3: Compilaci�n
echo  -----------------------------------------------
pause

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
echo    Fase 4: Generaci�n de los stubs y skeletons
echo  -----------------------------------------------
pause 

echo Generando stubs...

rmic uoc.edu.tds.pec4.iface.RemotoImpl

echo  -----------------------------------------------
echo    Ya puede ejecutar servidor.bat!
echo  -----------------------------------------------
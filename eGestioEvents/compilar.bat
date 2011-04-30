echo off

echo  -----------------------------------------------
echo    Fase 1: Preparación entorno de ejecución
echo  -----------------------------------------------
pause

rmdir /S /Q .\uoc
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
javadoc src\uoc\edu\tds\pec4\excepciones\*.java -d doc
javadoc src\uoc\edu\tds\pec4\iface\*.java -d doc
javadoc src\uoc\edu\tds\pec4\conexion\*.java -d doc
javadoc src\uoc\edu\tds\pec4\mantenimiento\*.java -d doc
javadoc src\uoc\edu\tds\pec4\estadisticas\*.java -d doc
javadoc src\uoc\edu\tds\pec4\eventos\*.java -d doc
javadoc src\uoc\edu\tds\pec4\client\*.java -d doc
javadoc src\uoc\edu\tds\pec4\servidor\*.java -d doc

echo  -----------------------------------------------
echo    Documentación generada
echo  -----------------------------------------------

echo  -----------------------------------------------
echo    Fase 3: Compilación
echo  -----------------------------------------------
pause

echo Compilando el codigo...

javac src\uoc\edu\tds\pec4\beans\*.java -d .
javac src\uoc\edu\tds\pec4\utils\*.java -d .
javac src\uoc\edu\tds\pec4\excepciones\*.java -d .
javac src\uoc\edu\tds\pec4\iface\*.java -d .
javac src\uoc\edu\tds\pec4\conexion\*.java -d .
javac src\uoc\edu\tds\pec4\mantenimiento\*.java -d .
javac src\uoc\edu\tds\pec4\eventos\*.java -d .
javac src\uoc\edu\tds\pec4\estadisticas\*.java -d .
javac src\uoc\edu\tds\pec4\cliente\*.java -d .
javac src\uoc\edu\tds\pec4\servidor\*.java -d .

echo  -----------------------------------------------
echo    Fase 4: Generación de los stubs y skeletons
echo  -----------------------------------------------
pause 

echo Generando stubs...

rmic uoc.edu.tds.pec4.servidor.RemotoImpl

echo  -----------------------------------------------
echo    Ya puede ejecutar servidor.bat!
echo  -----------------------------------------------
echo off
echo  -----------------------------------------------
echo    Fase 1: Copiar binarios
echo  -----------------------------------------------

rmdir /S /Q .\uoc
echo Copiando el codigo...
XCOPY /E /I /H /Y bin\*.* *.*

echo  -----------------------------------------------
echo    Fase 2: Generación de los stubs y skeletons
echo  -----------------------------------------------


echo Generando stubs...

rmic uoc.edu.tds.pec4.servidor.RemotoImpl

echo  -----------------------------------------------
echo    Fase 3: ejecutando servidor
echo  -----------------------------------------------
servidor.bat

echo  -----------------------------------------------
echo    Fase 4: ejecutando cliente
echo  -----------------------------------------------
cliente.bat
@echo off
@.;C:\Program Files\Java\jre6\lib\ext\QTJava.zip;C:\Program Files\Java\jdk1.6.0_24\lib\tools.jar
echo  -----------------------------------------------
echo    Servidor: Registro
echo  -----------------------------------------------
pause
set classpath=%classpath%


echo  -----------------------------------------------
echo    Servidor: Ejecución
echo  -----------------------------------------------


echo  -----------------------------------------------
echo    Esperando conexiones de clientes!
echo  -----------------------------------------------
cd build
java uoc.edu.tds.pec4.servidor.Servidor
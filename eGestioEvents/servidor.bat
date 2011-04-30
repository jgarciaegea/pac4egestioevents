@echo off

echo  -----------------------------------------------
echo    Servidor: Registro
echo  -----------------------------------------------
pause

start rmiregistry.exe

echo  -----------------------------------------------
echo    Servidor: Ejecución
echo  -----------------------------------------------


echo  -----------------------------------------------
echo    Esperando conexiones de clientes!
echo  -----------------------------------------------

java uoc.edu.tds.pec4.servidor.Servidor
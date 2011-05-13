#!/bin/bash
# compile.sh
#
# compila las fuentes del eGestioEvents
#
echo
echo
echo "Gestionar carpetas"
echo "------------------"
echo "Eliminar bin..."
#rm -rf bin
echo "....OK"
echo "Crear bin..."
#mkdir bin
cp -rf src/i18n bin
cp -rf conf bin
echo "....OK"
echo
echo
echo "Compilar fuentes eGestionEventos."
echo "--------------------------------"
echo "Nombre: "$LOGNAME
echo "Directorio: "$HOME
echo -n "Fecha: "
date
echo
echo "Beans...."
javac -d bin src/uoc/edu/tds/pec4/beans/*.java
echo "....OK"
echo "Utils...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/utils/*.java
echo "....OK"
echo "Resources...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/resources/*.java
echo "....OK"
echo "Excepciones...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/excepciones/*.java
echo "....OK"
echo "Daos...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/daos/*.java
echo "....OK"
echo "Dtos...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/dtos/*.java
echo "....OK"
echo "iFace...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/iface/*.java
echo "....OK"
echo "Gestores...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/gestores/*.java
echo "....OK"
echo "Pantallas...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/pantallas/*.java
echo "....OK"
echo "Servidor...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/servidor/*.java
echo "....OK"
echo "Cliente...."
javac -d bin -cp bin src/uoc/edu/tds/pec4/cliente/*.java
echo "....OK"
echo "Generar STUBs y Skeletons...."
cd bin
rmic uoc.edu.tds.pec4.iface.RemotoImpl
#cp uoc/edu/tds/pec4/iface/RemotoImpl_Stub.* uoc/edu/tds/pec4/comun
echo "....OK"
echo
echo "Ya se puede ejecutar el servidor y cliente...."
echo

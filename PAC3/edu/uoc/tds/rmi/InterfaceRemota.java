package edu.uoc.tds.rmi;

import java.rmi.*;
import java.io.Serializable;

/**
 * Interface remota con los métodos que se podrán llamar en remoto
 * @author  Jonathan Garcia
 */
public interface InterfaceRemota extends Remote {
    public void Menupral () throws RemoteException;
    
    public boolean conectaBD() throws RemoteException;
}


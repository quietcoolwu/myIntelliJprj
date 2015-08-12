package chap18;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {

    HashMap<String, Service> serviceList;


    public ServiceServerImpl() throws RemoteException {
        // start and set up services
        setUpServices();
    }

    public static void main(String[] args) {

        try {
            Naming.rebind("ServiceServer", new ServiceServerImpl());
        } catch (Exception ex) {
        }
        System.out.println("Remote service is running");
    }

    private void setUpServices() {
        serviceList = new HashMap<String, Service>();
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Day of the Week Service", new DayOfTheWeekService());
        serviceList.put("Visual Music Service", new MiniMusicService());
    }

    public Object[] getServiceList() {
        System.out.println("in remote");
        return serviceList.keySet().toArray();

    }

    public Service getService(Object serviceKey) throws RemoteException {
        Service theService = (Service) serviceList.get(serviceKey);
        return theService;
    }

}
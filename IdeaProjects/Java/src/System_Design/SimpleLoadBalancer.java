package System_Design;

import java.util.*;

public class SimpleLoadBalancer {
    private List<String> servers = new ArrayList<>();
    private int index = 0;

    public void addServer(String address) {
        servers.add(address);
    }

    public void handleRequest(String request) {
        if (servers.isEmpty()) {
            System.out.println("No servers available.");
            return;
        }
        String server = servers.get(index);
        System.out.println("Request '" + request + "' handled by " + server);
        index = (index + 1) % servers.size();
    }

    public static void main(String[] args) {
        SimpleLoadBalancer lb = new SimpleLoadBalancer();
        lb.addServer("192.168.1.1:8080");
        lb.addServer("192.168.1.2:8080");

        for (int i = 1; i <= 6; i++) {
            lb.handleRequest("Request-" + i);
        }
    }
}

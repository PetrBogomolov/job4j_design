package ru.job4j.ood.ocp.example1;

public class ClientProxy {
    private final Server server;

    public ClientProxy(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        ClientProxy client = new ClientProxy(new Proxy());
        client.server.operate();
    }

    interface Server {
        void operate();
    }

    static class ServerInput implements Server {

        @Override
        public void operate() {
            System.out.println("происходит вход...");
            System.out.println("добро пожаловать");
        }
    }

    static class Proxy implements Server {
        private final Server input = new ServerInput();

        @Override
        public void operate() {
            if (identific()) {
                input.operate();
            }
        }

        private boolean identific() {
            System.out.println("происходит идентификация...");
            return true;
        }
    }
}

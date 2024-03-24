package crtevn.webserver;

public class Config {

    private static Config instance;

    private final int port;
    private final String staticResourcesPath;

    private Config(final int port, final String staticResourcesPath) {
        verifyPort(port);
        this.port = port;
        this.staticResourcesPath = staticResourcesPath;
    }

    public static synchronized Config initialize(final int port, final String staticResourcesPath) {
        if (instance == null) {
            instance = new Config(port, staticResourcesPath);
        } else {
            throw new IllegalStateException("Config is already initialized.");
        }
        return instance;
    }

    public static Config getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Config is not initialized yet. Please call initialize() first.");
        }
        return instance;
    }

    private void verifyPort(final int port) {
        int minPort = 1024;
        int maxPort = 49151;
        if (port < minPort || port > maxPort) {
            throw new IllegalArgumentException(
                "Invalid port number " + port + ". Please enter a value between " + minPort
                    + " and " + maxPort + ".");
        }
    }

    public int getPort() {
        return port;
    }

    public String getStaticResourcesPath() {
        return staticResourcesPath;
    }
}

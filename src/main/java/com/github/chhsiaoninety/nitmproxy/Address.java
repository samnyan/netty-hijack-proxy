package com.github.chhsiaoninety.nitmproxy;

public class Address {
    private String host;
    private int port;
    private String fakeHost;

    public Address(String host, int port) {
        this.host = host;
        this.port = port;
        this.fakeHost = null;
    }

    public Address(String host, int port, String fakeHost) {
        this.host = host;
        this.port = port;
        this.fakeHost = fakeHost;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getFakeHost() {
        return fakeHost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if (port != address.port) {
            return false;
        }
        return host != null? host.equals(address.host) : address.host == null;

    }

    @Override
    public int hashCode() {
        int result = host != null? host.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s:%d", host, port);
    }
}

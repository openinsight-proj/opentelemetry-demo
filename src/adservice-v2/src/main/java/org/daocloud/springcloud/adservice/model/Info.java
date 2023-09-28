package org.daocloud.springcloud.adservice.model;

public class Info {
    String ID;

    String version;

    String hostName;

    Info target;

    public Info getTarget() {
        return target;
    }

    public void setTarget(Info target) {
        this.target = target;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}

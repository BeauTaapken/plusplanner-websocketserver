package plusplanner.websocketserver.reactor;

public abstract class TypeReactor {
    private final String type;

    protected TypeReactor(String type) {
        this.type = type;
    }

    public String getHandlingType() {
        return type;
    }
}

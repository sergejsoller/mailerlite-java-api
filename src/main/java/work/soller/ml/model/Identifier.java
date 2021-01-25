package work.soller.ml.model;

/**
 * Object ID for MailerLite objects
 */
public class Identifier {
    private final String value;

    public Identifier(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Id expectec, got null or empty string.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getValue();
    }
}

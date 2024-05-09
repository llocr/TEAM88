package type;

public enum SubjectType {
    MANDATORY("필수과목"),
    CHOICE("선택과목");

    private final String value;

    SubjectType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

public class Record {
    private final String field1;
    private final int field2;
    private final double field3;

    private Record(Builder builder) {
        this.field1 = builder.field1;
        this.field2 = builder.field2;
        this.field3 = builder.field3;
    }

    public String getField1() {
        return field1;
    }

    public int getField2() {
        return field2;
    }

    public double getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "Record{field1='" + field1 + "', field2=" + field2 + ", field3=" + field3 + "}";
    }

    public static class Builder {
        private String field1;
        private int field2;
        private int field3;

        public Builder field1(String field1) {
            this.field1 = field1;
            return this;
        }

        public Builder field2(int field2) {
            this.field2 = field2;
            return this;
        }

        public Builder field3(int field3) {
            this.field3 = field3;
            return this;
        }

        public Record build() {
            return new Record(this);
        }
    }
}

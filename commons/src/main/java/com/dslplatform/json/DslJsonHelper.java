package com.dslplatform.json;

public final class DslJsonHelper {
    private DslJsonHelper() {}
    public static <T> void reset(JsonReader<T> reader) {
        reader.reset();
    }
}

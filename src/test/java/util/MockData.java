package util;

public class MockData {

    private static String mockResponseBody1 = "{testAnswer}";

    private static String mockResponseBody2 = "[\n" +
            "  {\n" +
            "    \"id\": 0,\n" +
            "    \"name\":\"Fitenko\",\n" +
            "    \"full_name\":\"Oleg/Fitenko\",\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 666,\n" +
            "    \"name\":\"tnko\",\n" +
            "    \"full_name\":\"Oleg/tnko\",\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 69420,\n" +
            "    \"name\":\"ST-MAN\",\n" +
            "    \"full_name\": \"Oleg/ST-MAN\",\n" +
            "  }\n" +
            "]";

    private static String urlPath1 = "https://localhost:1080/testRest";

    private static String urlPath2 = "https://localhost:1080/api.github.com/";

    public static String getMockResponseBody1() {
        return mockResponseBody1;
    }

    public static String getMockResponseBody2() {
        return mockResponseBody2;
    }

    public static String getUrlPath1() {
        return urlPath1;
    }

    public static String getUrlPath2() {
        return urlPath2;
    }

}

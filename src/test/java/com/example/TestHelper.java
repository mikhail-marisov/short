package com.example;

import com.example.domain.Link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHelper {

    public static Link TEST_LINK = new Link("https://habr.com/ru/all/", "http://localhost:8080/short/?id=gDHfWoI", 1L);
    public static Link TEST_LINK2 = new Link("https://www.google.com/", "http://localhost:8080/short/?id=DjA5uTq", 1L);
    public static Link TEST_LINK3 = new Link("https://www.baeldung.com/", "http://localhost:8080/short/?id=pn2isod", 1L);
    public static List<Link> TEST_LINK_LIST = new ArrayList<>(Arrays.asList(TEST_LINK, TEST_LINK2, TEST_LINK3));

}

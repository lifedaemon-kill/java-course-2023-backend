package edu.java.bot.utility.LinkParseTest;

import edu.java.bot.utility.LinkParse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IsURLTest {
    @Test
    public void test1() {
        String url = "https://stackoverflow.com/questions/123456";
        boolean response = LinkParse.isURL(url);
        assertThat(response).isTrue();
    }

    @Test
    public void test2() {
        String url = "hello";
        boolean response = LinkParse.isURL(url);
        assertThat(response).isFalse();
    }
    @Test
    public void test3() {
        String url = "/hello/world.com";
        boolean response = LinkParse.isURL(url);
        assertThat(response).isFalse();
    }
}

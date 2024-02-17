package edu.LinkParse;

import edu.java.bot.utilities.LinkParse;
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
        String url = "questions/123456";
        boolean response = LinkParse.isURL(url);
        assertThat(response).isFalse();
    }
}
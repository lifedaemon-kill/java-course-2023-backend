package edu.LinkParse;

import edu.java.bot.utilities.LinkParse;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class IsSupportsTest {
    @Test
    public void test1() {
        String url = "https://stackoverflow.com/questions/123456";
        boolean response = LinkParse.isURLSupports(url);
        assertThat(response).isTrue();
    }

    @Test
    public void test2() {
        String url = "https://meta.stackoverflow.com/questions/123456";
        boolean response = LinkParse.isURLSupports(url);
        assertThat(response).isFalse();
    }
    @Test
    public void test3() {
        String url = "https://github.com/questions/1256";
        boolean response = LinkParse.isURLSupports(url);
        assertThat(response).isTrue();
    }
    @Test
    public void test4() {
        String url = "1256";
        boolean response = LinkParse.isURLSupports(url);
        assertThat(response).isFalse();
    }
}

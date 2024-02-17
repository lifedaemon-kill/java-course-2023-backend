package edu.LinkParse;

import edu.java.bot.utilities.LinkParse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class IsSupportsTest {
    @Test
    public void test1() throws URISyntaxException {
        URI url = new URI("https://stackoverflow.com/questions/123456");

        boolean response = LinkParse.isURLSupports(url);

        assertThat(response).isTrue();
    }
    @Test
    public void test2() throws URISyntaxException {
        URI url = new URI("https://meta.stackoverflow.com/questions/123456");

        boolean response = LinkParse.isURLSupports(url);

        assertThat(response).isFalse();
    }
}

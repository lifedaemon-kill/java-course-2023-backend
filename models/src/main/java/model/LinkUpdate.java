package model;

import java.net.URI;
import java.util.ArrayList;

public record LinkUpdate(
    int id,
    URI url,
    String description,
    ArrayList<Integer> tgChatIds
) {
}

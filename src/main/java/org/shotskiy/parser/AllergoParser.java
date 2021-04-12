package org.shotskiy.parser;

import java.io.IOException;
import java.util.List;

public interface AllergoParser {
    List<String> getAllPromGoods(String url) throws IOException;
}

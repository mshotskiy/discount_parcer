package org.shotskiy.parser;

import java.io.IOException;
import java.util.List;

public interface ProductExtractor<T> {
    T getFromURL(String url) throws IOException, InterruptedException;

    List<T> getAllItems(List<String> links) throws IOException, InterruptedException;


}

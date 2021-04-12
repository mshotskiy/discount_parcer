package org.shotskiy.utils;

import java.util.List;

public interface ProductWriter<T> {
    void writeToCSV(List<T> products);
}

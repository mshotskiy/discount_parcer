package org.shotskiy;

import org.shotskiy.model.DiscountItem;
import org.shotskiy.parser.impl.AllegroParserImpl;
import org.shotskiy.parser.impl.PromProductsExtractorImpl;
import org.shotskiy.utils.DiscountItemWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.shotskiy.utils.Constants.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        AllegroParserImpl allegroParserImpl = new AllegroParserImpl();
        PromProductsExtractorImpl deviceExtractorImpl = new PromProductsExtractorImpl();
        DiscountItemWriter discountItemWriter = new DiscountItemWriter();

        List<DiscountItem> discountItems = new ArrayList<>(300);
        System.out.println("Starting parsing, it may take a few minutes");
        List<String> links = allegroParserImpl.getAllPromGoods(URL_DEVICES);
        links.addAll(allegroParserImpl.getAllPromGoods(URL_TOURIST_EQUIPMENT));
        links.addAll(allegroParserImpl.getAllPromGoods(URL_FOOD));
        discountItems.addAll(deviceExtractorImpl.getAllItems(links));

        discountItemWriter.writeToCSV(discountItems);
        System.out.println("Parsing finished");
    }
}

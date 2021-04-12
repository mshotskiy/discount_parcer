package org.shotskiy;

import org.shotskiy.model.DiscountItem;
import org.shotskiy.parser.AllergoParser;
import org.shotskiy.parser.ProductExtractor;
import org.shotskiy.parser.impl.AllegroParserImpl;
import org.shotskiy.parser.impl.PromProductsExtractorImpl;
import org.shotskiy.utils.DiscountItemWriter;
import org.shotskiy.utils.ProductWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.shotskiy.utils.Constants.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        AllergoParser allegroParser = new AllegroParserImpl();
        ProductExtractor productExtractor = new PromProductsExtractorImpl();
        ProductWriter discountItemWriter = new DiscountItemWriter();

        List<DiscountItem> discountItems = new ArrayList<>(300);
        System.out.println("Starting parsing, it may take a few minutes");
        List<String> links = allegroParser.getAllPromGoods(URL_DEVICES);
        links.addAll(allegroParser.getAllPromGoods(URL_TOURIST_EQUIPMENT));
        links.addAll(allegroParser.getAllPromGoods(URL_FOOD));
        discountItems.addAll(productExtractor.getAllItems(links));

        discountItemWriter.writeToCSV(discountItems);
        System.out.println("Parsing finished");
    }
}

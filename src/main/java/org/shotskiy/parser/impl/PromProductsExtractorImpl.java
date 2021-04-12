package org.shotskiy.parser.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.shotskiy.model.DiscountItem;
import org.shotskiy.parser.ProductExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.shotskiy.utils.Constants.NO_FIELD_VALUE;

public class PromProductsExtractorImpl implements ProductExtractor<DiscountItem> {
    private int iter = 0;

    public List<DiscountItem> getAllItems(List<String> links) throws IOException, InterruptedException {
        List<DiscountItem> discountItems = new ArrayList<>();
        for (String link : links) {
            discountItems.add(getFromURL(link));
        }
        return discountItems;
    }


    public DiscountItem getFromURL(String url) throws IOException, InterruptedException {
        if (iter >= 10) {
            Thread.sleep(5000);
            iter = 0;
        }
        Thread.sleep(500);
        Document page = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get();

        Elements elements = page.select("div._1qltd._f8818_3-1jj");

        DiscountItem discountItem = new DiscountItem();

        discountItem.setName(getNameFromPage(page));
        discountItem.setInvoice(getFieldFromPage(elements, "Faktura"));
        discountItem.setColor(getFieldFromPage(elements, "Kolor"));
        discountItem.setState(getFieldFromPage(elements, "Stan"));
        discountItem.setType(getFieldFromPage(elements, "Rodzaj"));
        discountItem.setBrand(getFieldFromPage(elements, "Marka"));
        discountItem.setPrice(getPriceFromPage(page));
        discountItem.setDiscount(getDiscountFromPage(page));
        discountItem.setLink(url);

        iter++;
        return discountItem;
    }

    public String getPriceFromPage(Document page) {
        Elements price = page.select("div._1svub._lf05o._9a071_3SxcJ").select("span");

        if (price.isEmpty()) {
            return NO_FIELD_VALUE;
        } else {
            return price.text().trim();
        }
    }

    public String getDiscountFromPage(Document page) {
        Elements discount = page.select("span._1t9p2._bsvj8._1dd5x._1jusk._9a071_1df9W");

        if (discount.isEmpty()) {
            return NO_FIELD_VALUE;
        } else {
            return discount.first().text();
        }
    }

    public String getFieldFromPage(Elements div, String fieldName) {
        StringBuilder cssQuery = new StringBuilder("div:contains()");
        cssQuery.insert(13, fieldName);
        Elements elements = div.select(cssQuery.toString());
        if (elements.isEmpty()) {
            return NO_FIELD_VALUE;
        } else {
            return elements.select("div._f8818_DQKcc").get(0).text();
        }
    }

    public String getNameFromPage(Document page) {
        Elements nameHeading = page.select("div._1h7wt._15mod").select("h1");
        if (nameHeading.isEmpty()) {
            return NO_FIELD_VALUE;
        } else {
            return nameHeading.first().text();
        }

    }


}

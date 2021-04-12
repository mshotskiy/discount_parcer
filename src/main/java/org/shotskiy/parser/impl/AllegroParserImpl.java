package org.shotskiy.parser.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.shotskiy.parser.AllergoParser;
import org.shotskiy.utils.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AllegroParserImpl implements AllergoParser {

    @Override
    public List<String> getAllPromGoods(String url) throws IOException {
        int pageNumb = 1;
        List<String> list = getLinksPromotionalGoods(url, pageNumb);
        int countProductsInArray = list.size();

        while (countProductsInArray <= Constants.NUMBER_PRODUCTS) {
            pageNumb++;
            list.addAll(getLinksPromotionalGoods(url, pageNumb));
            countProductsInArray = list.size();
        }

        while (countProductsInArray > Constants.NUMBER_PRODUCTS) {
            list.remove(--countProductsInArray);
        }

        return list;
    }

    private List<String> getLinksPromotionalGoods(String url, int pageNum) throws IOException{
        Document page = Jsoup.connect(url+pageNum)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get();

        removeAds(page);

        Elements itemDivs = page.select("div.mpof_ki.mqen_m6.mp7g_oh:has(div.msbw_2.mldj_2)");
        Elements linkTags = itemDivs.select("a._w7z6o._uj8z7");
        List<String> links = new ArrayList<>();
        linkTags.forEach(linkTag -> links.add(linkTag.attr("href")));
        return links;
    }

    private void removeAds(Document page) {
        page.select("article[data-analytics-view-label=showSponsoredItems]").remove();
        page.select("div[data-box-name=leftpanel_ads]").remove();
        page.select("div[data-box-name=AdsPremium]").remove();
        page.select("div[data-box-name=AdsPremiumBottom]").remove();
        page.select("div[data-box-name=rekomendacje]").remove();
    }


}

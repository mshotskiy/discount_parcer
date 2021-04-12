package org.shotskiy.utils;

import org.shotskiy.model.DiscountItem;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class DiscountItemWriter implements ProductWriter<DiscountItem>{


    public void writeToCSV(List<DiscountItem> items) {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("products.csv"), "UTF-8"))) {

            String header = new String("Name" + Constants.CSV_SEPARATOR
                    + "Cena" + Constants.CSV_SEPARATOR + "Zniżka" + Constants.CSV_SEPARATOR
                    + "Stan" + Constants.CSV_SEPARATOR + "Kolor" + Constants.CSV_SEPARATOR
                    + "Rodzaj" + Constants.CSV_SEPARATOR + "Marka" + Constants.CSV_SEPARATOR
                    + "Faktura" + Constants.CSV_SEPARATOR + "Lącze");

            bw.write(header);
            bw.newLine();

            for (DiscountItem item : items) {
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(item.getName().trim().length() == 0 ? "" : item.getName());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getPrice().trim().length() == 0 ? "" : item.getPrice());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getDiscount().trim().length() == 0 ? "" : item.getDiscount());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getState().trim().length() == 0 ? "" : item.getState());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getColor().trim().length() == 0 ? "" : item.getColor());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getType().trim().length() == 0 ? "" : item.getType());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getBrand().trim().length() == 0 ? "" : item.getBrand());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getInvoice().trim().length() == 0 ? "" : item.getInvoice());
                oneLine.append(Constants.CSV_SEPARATOR);
                oneLine.append(item.getLink().trim().length() == 0 ? "" : item.getLink());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

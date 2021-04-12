package org.shotskiy.model;

import java.util.Objects;

public class DiscountItem {
    private String name;
    private String invoice;
    private String color;
    private String state;
    private String price;
    private String discount;
    private String type;
    private String brand;

    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountItem that = (DiscountItem) o;
        return Objects.equals(name, that.name) && Objects.equals(invoice, that.invoice) && Objects.equals(color, that.color) && Objects.equals(state, that.state) && Objects.equals(type, that.type) && Objects.equals(brand, that.brand) && Objects.equals(price, that.price) && Objects.equals(discount, that.discount) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, invoice, color, state, type, brand, price, discount, link);
    }

    @Override
    public String toString() {
        return "DiscountItem{" +
                "name='" + name + '\'' +
                ", invoice='" + invoice + '\'' +
                ", color='" + color + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}

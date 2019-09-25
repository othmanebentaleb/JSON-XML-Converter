package com.example.demo.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringTokenizer;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    private String reference;
    private char color;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(String reference, char color, double price, int quantity) {
        this.reference = reference;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String line) {
        StringTokenizer lineSplited = new StringTokenizer(line, ";");
        this.reference = lineSplited.nextToken();
        this.color =  lineSplited.nextToken().charAt(0);
        this.price = Double.parseDouble(lineSplited.nextToken());
        this.quantity = Integer.parseInt(lineSplited.nextToken());
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "reference='" + reference + '\'' +
                ", color=" + color +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

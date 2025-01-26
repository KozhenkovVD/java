package homeworks.homework08;

import homeworks.homework08.Person;
import homeworks.homework08.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> inputList = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader("src/main/java/homeworks/homework08/input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("src/main/java/homeworks/homework08/output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (bufferedReader.ready()) {
                inputList.add(bufferedReader.readLine());
            }
            String[] arrayPerson = inputList.get(0).split(";");
            List<Person> persons = new ArrayList<Person>();
            for (int i = 0; i < arrayPerson.length; i++) {
                String name = arrayPerson[i].substring(0, arrayPerson[i].indexOf("=")).trim();
                float balance = Float.parseFloat(arrayPerson[i].substring(arrayPerson[i].indexOf("=") + 1).trim());
                persons.add(new Person(name, balance));
            }
            ;

            String[] arrayStrProduct = inputList.get(1).split(";");
            List<Product> products = new ArrayList<Product>();
            for (int i = 0; i < arrayStrProduct.length; i++) {
                String name = arrayStrProduct[i].substring(0, arrayStrProduct[i].indexOf("=")).trim();
                float price = Float.parseFloat((arrayStrProduct[i].substring(arrayStrProduct[i].indexOf("=") + 1).trim()));
                products.add(new Product(name, price));
            }
            ;

            for (int i = 2; i < inputList.size(); i++) {
                if (inputList.get(i).equals("END")) {
                    break;
                } else {
                    String buy = inputList.get(i);
                    String name = buy.substring(0, buy.indexOf("-")).trim();
                    String productname = buy.substring(buy.indexOf("-") + 1).trim();
                    Person bayer = null;
                    Product product = null;

//Определяем покупателя
                    for (Person p : persons) {
                        if (p.getName().equals(name)) {
                            bayer = p;
                        }
                    }

//Определяем продукт
                    for (Product p : products) {
                        if (p.getName().equals(productname)) {
                            product = p;
                        }
                    }

//Добавляем в корзину
                    bufferedWriter.write(bayer.addProductToBasket(product) + "\r\n");
                }
            }
            for (int i = 0; i < persons.size(); i++) {
                bufferedWriter.write(persons.get(i).getProductFromBasket() + "\r\n");
                bufferedWriter.flush();
            }
            fileReader.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}




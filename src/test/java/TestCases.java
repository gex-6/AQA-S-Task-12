import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;


public class TestCases {

    String bookName = "Про Миколу Гоголя, Марка Твена, Ніколу Теслу, Альберта Ейнштейна, Стівена Кінга"; //not available
    String bookName2 = "Знову й знову"; //available both
    String bookName3 = "Ідентичність"; //available in paper
    String url = "https://www.yakaboo.ua/";
    public SelenideElement searchField = Selenide.$("[type=\"search\"]");
    public SelenideElement searchButton = Selenide.$("[class=\"ui-search-form-input\"]>button[class=\"ui-btn-primary\"]");
    public SelenideElement noBookButton = Selenide.$("[class=\"base-product__buying\"] ~ [class=\"ui-btn-accept waiting-list button-accept\"]");
    public SelenideElement paperBookButton = Selenide.$("[class=\"paper\"]");
    public SelenideElement digitalBookButton = Selenide.$("[class=\"digital\"]");
    public SelenideElement paperPrice = Selenide.$("div.ui-price-display.price.simple.selected > div.ui-price-display__main.special-price > span");
    public SelenideElement digitalPrice = Selenide.$("div.ui-price-display.price.simple:last-of-type > [class=\"ui-price-display__main\"] > span");

    @Test
    public void yakabooTests() throws InterruptedException {

        String book = bookName2;

        Selenide.open(url);
        searchField.setValue(book);
        Thread.sleep(1000);
        searchButton.shouldBe(Condition.visible).click();
        Selenide.$("[title=\"" + book + "\"]").click();

        if (noBookButton.exists()) {
            System.out.println("Книга не в наявності");
        } else {
            Thread.sleep(1000);
            if (paperBookButton.isDisplayed()) {
                String pricePaper = paperPrice.getText();
                System.out.println("Ціна паперової книги: " + pricePaper);
            }
            Thread.sleep(1000);
            if (digitalBookButton.isDisplayed()) {
                String priceDig = digitalPrice.getText();
                System.out.println("Ціна електронної книги: " + priceDig);
            }
        }
    }
}

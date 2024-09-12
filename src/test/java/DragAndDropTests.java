import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTests {


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void moveElements() {
        //открываем сайт
        open("https://the-internet.herokuapp.com/drag_and_drop");

        //берем квадрат А, двигаем на 150пикселей направо, и отпускаем.
        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(150, 0).release().perform();

        //после успешного переремещения у квадратов меняются местами заголовки. Проверяем, что на месте квадрата B стоит заголовок квадрата А
        $("#column-b").$("header").shouldHave(exactTextCaseSensitive("A"));

        //пробуем поменять их местами обратно с помошью команды dragAndDrop
        $("#column-b").dragAndDrop(to($("#column-a")));

        //если перемещение удалось, то у квадрата А снова должен быть залоговок "А". Проверяем это
        $("#column-a").$("header").shouldHave(exactTextCaseSensitive("A"));


    }


}

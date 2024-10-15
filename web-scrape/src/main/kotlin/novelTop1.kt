import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val anyUrl = "https://noveltop1.net/novel-book/she-was-both-called-god-as-well-as-satan/chapter-82"
    val name = "she was called god"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(anyUrl)
    val listButton = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chr-nav-top > div > button")))
    listButton.click()
    val selectTag = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chr-nav-top > div > select")))
    val catalogueHtml = selectTag.getAttribute("innerHTML")
    val jsoupDoc = Jsoup.parse(catalogueHtml)
    jsoupDoc.setBaseUri("https://noveltop1.com/")
    val aList = jsoupDoc.getElementsByTag("option")
//    aList.reverse()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for ((i,a) in aList.withIndex()) {
            println("$i,$a")
            val url = (a.absUrl("value"))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chr-content")))
            it.write("\n\nChapter $i:\n\n")
//            it.write("\n\n<h2 class=chapter>Chapter $i:</h2>\n\n")
//            it.write(content.getAttribute("innerHTML"));
            it.write(content.text);
        }
    }

}
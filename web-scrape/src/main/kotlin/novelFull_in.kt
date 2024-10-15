import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val indexUrl = "https://www.novelfull.in/My-House-of-Horrors/"
    val name = "My house of horrors"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(indexUrl)
    val sSchapterList = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-list")))
    val catalogueHtml = sSchapterList.getAttribute("innerHTML")
    val jsoupDoc = Jsoup.parse(catalogueHtml)
    jsoupDoc.setBaseUri("https://www.novelfull.in/")
    val aList = jsoupDoc.getElementsByTag("a")
//    aList.reverse()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for ((i,a) in aList.withIndex()) {
//            println("$i,$a")
            val url = (a.absUrl("href"))
            println(url)
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter__content")))
            it.write("\n\nChapter $i:\n\n")
//            it.write("\n\n<h2 class=chapter>Chapter $i:</h2>\n\n")
//            it.write(content.getAttribute("innerHTML"));
            it.write(content.text);
        }
    }

}
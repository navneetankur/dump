import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val anyUrl = "https://allnovelfull.com/otherworldly-evil-monarch/chapter-1001-infiltration.html"
    val name = "otherworldly evil monarch"
    val baseUri = "https://allnovelfull.com/"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(anyUrl)
    val listButton = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-nav-top > div > button")))
    listButton.click()
    val selectTag = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-nav-top > div > select")))
    val catalogueHtml = selectTag.getAttribute("innerHTML")
    val jsoupDoc = Jsoup.parse(catalogueHtml)
    jsoupDoc.setBaseUri(baseUri)
    val aList = jsoupDoc.getElementsByTag("option")
//    aList.reverse()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for ((i,a) in aList.withIndex()) {
            println("$i,$a")
            val url = (a.absUrl("value"))
            for(i in 1..100) {
                try {
                    chromeDriver.get(url)
                    break;
                } catch (e: WebDriverException) {
                    e.printStackTrace()
                }
            }
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-content"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }

}

import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //cloudflare shit don't use, unless there is a method to bypass.
    val indexUrl = "https://watashiwasugoidesu.com/fake-saint-of-the-year/"
    val baseUrl = "https://www.novelpub.com/"
    val name = "Fake saint of the year"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(indexUrl)
    val sIndexPagesUl = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content > article > div.entry.clr > ul")))
    val indexPagesUl = Jsoup.parse(sIndexPagesUl.getAttribute("innerHTML"))
    indexPagesUl.setBaseUri(baseUrl)
    val indexPagesLinks = indexPagesUl.getElementsByTag("a")
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for ((i,link) in indexPagesLinks.withIndex()) {
            val url = link.absUrl("href")
            println(url)
            chromeDriver.get(url)
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#wtr-content"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content)
        }
    }
}
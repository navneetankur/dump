import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val anyUrl = "https://www.novelcool.com/chapter/Tilea-s-Worries-Volume-3-Chapter-19/2060782/"
    val name = "tilea s worries"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(anyUrl)
    val catalogue = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chp-catalogue-m > div > div.chp-catalogue-m-list")))
    val catalogueHtml = catalogue.getAttribute("innerHTML")
    val jsoupDoc = Jsoup.parse(catalogueHtml)
    val aList = jsoupDoc.getElementsByTag("a")
    aList.reverse()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for ((i,a) in aList.withIndex()) {
            println("$i,$a")
            val url = a.attr("href")
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div.site-content > div.chp-skin.null > div.chapter-reading-section-list"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }

}
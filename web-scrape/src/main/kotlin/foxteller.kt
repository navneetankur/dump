import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //incomlete code, cloudflare shit
    val indexUrl = "https://www.foxteller.com/novel/47/the-good-for-nothing-seventh-young-lady"
    val name = "good for nothing seventh"
    val baseUrl = "https://www.foxteller.com/"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(indexUrl)
    val divTag = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#home")))
    val catalogueHtml = divTag.getAttribute("innerHTML")
    val jsoupDoc = Jsoup.parse(catalogueHtml)
    jsoupDoc.setBaseUri(baseUrl)
    val aList = jsoupDoc.getElementsByTag("a")
    aList.reverse()
    println(aList)
    return
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for ((i,a) in aList.withIndex()) {
            println("$i,$a")
            val url = (a.absUrl("href"))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.entry-content.cl"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }

}
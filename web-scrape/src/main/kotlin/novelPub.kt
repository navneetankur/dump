
import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //cloudflare shit don't use, unless there is a method to bypass.
    val indexStart = "https://www.novelpub.com/novel/running-away-from-the-hero-remake-26041540/chapters"
    val name = "evade the hero and flee"
    val baseUrl = "https://www.novelpub.com/"
    val chromeDriver = getNewChromeDriver()
    chromeDriver.get(indexStart)
    val sIndexPagesUl = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chpagedlist > div > div > div > ul")))
    val indexPagesUl = Jsoup.parse(sIndexPagesUl.getAttribute("innerHTML"))
    indexPagesUl.setBaseUri(baseUrl)
    val indexPagesLinks = indexPagesUl.getElementsByTag("a")
    val indexList = mutableListOf<String>()
    for(i in 0 until indexPagesLinks.size-1) {
        val a = indexPagesLinks[i]
        if(a.hasAttr("href"))
            indexList += (a.absUrl("href"))
    }
    val chapterList = mutableListOf<String>()
    addToChapterList(chromeDriver, baseUrl, chapterList)
    for(indexPageUrl in indexList) {
        chromeDriver.get(indexPageUrl)
        addToChapterList(chromeDriver, baseUrl, chapterList)
    }
    File("/home/navn/temp/"+name.replace(' ','_')+".html").bufferedWriter().use {
        for ((i,chapter) in chapterList.withIndex()) {
            chromeDriver.get(chapter)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-container")))
            it.write("\n\nChapter $i:\n\n")
//            it.write("\n\n<h2 class=chapter>Chapter $i:</h2>\n\n")
//            it.write(content.getAttribute("innerHTML"));
            it.write(content.text);

        }
    }







}

private fun addToChapterList(
    chromeDriver: ChromeDriver,
    baseUrl: String,
    chapterList: MutableList<String>
) {
    val sChapterList = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chpagedlist > ul")))
    val chapterListPage = Jsoup.parse(sChapterList.getAttribute("innerHTML"))
    chapterListPage.setBaseUri(baseUrl)
    val aList = chapterListPage.getElementsByTag("a")
    for (a in aList) {
        if (a.hasAttr("href"))
            chapterList += a.absUrl("href")
    }
}
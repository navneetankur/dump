import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //cloudflare shit don't use, unless there is a method to bypass.
    val urlFormat = "https://readwebnovels.net/novel/poison-genius-consort/chapter-%d/"
    val name = "Poison Genius Consort"
    val max = 1341
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')).bufferedWriter().use {
        for (i in 1..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div.wrap > div > div.site-content > div > div > div > div > div > div > div.c-blog-post > div.entry-content > div > div > div"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }
}
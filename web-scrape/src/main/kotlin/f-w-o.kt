import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {

    val urlFormat = "https://f-w-o.com/novel/i-am-the-fated-villain/latest-releases/chapter-%d/"
    val name = "Fated Villain"
    val max = 190
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for (i in 1..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div.wrap > div > div > div > div > div > div > div.main-col.col-md-8.col-sm-12.sidebar-hidden > div > div.c-blog-post > div.entry-content > div > div > div.reading-content > div.text-left"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }
}
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {

    val urlFormat = "https://www.wuxianovelhub.com/novel/omniscient-readers-viewpoint_%d.html"
    val name = "omniscient readers viewpoint"
    val max = 551
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')).bufferedWriter().use {
        for (i in 1..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-article > section.page-in.content-wrap > div.chapter-content"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }
}
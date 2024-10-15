import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val urlFormat = "https://www.readnovel.net/the-good-for-nothing-seventh-young-lady-chapter-%d"
    val name = "good for nothing seventh young"
    val max = 2264
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for (i in 494..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#section-list-wp > section > div.chapter-entity"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }
}
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {

    val urlFormat = "https://veratales.com/read/tales-of-the-reincarnated-lord/chapter-%d"
    val name = "tales of the reincarnated lord"
    val min = 0
    val max = 590
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for (i in min..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter_body"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }
}
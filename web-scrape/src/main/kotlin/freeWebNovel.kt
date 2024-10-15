import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //cloudflare shit don't use, unless there is a method to bypass.
    val urlFormat = "https://freewebnovel.com/sssclass-suicide-hunter/chapter-%d.html"
    val name = "sss class suicide hunter"
    val max = 211
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')).bufferedWriter().use {
        for (i in 1..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#main1 > div > div > div.txt"))).text
            it.write("\n\nChapter $i:\n\n")
            it.write(content);
        }
    }
}
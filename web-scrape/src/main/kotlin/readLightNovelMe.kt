import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val urlFormat = "https://www.readlightnovel.me/tales-of-demons-and-gods-rln/chapter-%d"
    val name = "tales of demons and gods"
    val max = 496
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')).bufferedWriter().use {
        for (i in 1..496) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc"))).text
            it.write("Chapter $i:")
            it.write(content);
        }
    }
}
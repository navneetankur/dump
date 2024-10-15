import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val urlFormat = "https://www.wuxialeague.com/chpater/397/%d.html"
    val name = "tileas worries"
    val max = 107
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')+".txt").bufferedWriter().use {
        for (i in 1..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#mlfy_main_text")))
            it.write("\n\nChapter $i:\n\n")
//            it.write(content.getAttribute("innerHTML"));
            it.write(content.text);
        }
    }
}
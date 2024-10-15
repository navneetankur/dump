import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //cloudflare shit don't use, unless there is a method to bypass.
    val urlFormat = "https://www.readwn.com/novel/tileas-worries_229.html"
    val name = "tileas worries"
    val max = 247
    val chromeDriver = getNewChromeDriver()
    File("/home/navn/temp/"+name.replace(' ','_')+".html").bufferedWriter().use {
        for (i in 1..max) {
            val url = (urlFormat.format(i))
            chromeDriver.get(url)
            //#ch-page-container > div > div.col-lg-8.content2 > div > div.chapter-content3 > div.desc
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#chapter-article > section.page-in.content-wrap > div.chapter-content")))
            it.write("\n\n<h2 class=chapter>Chapter $i:</h2>\n\n")
            it.write(content.getAttribute("innerHTML"));
        }
    }
}
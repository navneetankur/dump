
import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val firstPage = "https://www.royalroad.com/fiction/28601/arrogant-young-master-template-a-variation-4/chapter/758717/77-little-fish"
    val name = firstPage.split('/').let { it[it.size - 4] }.replace("-"," ")
    val basurl = "https://www.royalroad.com"
    val chromeDriver = getNewChromeDriver()
    var i=1;
    var currentPage = firstPage
    File("/home/navn/data/scrape/webnovel/" + name.replace(' ', '_') + ".html").bufferedWriter().use {
        it.write("<html><title>$name</title><body>")
        while(true){
            chromeDriver.get(currentPage)
//            val title = currentPage.split("/").last()
//            for (i in 1..100) {
//                try {
//                    chromeDriver.get(currentPage)
//                    break;
//                } catch (e: WebDriverException) {
//                    println("currentUrl is ${chromeDriver.currentUrl}")
//                    e.printStackTrace()
//                }
//            }
            WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chapter-inner.chapter-content")))
            val title = chromeDriver.findElement(By.cssSelector("h1.font-white")).text
            val jsoup = Jsoup.parse(chromeDriver.pageSource)
            val content = jsoup.select("div.chapter-inner.chapter-content")
            println("extracting: $i, $title, $currentPage")
//            it.write("\n\nChapter ${i++}:\n\n")
            it.write("\n\n<h2 class=chapter>Chapter $i:</h2>\n\n")
            it.write("$title\n\n")
            i++
            it.write(content.html())
//            it.write(content.getAttribute("innerHTML"));
            val buttonRow = chromeDriver.findElement(By.cssSelector("div.row.nav-buttons"))
            val buttons = buttonRow.findElements(By.cssSelector("a.btn.btn-primary.col-xs-12"))
            var nextFound = false;
            for(button in buttons) {
                if(button.text.contains("Next")) {
                    nextFound = true;
                    currentPage = button.getAttribute("href")
//                    button.click()
                    break;
                }
            }
            if(!nextFound) break;
        }
        it.write("</body></html>")
    }
}
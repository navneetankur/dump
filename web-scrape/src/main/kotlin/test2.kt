
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    val firstPage = "https://www.royalroad.com/fiction/28601/arrogant-young-master-template-a-variation-4/chapter/428239/2-disciple-acceptance-ceremony"
    val name = firstPage.split('/').let { it[it.size - 4] }.replace("-"," ")
    val chromeDriver = getNewChromeDriver()
    var i=1;
    chromeDriver.get(firstPage)
    val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chapter-inner.chapter-content"))).text
    println(content)
    return
    File("/home/navn/data/scrape/webnovel/" + name.replace(' ', '_') + ".txt").bufferedWriter().use {
        while(true){
            val currentPage = chromeDriver.currentUrl
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
            val content = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chapter-inner.chapter-content"))).text
            val title = chromeDriver.findElement(By.cssSelector("h1.font-white")).text
            println("extracting: $i, $title, $currentPage")
            it.write("\n\nChapter ${i++}:\n\n")
            it.write("$title\n\n")
            it.write(content);
            val buttonRow = chromeDriver.findElement(By.cssSelector("div.row.nav-buttons"))
            val buttons = buttonRow.findElements(By.cssSelector("a.btn.btn-primary.col-xs-12"))
            var nextFound = false;
            for(button in buttons) {
                if(button.text.contains("Next")) {
                    nextFound = true;
                    button.click()
                    break;
                }
            }
            if(!nextFound) break;
        }
    }
}
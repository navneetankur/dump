import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

fun main() {
    //read manually saved html
    val src = File("/home/navn/temp.html").readText()
    val jsoupDoc = Jsoup.parse(src)
    val outputLocation = "/home/navn/data/fakku/htmls/"
    val outputFile = File(outputLocation)
    outputFile.mkdirs()
    //set base url
    jsoupDoc.setBaseUri("https://archive.org/download/fakku-by-rbot2000-zip/")
    //get all useful links
    val trs = jsoupDoc.getElementsByTag("tr")
    val links = mutableListOf<String>()
    for (tr in trs) {
        val al = tr.getElementsByTag("a")
        for (a in al) {
            if(a.text() == "View Contents")
                links.add(a.absUrl("href"))
        }
    }
    //download the links using chromedriver
    val chromeDriver = getNewChromeDriver()
    for(link in links) {
        chromeDriver.get(link)
        //#maincontent > div > table > caption
        val fileName = WebDriverWait(chromeDriver, Duration.ofMinutes(3))
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#maincontent > div > table > caption"))).text
        val src = chromeDriver.pageSource
        File(outputLocation + fileName+".html").writeText(src)
        println(link)
    }
//    println(trs)
}

fun downloadPage(absUrl: String) {
    val chromeDriver = getNewChromeDriver()
    TODO("Not yet implemented")
}

package com.aaron.training.framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/16.
 */
public class BlueRose {
    private WebDriver driver;
    private int stepInterval = Integer.parseInt(GlobalSettings.stepInterval);
    private int timeout = Integer.parseInt(GlobalSettings.timeout);
    private Logger logger = Logger.getLogger(BlueRose.class.getName());

    /**
     * 构造函数初始化webdriver
     *
     * @param browserType 是浏览器类型
     */
    public BlueRose(int browserType) {
        switch (browserType) {
            //1:chrome
            case 1:
                System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            //2:opera
//            case 2:
//                System.setProperty("webdriver.opera.driver", "res/operadriver");
//                driver = new OperaDriver();
//                break;
            //3:firefox
            case 3:
//                System.setProperty("webdriver.gecko.driver", "res/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            //4:ie
            case 4:
                System.setProperty("webdriver.ie.driver", "res/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;

        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    /**
     * 构造函数初始化webdriver
     *
     * @param browserType 是浏览器类型
     */
    public BlueRose(int browserType, String seleniumURL) {
        switch (browserType) {
            //1:chrome
            case 1:
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//                capabilities.setBrowserName("chrome");
//                capabilities.setVersion("2.48.2");
                capabilities.setPlatform(Platform.WINDOWS);
                try {
                    driver = new RemoteWebDriver(new URL(seleniumURL), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            //2:opera
            case 2:
                System.setProperty("webdriver.opera.driver", "res/operadriver");
                driver = new OperaDriver();
                break;
            //3:firefox
            case 3:
                driver = new FirefoxDriver();
                break;
            //4:safari
            case 4:
                driver = new SafariDriver();
                break;
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    /**
     * 获取webdriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * 打开浏览器
     *
     * @param url 浏览器地址
     */
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * 通过ID点击
     *
     * @param id 是控件ID
     */
    public void clickById(String id) {
        findById(id).click();
    }

    /**
     * 通过ID获取元素再获取坐标然后再点击
     *
     * @param id 是控件ID
     */
    public void clickByIdWithLocation(String id) {
        WebElement webElement = findById(id);
        clickByLocation(webElement);
    }

    /**
     * 通过XPATH获取元素再获取坐标然后再点击
     *
     * @param xpath 是控件ID
     */
    public void clickByXpathWithLocation(String xpath) {
        WebElement webElement = findByXpath(xpath);
        clickByLocation(webElement);
    }

    /**
     * 通过CLASS获取元素再获取坐标然后再点击
     *
     * @param className 是控件ID
     */
    public void clickByClassNameWithLocation(String className) {
        WebElement webElement = findByClass(className);
        clickByLocation(webElement);
    }

    /**
     * 通过元素再获取坐标然后再点击
     *
     * @param webElement 是控件ID
     */
    public void clickByLocation(WebElement webElement) {
        int x = webElement.getSize().getWidth() / 2;
        int y = webElement.getSize().getHeight() / 2;
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().build().perform();
    }

    /**
     * 通过linkText点击
     *
     * @param linkText 是控件name
     */
    public void clickByLinkText(String linkText) {
        findByLinkText(linkText).click();
    }

    /**
     * 通过Name点击
     *
     * @param name 是控件name
     */
    public void clickByName(String name) {
        findByName(name).click();
    }


    /**
     * 通过xpath点击
     *
     * @param xpath 是控件xpath
     */
    public void clickByXpath(String xpath) {
        findByXpath(xpath).click();
    }

    /**
     * 通过class点击
     *
     * @param className 是控件class
     */
    public void clickByClass(String className) {
        findByClass(className).click();
    }

    public void clickByClassAndIndex(String className, int index) {
        findByClassAndIndex(className, index).click();
    }

    /**
     * 通过cssSelector点击
     *
     * @param cssSelector 是控件cssSelector
     */
    public void clickByCssSelector(String cssSelector) {
        findByCssSelecter(cssSelector).click();
    }

    /**
     * 通过tagName点击
     *
     * @param tagName 是控件tagName
     * @param index   是控件index
     */
    public void clickByTagNameAndIndex(String tagName, int index) {
        WebElement webElement = findByTagNameAndIndex(tagName, index);
        webElement.click();
    }

    /**
     * 通过ID输入值
     *
     * @param id    是控件id
     * @param value 是输入控件的值
     */
    public void typeById(String id, String value) {
        WebElement webElement = findById(id);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * 通过Name输入值
     *
     * @param name  是控件name
     * @param value 是输入控件的值
     */
    public void typeByName(String name, String value) {
        WebElement webElement = findByName(name);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * 通过Name输入值
     *
     * @param className 是控件class
     * @param value     是输入控件的值
     */
    public void typeByClass(String className, String value) {
        WebElement webElement = findByClass(className);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * 通过Name输入值
     *
     * @param className 是控件class
     * @param value     是输入控件的值
     */
    public void typeByClassAndIndex(String className, int index, String value) {
        WebElement webElement = findByClassAndIndex(className, index);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * 通过cssSelector输入值
     *
     * @param cssSelector 是控件cssSelcetor
     * @param value       是输入控件的值
     */
    public void typeByCssSelector(String cssSelector, String value) {
        WebElement webElement = findByCssSelecter(cssSelector);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * 通过xpath输入值
     *
     * @param xpath 是控件xpath
     * @param value 是输入控件的值
     */
    public void typeByXpath(String xpath, String value) {
        WebElement webElement = findByXpath(xpath);
        logger.info(webElement.getAttribute("type"));
        if (webElement.getAttribute("type").equals("text")) {
            webElement.clear();
        }
        webElement.sendKeys(value);
    }

    /**
     * 通过tagName输入值
     *
     * @param tagName 是控件tagName
     * @param index   是控件index
     * @param value   是输入控件的值
     */
    public void typeByTagNameAndIndex(String tagName, int index, String value) {
        WebElement webElement = findByTagNameAndIndex(tagName, index);
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * 通过ID查找控件
     *
     * @param elementId 是控件id
     */
    private WebElement findById(String elementId) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("id", elementId, endTime);
    }

    /**
     * 查找元素的公共方法
     *
     * @param type            是通过什么属性查找元素(id,name,className,xpath,tagName,linkText,partialLinkText,cssSelector)
     * @param elementProperty 元素属性
     * @param endTime         延时查找的结束时间
     */
    private WebElement findElement(String type, String elementProperty, long endTime) {
        WebElement webElement = null;
        while (true) {
            try {
                switch (type) {
                    case "id":
                        webElement = driver.findElement(By.id(elementProperty));
                        break;
                    case "name":
                        webElement = driver.findElement(By.name(elementProperty));
                        break;
                    case "className":
                        webElement = driver.findElement(By.className(elementProperty));
                        break;
                    case "xpath":
                        webElement = driver.findElement(By.xpath(elementProperty));
                        break;
                    case "tagName":
                        webElement = driver.findElement(By.tagName(elementProperty));
                        break;
                    case "linkText":
                        webElement = driver.findElement(By.linkText(elementProperty));
                        break;
                    case "partialLinkText":
                        webElement = driver.findElement(By.partialLinkText(elementProperty));
                        break;
                    case "cssSelector":
                        webElement = driver.findElement(By.cssSelector(elementProperty));
                        break;
                }
                if (webElement != null) {
                    break;
                }
            } catch (Exception e) {
                reFind(type, elementProperty, endTime);
            }
        }
        return webElement;
    }

    /**
     * 重新查找元素
     */
    public void reFind(String type, String elementProperty, long endTime) {
        if (System.currentTimeMillis() < endTime) {
            logger.info("can not find element by " + type + ":" + elementProperty + " retry!!!");
            pause(stepInterval);
            findElement(type, elementProperty, endTime);
        } else {
            handleFailure("time out to find element by " + type + ":" + elementProperty + " notice!!!");
        }
    }

    /**
     * 查找元素的公共方法
     *
     * @param type            是通过什么属性查找元素(className,tagName)
     * @param elementProperty 元素属性
     * @param index           元素的索引
     * @param endTime         延时查找的结束时间
     */
    private WebElement findElements(String type, String elementProperty, int index, long endTime) {
        WebElement webElement = null;
        while (true) {
            try {
                switch (type) {
                    case "className":
                        webElement = driver.findElements(By.className(elementProperty)).get(index);
                        break;
                    case "tagName":
                        webElement = driver.findElements(By.tagName(elementProperty)).get(index);
                        break;
                }
                if (webElement != null) {
                    break;
                }
            } catch (Exception e) {
                reFind(type, elementProperty, index, endTime);
            }
        }
        return webElement;
    }

    /**
     * 重新查找元素
     */
    private void reFind(String type, String elementProperty, int index, long endTime) {
        if (System.currentTimeMillis() < endTime) {
            logger.info("can not find element by " + type + ":" + elementProperty + " and index:" + index + " retry!!!");
            pause(stepInterval);
            findElements(type, elementProperty, index, endTime);
        } else {
            handleFailure("time out to find element by " + type + ":" + elementProperty + " and index:" + index + " notice!!!");
        }
    }

    /**
     * 通过ID查找控件
     *
     * @param linkText 是控件id
     */
    private WebElement findByLinkText(String linkText) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("linkText", linkText, endTime);
    }

    /**
     * 通过name查找控件
     *
     * @param name 是控件id
     */
    private WebElement findByName(String name) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("name", name, endTime);
    }

    /**
     * 通过className查找控件
     *
     * @param className 是控件class
     */
    private WebElement findByClass(String className) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("className", className, endTime);
    }

    /**
     * 通过className和索引查找控件
     *
     * @param className 是控件class
     * @param index     是控件index
     */
    private WebElement findByClassAndIndex(String className, int index) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElements("className", className, index, endTime);
    }

    /**
     * 通过xpath查找控件
     *
     * @param xpath 是控件xpath
     */
    private WebElement findByXpath(String xpath) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("xpath", xpath, endTime);
    }

    /**
     * 通过cssSelector查找控件
     *
     * @param cssSelector 是控件css
     */
    private WebElement findByCssSelecter(String cssSelector) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("cssSelector", cssSelector, endTime);
    }

    /**
     * 通过tagName查找控件
     *
     * @param tagName 是控件tag
     * @param index   是控件索引
     */
    public WebElement findByTagNameAndIndex(String tagName, int index) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElements("tagName", tagName, index, endTime);
    }

    /**
     * 通过tagName查找控件
     *
     * @param tagName 是控件tag
     */
    public WebElement findByTag(String tagName) {
        final long endTime = System.currentTimeMillis() + timeout;
        return findElement("tagName", tagName, endTime);
    }

    /**
     * 处理异常
     *
     * @param notice 是异常信息
     */
    private void handleFailure(String notice) {
        String png = LogTools.screenShot(this);
        String log = notice + " >> capture screenshot at " + png;
        logger.error(log);
        if (GlobalSettings.baseStorageUrl.lastIndexOf("/") == GlobalSettings.baseStorageUrl.length()) {
            GlobalSettings.baseStorageUrl = GlobalSettings.baseStorageUrl.substring(0, GlobalSettings.baseStorageUrl.length() - 1);
        }
        Reporter.log(log + "<br/><img src=\"" + GlobalSettings.baseStorageUrl + "/" + png + "\" />");
        Assert.fail(log);
    }

    /**
     * 暂停
     *
     * @param time 是等待的时间单位毫秒
     */
    public void pause(int time) {
        if (time <= 0) {
            return;
        }
        try {
            Thread.sleep(time);
            logger.info("Pause " + time + " ms");
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 退出浏览器
     */
    public void quit() {
        driver.quit();
    }

    /**
     * 点击alert的确定
     */
    public void alertConfirm() {
        pause(stepInterval);
        alertAccept(System.currentTimeMillis(), timeout);
    }

    /**
     * 点击alert的取消
     */
    public void alertDismiss() {
        pause(stepInterval);
        alertDismiss(System.currentTimeMillis(), timeout);
    }

    /**
     * 点击alert的确定
     *
     * @param startTime 开始时间
     * @param timeout   超时时间
     */
    private void alertAccept(long startTime, int timeout) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            if (System.currentTimeMillis() - startTime > timeout) {
                logger.info("Element alert is not found");
                handleFailure("Failed to click confirm");
            } else {
                pause(500);
                logger.info("Element alert is not found, try again");
                alertAccept(startTime, timeout);
            }
        }
    }

    /**
     * 点击alert的取消
     *
     * @param startTime 开始时间
     * @param timeout   超时时间
     */
    private void alertDismiss(long startTime, int timeout) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            if (System.currentTimeMillis() - startTime > timeout) {
                logger.info("Element alert is not found");
                handleFailure("Failed to click cancel");
            } else {
                pause(500);
                logger.info("Element alert is not found, try again");
                alertDismiss(startTime, timeout);
            }
        }
    }


    /**
     * 通过xpath切换 iframe
     *
     * @param xpath iframe的xpath
     */
    public void enterFrameByXpath(String xpath) {
        pause(stepInterval);
        driver.switchTo().frame(findByXpath(xpath));
        logger.info("Entered iframe by xpath:" + xpath);
    }

    /**
     * 通过id切换 iframe
     *
     * @param id iframe的id
     */
    public void enterFrameById(String id) {
        pause(stepInterval);
        driver.switchTo().frame(findById(id));
        logger.info("Entered iframe by id:" + id);
    }

    /**
     * 通过id切换 iframe
     *
     * @param webElement
     */
    public void enterFrameByElement(WebElement webElement) {
        pause(stepInterval);
        driver.switchTo().frame(webElement);
        logger.info("Entered iframe by webElement:" + webElement);
    }


    /**
     * 通过name切换 iframe
     *
     * @param name iframe的name
     */
    public void enterFrameByName(String name) {
        pause(stepInterval);
        driver.switchTo().frame(findByName(name));
        logger.info("Entered iframe by name: " + name);
    }

    /**
     * 通过className切换 iframe
     *
     * @param className iframe的class
     */
    public void enterFramebyClass(String className) {
        pause(stepInterval);
        driver.switchTo().frame(findByClass(className));
        logger.info("Entered iframe by className: " + className);
    }

    /**
     * 通过cssSelector切换 iframe
     *
     * @param cssSelector iframe的cssSelector
     */
    public void enterFrameByCssSelector(String cssSelector) {
        pause(stepInterval);
        driver.switchTo().frame(findByCssSelecter(cssSelector));
        logger.info("Entered iframe by cssSelector:" + cssSelector);
    }

    /**
     * 返回到默认的 iframe
     */
    public void leaveFrame() {
        pause(stepInterval);
        driver.switchTo().defaultContent();
        logger.info("Left the iframe");
    }


    /**
     * 刷新界面
     */
    public void refresh() {
        pause(stepInterval);
        driver.navigate().refresh();
        logger.info("Refreshed");
    }


    /**
     * 通过xpath悬停鼠标
     *
     * @param xpath 界面原属的xpath
     */
    public void mouseClickAndHoldByXpath(String xpath) {
        pause(stepInterval);
        mouseClickAndHold("xpath", xpath);
    }

    /**
     * 悬停鼠标
     *
     * @param elementProperty 界面元素的属性
     * @param type            定位元素的类型
     */
    public void mouseClickAndHold(String type, String elementProperty) {
        pause(stepInterval);
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        rb.mouseMove(0, 0);
        long endTime = System.currentTimeMillis() + timeout;
        WebElement we = findElement(type, elementProperty, endTime);
        try {
            Actions builder = new Actions(driver);
            builder.moveToElement(we).clickAndHold().build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            handleFailure("Failed to click and hold by " + type + ": " + elementProperty);
        }
        logger.info("click and hold by " + type + ": " + elementProperty);
    }

    /**
     * 通过id悬停鼠标
     *
     * @param id 界面原属的id
     */
    public void mouseClickAndHoldById(String id) {
        pause(stepInterval);
        mouseClickAndHold("id", id);
    }

    /**
     * 通过name悬停鼠标
     *
     * @param name 界面原属的name
     */
    public void mouseClickAndHoldByName(String name) {
        pause(stepInterval);
        mouseClickAndHold("name", name);
    }

    /**
     * 通过class悬停鼠标
     *
     * @param className 界面原属的class
     */
    public void mouseClickAndHoldByClass(String className) {
        pause(stepInterval);
        mouseClickAndHold("className", className);
    }

    /**
     * 通过cssSelector悬停鼠标
     *
     * @param cssSelector 界面原属的class
     */
    public void mouseClickAndHoldByCssSelector(String cssSelector) {
        pause(stepInterval);
        // First make mouse out of browser
        mouseClickAndHold("cssSelector", cssSelector);
    }

    /**
     * 执行js返回webelement
     *
     * @param js javaScript串
     */
    public WebElement findByJs(String js) {
        WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript(js);
        return element;
    }

    /**
     * 执行js,并且用webelement做参数
     *
     * @param js         javaScript串
     * @param webElement 界面元素
     */
    public void executeJs(String js, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(js, webElement);
    }

    /**
     * 执行js,并且用webelement做参数
     *
     * @param js javaScript串
     */
    public void executeJs(String js) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(js);
    }

    /**
     * 切换窗口
     */
    public void switchWindow() {
        String currentWindow = driver.getWindowHandle();//获取当前窗口句柄
        Set<String> handles = driver.getWindowHandles();//获取所有窗口句柄
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            if (currentWindow == it.next()) {
                continue;
            }
            driver.switchTo().window(it.next());//切换到新窗口
        }
    }

    /**
     * 根据索引切换窗口
     */
    public void switchWindow(int index) {
        Set<String> winHandels = driver.getWindowHandles();
        List<String> it = new ArrayList<String>(winHandels);
        driver.switchTo().window(it.get(index));
    }

    /**
     * 通过LINKTEXT断言结果
     * */
    public void expectExistOrNotByLinkText(boolean expectExist, String text) {
        WebElement webElement = findByLinkText(text);
        assertResult(expectExist, webElement);
    }

    /**
     * 通过XPATH断言结果
     * */
    public void expectExistOrNotByXpath(boolean expectExist, final String xpath) {
        WebElement webElement = findByXpath(xpath);
        assertResult(expectExist, webElement);
    }

    /**
     * 断言结果
     * */
    private void assertResult(boolean expectExist,WebElement webElement){
        if (expectExist) {
            if (webElement.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                handleFailure("assert result failed");
            }

        } else {
            if (webElement.isDisplayed()) {
                Assert.assertTrue(false);
            } else {
                handleFailure("assert result failed");
            }
        }
    }

}

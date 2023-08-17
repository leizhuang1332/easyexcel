# 请登录官网查看
官网地址：[https://alibaba-easyexcel.github.io](https://alibaba-easyexcel.github.io)

# yl版本使用说明
gitlab：ssh://git@code.jms.com:2222/project/jmsth/api/yl-jmsth-spm-easy-excel-api.git

- branch：v2.2.11

quickstart：http://code.jms.com/project/jmsth/api/yl-jmsth-spm-easy-excel-api/-/blob/v2.2.11/quickstart.md

```xml
<dependency>
    <groupId>com.lz</groupId>
    <artifactId>easyexcel</artifactId>
    <version>2.2.11.7</version>
</dependency>
```

_**使用时注意：需要排除其他版本的依赖。**_

增加的api方法签名如下：

```java
/**
 * @param pathName        要写入的文件路径
 * @param head            为类注释配置信息
 * @param param           导出参数            
 * @param translator      翻译    
 * @param isFieldCache    是否缓存head字段    
 */
public static ExcelWriterBuilder write(String pathName, Class head, Map<String, Object> param, Translator translator, boolean isFieldCache)
```

使用方法：

```java
写法一
        
String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
// 导出参数
Map<String, Object> param = new HashMap<>();
param.put("dayMonthType", 1);
param.put("code", "中国");
param.put("weight", BigDecimal.valueOf(10));
/**
 *      fileName：临时文件路径
 *      DemoData.class：模版类
 *      param：导出参数（前端查询参数）
 *      new I18nTranslator("TH")：自定义翻译
 *      false：告诉easyexcel不缓存head字段。easyexcel根据模版类解析出head后默认会缓存下来，现在需要根据参数动态生成head，所以要禁止缓存
 */
ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class, param, new I18nTranslator("TH"), false).build();
WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
excelWriter.write(data(), writeSheet);
```
```java

写法二（推荐）

// 导出参数
Map<String, Object> param = new HashMap<>();
param.put("dayMonthType", 1);
param.put("code", "中国");
param.put("weight", BigDecimal.valueOf(10));
// 文件流
OutputStream stream;
ExcelWriter excelWriter = EasyExcel.write().file(stream)
        .registerConverter(new LocalDateTimeConverter())
        .registerConverter(new LocalDateConverter())
        .build();
/**
 *      .head(DemoData.class)：模版类
 *      .param(param)：导出参数（前端查询参数）
 *      .translator(new I18nTranslator("CN"))：自定义翻译
 *      .isFieldCache(false)：告诉easyexcel不缓存head字段。easyexcel根据模版类解析出head后默认会缓存下来，现在需要根据参数动态生成head，所以要禁止缓存
 */
WriteSheet writeSheet = EasyExcel.writerSheet(0,sheetName)
        .registerWriteHandler(new SimpleColumnWidthStyleStrategy(20))
        .head(DemoData.class)
        .param(param)
        .translator(new I18nTranslator("TH"))
        .isFieldCache(false)
        .build();
writer.write(data(), writeSheet);
```

模版类示例，DemoData.class

```java
public class DemoData {
    @ExcelProperty("字符串标题")
    // 根据参数忽略这个字段
    @ExcelIgnoreCondition(test = "dayMonthType ==1")
    private String string;
    
    @ExcelProperty(value = "日期标题", multi = {
            // 可以定义多个Head，根据test条件判断使用
            @ExcelPropertyConditionMulti(value = "日期标题日汇总", test = "dayMonthType ==1 and (summaryType==1 or code ==中国)"),
            @ExcelPropertyConditionMulti(value = "日期标题月月月", test = "dayMonthType ==2")
    })
    private Date date;
    @ExcelProperty(value = "数字标题", multi = {
            // 根据参数输出指定Head
            @ExcelPropertyConditionMulti(value = "数字标题-重量>5", test = "weight > 5"),
            @ExcelPropertyConditionMulti(value = "数字标题-重量>10", test = "weight > 10")
    })
    private Double doubleData;

    @ExcelProperty(value = "年龄分布", multi = {
        // 可以定义多个Head，根据test条件判断使用
        @ExcelPropertyConditionMulti(value = "老年人", test = "age >=  1965-06-20 00:00:00 and age <  1985-06-20 00:00:00"),
        @ExcelPropertyConditionMulti(value = "中年人", test = "age >=  1985-06-20 00:00:00 and age <  1992-06-20 00:00:00"),
        @ExcelPropertyConditionMulti(value = "成年人", test = "age >= 1992-06-20 00:00:00 and age < 2005-06-20 00:00:00"),
        @ExcelPropertyConditionMulti(value = "未成年人", test = "age > 2005-06-20 00:00:00")
    })
    private String grownMan;

    /**
     * 原生注解，忽略这个字段
     */
    @ExcelProperty("忽略字段")
    @ExcelIgnoreCondition(test = "ignore!=null")
    private String ignore;
}
```

翻译示例，I18nTranslator.class

```java
// 以泰国翻译为例，路径为：yl-jmsth-ft-api com.lz.ft.api.utils.I18nTranslator
public class I18nTranslator implements Translator {
    /**
     * 目标语言（英文缩写，泰国:TH, 中国:CN）
     */
    private String language;

    private com.lz.platform.i18n.helper.YlI18nHelper ylI18nHelper;

    public I18nTranslator(String language) {
        this.language = language;
        this.ylI18nHelper = I18nUtils.getIn18nHelper();
        //获取翻译表头，就在本地线程设置翻译语言类型
        I18nUtils.threadLgType.set(language);
        //每个线程每次导入，重新缓存一下
        I18nUtils.threadTranMap.set(new HashMap<>());
    }

    /**
     * 
     * @param t @ExcelProperty里的value值
     * @return
     */
    @Override
    public String[] apply(String[] t) {
        if (this.ylI18nHelper == null) {
            return t;
        }
        for (int i = 0; i < t.length; i++) {
            // 这里开始翻译
            t[i] = this.ylI18nHelper.conver(t[i], language);
        }
        return t;
    }
    
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public YlI18nHelper getYlI18nHelper() {
        return ylI18nHelper;
    }

    public void setYlI18nHelper(YlI18nHelper ylI18nHelper) {
        this.ylI18nHelper = ylI18nHelper;
    }
}
```

**支持情况**

- `and`、`or` 前后需要有空格
- 支持数值类型大小比较，如：int、long、double、float、BigDecimal
- 支持 String 类型相等不等比较
- 支持 null 值判断
- 支持时间类型：Date、LocalDate、LocalDateTime
- 支持括号嵌套


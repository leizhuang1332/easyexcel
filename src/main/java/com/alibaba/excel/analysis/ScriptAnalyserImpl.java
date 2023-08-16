package com.alibaba.excel.analysis;

import com.alibaba.excel.strategy.BigDecimalRelationalOperator;
import com.alibaba.excel.strategy.DateRelationalOperator;
import com.alibaba.excel.strategy.LocalDateRelationalOperator;
import com.alibaba.excel.strategy.LocalDateTimeRelationalOperator;
import com.alibaba.excel.strategy.RelationalOperatorContext;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.util.StringUtils;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author leizhuang
 */
public class ScriptAnalyserImpl implements ScriptAnalyser {
    /**
     * 脚本
     */
    private String script;
    /**
     * 导出参数
     */
    private Map<String, Object> param;
    private static final String EXPRESSION_AND = " and ";
    private static final String EXPRESSION_AND_1 = " && ";
    private static final String EXPRESSION_OR = " or ";
    private static final String EXPRESSION_OR_1 = " || ";
    private static final String EQ="==";
    private static final String NE="!=";

    /**
     * 匹配所有表达式
     */
    private static final String EXPRESSION_REGEX = "\\(|\\)|" + EXPRESSION_AND + "|" + EXPRESSION_OR;

    /**
     * 关系运算符正则表达式
     */
    private static final String RELATIONAL_OPERATOR_REGEX = "/|==|!=|>=|>|<=|<|/";

    public ScriptAnalyserImpl(String script, Map<String, Object> param) {
        this.script = script;
        this.param = param;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    /**
     * 解析脚本
     * @return
     */
    public ScriptAnalyserImpl parse() {
        // 获取所有表达式
        List<String> allExpression = getAllExpression();
        // 解析所有表达式
        List<String> parsedResult = parseExpression(allExpression);
        for (int i = 0; i < allExpression.size(); i++) {
            // 将表达式替换为真实结果（ture,false)）
            this.script = this.script.replace(allExpression.get(i), parsedResult.get(i));
        }
        // 将and替换为&&
        this.script = this.script.replace(EXPRESSION_AND, EXPRESSION_AND_1);
        // 将or替换||
        this.script = this.script.replace(EXPRESSION_OR, EXPRESSION_OR_1);
        return this;
    }

    /**
     * 计算
     * @return
     */
    public boolean eval() {
        Expression expression = new Expression(this.script);
        return expression.eval().compareTo(BigDecimal.ONE) == 0;
    }

    /**
     * 获取所有表达式
     */
    private List<String> getAllExpression() {
        List <String> allExpression= new LinkedList<String>();
        String[] relationalExpressions = this.script.split(ScriptAnalyserImpl.EXPRESSION_REGEX);
        for (String exp : relationalExpressions) {
            if (!StringUtils.isEmpty(exp)){
                allExpression.add(exp.trim());
            }
        }
        return allExpression;
    }

    /**
     * 解析表达式
     */
    private List<String> parseExpression(List<String> allExpression) {
        List<String> expressionsParsedResult = new LinkedList<String>();
        for (String expression : allExpression) {
            Pattern pattern = Pattern.compile(ScriptAnalyserImpl.RELATIONAL_OPERATOR_REGEX);
            Matcher matcher = pattern.matcher(expression);
            // 关系运算符
            String relationalOperator = null;
            while (matcher.find()) {
                relationalOperator = matcher.group();
            }
            String[] split = expression.split(ScriptAnalyserImpl.RELATIONAL_OPERATOR_REGEX);
            // column
            String column = split[0].trim();
            // 表达式值
            String conditionalData = split[1].trim();
            // 真实数据值
            Object realData = this.param.get(column);
            String result;
            if (realData != null && !StringUtils.isNull(conditionalData)) {
                if (realData instanceof String){
                    if (EQ.equals(relationalOperator)){
                        result = conditionalData.equals(realData) + "";
                    } else {
                        result = !conditionalData.equals(realData) + "";
                    }
                } else if (realData instanceof BigDecimal){
                    RelationalOperatorContext<BigDecimal> context = new RelationalOperatorContext<>(new BigDecimalRelationalOperator());
                    result = context.eval(relationalOperator, (BigDecimal) realData, new BigDecimal(conditionalData)) + "";
                } else if (realData instanceof Date) {
                    try {
                        RelationalOperatorContext<Date> context = new RelationalOperatorContext<>(new DateRelationalOperator());
                        result = context.eval(relationalOperator, (Date)realData, DateUtils.parseDate(conditionalData)) + "";
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else if (realData instanceof LocalDate) {
                    LocalDate localDate = LocalDate.parse(conditionalData, DateTimeFormatter.ofPattern(DateUtils.switchDateFormat(conditionalData)));
                    RelationalOperatorContext<LocalDate> context = new RelationalOperatorContext<>(new LocalDateRelationalOperator());
                    result = context.eval(relationalOperator, (LocalDate)realData, localDate) + "";
                } else if (realData instanceof LocalDateTime) {
                    LocalDateTime localDateTime;
                    try {
                        localDateTime = LocalDateTime.parse(conditionalData, DateTimeFormatter.ofPattern(DateUtils.switchDateFormat(conditionalData)));
                    } catch (DateTimeParseException e) {
                        localDateTime = LocalDate.parse(conditionalData, DateTimeFormatter.ofPattern(DateUtils.switchDateFormat(conditionalData))).atTime(0, 0, 0);
                    }
                    RelationalOperatorContext<LocalDateTime> context = new RelationalOperatorContext<>(new LocalDateTimeRelationalOperator());
                    result = context.eval(relationalOperator, (LocalDateTime)realData, localDateTime) + "";
                } else {
                    result = realData + relationalOperator + conditionalData;
                }
            } else {
                // null值判断处理
                result = eqNull(relationalOperator, realData, conditionalData) + "";
            }
            expressionsParsedResult.add(result);
        }
        return expressionsParsedResult;
    }

    private boolean eqNull(String relationalOperator, Object realData, String conditionalData) {
        boolean b1 = EQ.equals(relationalOperator) && StringUtils.isNull(conditionalData) && realData == null;
        boolean b2 = NE.equals(relationalOperator) && (!StringUtils.isNull(conditionalData) || realData != null);
        return b1 || b2;
    }
}

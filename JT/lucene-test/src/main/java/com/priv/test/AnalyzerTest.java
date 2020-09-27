package com.priv.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

/**
 * 测试Lucene的分词器接口
 * 利用不同实现类对同一字符串分词计算
 *
 * Lucene相关分词器：
 * standarAnalyzer
 * simpleAnalyzer
 * smartChineseAnalyzer
 * whitspaceAnalyzer
 */
public class AnalyzerTest {

    public void printAnalyzer(Analyzer analyzer, String msg) throws IOException {
        //分词器最终都是处理流数据，先将msg转化成字符串流
        StringReader stringReader = new StringReader(msg);

        TokenStream tokenStream = analyzer.tokenStream("test",stringReader);

        tokenStream.reset();

        CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);

        while (tokenStream.incrementToken()){
            System.out.println(charTermAttribute.toString());
        }
    }

    @Test
    public void test1(){
        System.out.println("!");

        String msg = "北京科东配电技术分公司,运维管理系统研发分部";
        try{
            printAnalyzer(new StandardAnalyzer(), msg);
            printAnalyzer(new SimpleAnalyzer(), msg);
            printAnalyzer(new WhitespaceAnalyzer(), msg);
            printAnalyzer(new SmartChineseAnalyzer(), msg);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

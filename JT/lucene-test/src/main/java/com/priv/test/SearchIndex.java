package com.priv.test;

import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 测试Lucene不同的查询功能
 */
public class SearchIndex {

    /*
        词项查询
     */
    @Test
    public void termQuery() throws IOException {
        //指定路径
        Path path = Paths.get("./index01");
        FSDirectory directory = FSDirectory.open(path);

        //读取索引文件流，构造查询条件
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        //可自定义分词器

        //构造查询条件query
        Term term = new Term("name", "华为");
        Query query = new TermQuery(term);

        //调用查询方法，获取结果，解析遍历打印
        //从索引中搜索时，会进行评分，获取结果放到TopDocs中
        TopDocs topDocs = searcher.search(query, 10);//查询前10条

        //解析数据，获取document评分+document ID  的 集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs
             ) {
            int docId = scoreDoc.doc;//获取document的id
            Document doc = searcher.doc(docId); //根据id查询document

            System.out.println("id为：" + docId + "的评分是：" + scoreDoc.score);

            //打印所有document的所有阈值，没有的就会输出空
            System.out.println("name:" + doc.get("name"));
            System.out.println("price:" + doc.get("price"));
            System.out.println("img:" + doc.get("img"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("content:" + doc.get("content"));
        }

    }



    /*
        多域查询
     */
    @Test
    public void multiFieldQuery() throws ParseException, IOException {

        //指定查询的多个域
        String[] fields = {"name", "price"};
        String[] queries = {"三星的手机哈哈哈","8888"};

        //利用分词器，和域数组生成一个解析查询关键字对象

        /*
            下面这种写法必须要fields的长度和queries的长度相等，不知道为什么
         */
//        Query query = MultiFieldQueryParser.parse(queries, fields, new SmartChineseAnalyzer());

        /*
            流程：
                使用SmartChineseAnalyzer解析三星的手机8888，然后得出词项term
                使用term去匹配fields，匹配中了则找到document
                查询结果 匹配度=评分。假设一个fileds包含了多个term，那么肯定匹配度高，评分则高
         */
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new SmartChineseAnalyzer());
        Query query1 = parser.parse("三星的手机8888");
        common(query1);


    }

    /*
        布尔查询
     */
    @Test
    public void booleanQuery() throws ParseException, IOException {

        Query query1 = new TermQuery(new Term("name", "三星"));
        Query query2 = new TermQuery(new Term("price", "1500"));

        //定义布尔子条件的逻辑值
        /*
        MUST : 必须是
        MUST_NOT ： 必须不是
        SHOULD ： 所有的要，并集
        FILTER ： 和MUST一样的，但是FILTER的查询不计算评分，FILTER出来的评分都为0
         */

        BooleanClause bc1 = new BooleanClause(query1, BooleanClause.Occur.MUST);
        BooleanClause bc2 = new BooleanClause(query2, BooleanClause.Occur.MUST_NOT);

        Query query = new BooleanQuery.Builder().add(bc1).add(bc2).build();

        common(query);

    }

    /*
        范围查询
     */
    @Test
    public void rangeQuery() throws IOException {

        Query query = DoublePoint.newRangeQuery("price", 999, 1500);

        common(query);

    }



    public void common(Query query) throws IOException {
        //指定路径
        Path path = Paths.get("./index01");
        FSDirectory directory = FSDirectory.open(path);

        //读取索引文件流，构造查询条件
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        //调用查询方法，获取结果，解析遍历打印
        //从索引中搜索时，会进行评分，获取结果放到TopDocs中
        TopDocs topDocs = searcher.search(query, 10);//查询前10条

        //解析数据，获取document评分+document ID  的 集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs
        ) {
            int docId = scoreDoc.doc;//获取document的id
            Document doc = searcher.doc(docId); //根据id查询document

            System.out.println("id为：" + docId + "的评分是：" + scoreDoc.score);

            //打印所有document的所有阈值，没有的就会输出空
            System.out.println("name:" + doc.get("name"));
            System.out.println("price:" + doc.get("price"));
            System.out.println("img:" + doc.get("img"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("content:" + doc.get("content"));
        }
    }


}

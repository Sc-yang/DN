package com.priv.test;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 利用Lucene创建索引
 */
public class CreateIndex {

    @Test
    public void createindex() throws IOException {
        //指定当前系统索引输出输入位置
        Path path = Paths.get("./index01");
        FSDirectory fsDirectory = FSDirectory.open(path);

        //配置对象，为输出做配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new SmartChineseAnalyzer());

        // 使用config设置索引文件操作
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        //输出对象
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

        //在输出流中，编写一批document对象使用
//        Document doc1 = new Document();
        Document doc2 = new Document();

        //为Document封装源数据读取过来的内容，doc1是新闻，doc2是
        //doc1 新闻 title(String) content(String)
        //doc2 商店 name(String),price(Double),category(String)

//        doc1.add(new TextField("title","新闻的标题", Field.Store.YES));
//        doc1.add(new TextField("content","这期的新闻内容", Field.Store.NO));
//        doc2.add(new TextField("name","华为p40pro+", Field.Store.YES));
//        doc2.add(new DoublePoint("price",8888));
//        doc2.add(new StringField("price","8888", Field.Store.YES));
//        doc2.add(new StringField("img","这是一张图片", Field.Store.YES));

//        doc2.add(new TextField("name","三星T5", Field.Store.YES));
//        doc2.add(new DoublePoint("price",1000));
//        doc2.add(new StringField("price","1000", Field.Store.YES));
//        doc2.add(new StringField("img","三星T5红色的图片", Field.Store.YES));

//        doc2.add(new TextField("name","三星T7", Field.Store.YES));
//        doc2.add(new DoublePoint("price",1500));
//        doc2.add(new StringField("price","1500", Field.Store.YES));
//        doc2.add(new StringField("img","三星T7红色的图片", Field.Store.YES));

        doc2.add(new TextField("name","华为手机", Field.Store.YES));
        doc2.add(new DoublePoint("price",1500));
        doc2.add(new StringField("price","1500", Field.Store.YES));
        doc2.add(new StringField("img","华为手机多个颜色的图片", Field.Store.YES));


        //输出对象携带Document
//        indexWriter.addDocument(doc1);
        indexWriter.addDocument(doc2);

        //提交
        indexWriter.commit();
    }


}

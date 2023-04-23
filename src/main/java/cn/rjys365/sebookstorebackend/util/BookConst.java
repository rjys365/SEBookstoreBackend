//Constants for books. Will be deleted after JPA/JDBC implementation.

package cn.rjys365.sebookstorebackend.util;

import cn.rjys365.sebookstorebackend.datatypes.Book;

public class BookConst {
    public static final Book[] books = {
            new Book(1,"一九八四","乔治·奥威尔","上海人民出版社",10, 20.0, "/images/1984.jpg", "《1984》是英国作家乔治·奥威尔于1948年至1949年间所写的反乌托邦小说，是奥威尔的代表作之一。该书讲述了一个极权主义的未来世界，政府控制一切，人们的思想、言论、行为都受到严密的监控，人们的生活被严格的规定所限制，人们的思想被洗脑，人们的记忆被操纵。"),
            new Book(2,"红楼梦","曹雪芹","人民文学出版社",0, 30.0, "/images/hlm.jpg", "《红楼梦》是中国古典四大名著之一，是中国古典文学的杰出代表作，也是中国现代文学的开山之作。"),
            new Book(3,"三国演义","罗贯中","人民文学出版社",5, 25.0, "/images/sgyy.jpg", "《三国演义》是中国古典四大名著之一，是中国古典文学的杰出代表作，也是中国现代文学的开山之作。"),
            new Book(4,"深入理解计算机系统","Randal E.Bryant","机械工业出版社",10, 20.0, "/images/csapp.jpg", "《深入理解计算机系统》是一本计算机系统原理的经典教材，由Randal E.Bryant和David R.O\"Hallaron合著，由机械工业出版社出版。"),
            new Book(5,"编译原理","Alfred V.Aho","机械工业出版社",20, 20.0, "/images/cptt.jpg", "《编译原理》是一本计算机系统原理的经典教材，由Randal E.Bryant和David R.O\"Hallaron合著，由机械工业出版社出版。"),
    };
}

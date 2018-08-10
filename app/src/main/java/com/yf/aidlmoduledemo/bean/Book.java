package com.yf.aidlmoduledemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FileName :  Book
 * Author   :  zhizhongbiao
 * Date     :  2018/8/10
 * Describe :
 */

public class Book implements Parcelable {

    public String name;
    public String author;
    public double price;
    public int bookId;


    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", bookId=" + bookId +
                '}';
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        price = in.readDouble();
        bookId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeDouble(price);
        dest.writeInt(bookId);
    }

    /**
     *      但是请注意，这里有一个坑：默认生成的模板类的对象只支持为 in 的定向 Tag 。
     *      为什么呢？因为默认生成的类里面只有 writeToParcel() 方法，而如果要支持为 out 或者 inout 的定向 tag 的话
     *      ，还需要实现 readFromParcel() 方法——而这个方法其实并没有在 Parcelable 接口里面，所以需要我们从头写。
     *      当有了该方法之后，我们的Aidl接口文件中的方法的参数Tag就可以定义为out或者inout类型了。
     */

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     *
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = dest.readString();
        author = dest.readString();
        price = dest.readDouble();
        bookId = dest.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}

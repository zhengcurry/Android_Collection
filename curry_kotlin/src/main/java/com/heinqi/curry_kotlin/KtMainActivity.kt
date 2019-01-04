package com.heinqi.curry_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_kt_main.*

/**
 *Kotlin学习
 */
class KtMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_main)


        /**
         * 可以直接引用布局中的id，直接设置
         */
        tv_test.setText("这是自己设置的TextView文本1");
        tv_test.text = "这是自己设置的TextView文本2"
    }


}

/**
 * 主程序入口
 */
fun main(args: Array<String>) {
    print("sum of 3 and 5 is ")
    println(sum1(1, 1))
    println("sum2 " + sum2(1, 2))
    println("sum of 19 and 23 is ${sum1(19, 23)}")
    sum3(-1, 8)

    //一次赋值（只读）的局部变量
    val val1: Int = 1   // 立即赋值
    val val3 = 3        // 自动推断出 `Int` 类型
    val val2: Int       // 如果没有初始值类型不能省略
    val2 = 2            // 明确赋值
    println("a = $val1, b = $val3, c = $val2")


    var test1: Int
}

fun sum1(a: Int, b: Int) = a + b

fun sum2(int0: Int, int1: Int): Int {
    return int0 + int1
}

/**
 * 函数返回无意义的值：Unit(返回类型可以省略)
 */
fun sum3(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}


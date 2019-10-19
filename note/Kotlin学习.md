#### [Android 与 Kotlin 入门](https://www.kotlincn.net/docs/tutorials/kotlin-android.html)

#### 入门
手动配置如下：  
```
apply plugin: 'kotlin-android'  
api "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

ext.kotlin_version = '1.2.71'  
classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
```
当用AS自动创建一个kotlin的文件后，会自动提醒进行自动配置；

#### 1. 使用Kotlin安卓扩展 View-Binding

(创建时直接选中kotlin会自动配置)  
手动配置：  
需要在build.gradle中配置依赖apply plugin: 'kotlin-android-extensions'
在Activity中导入对应的布局文件import kotlinx.android.synthetic.main.activity_kt_main.*，就可以通过控件的id直接调用方法；

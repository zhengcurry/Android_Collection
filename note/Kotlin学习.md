#### [Android 与 Kotlin 入门](https://www.kotlincn.net/docs/tutorials/kotlin-android.html)

#### 1. 使用Kotlin安卓扩展
(创建时直接选中kotlin会自动配置)需要在build.gradle中配置依赖apply plugin: 'kotlin-android-extensions'
在Activity中导入对应的布局文件import kotlinx.android.synthetic.main.activity_kt_main.*，就可以通过控件的id直接调用方法；

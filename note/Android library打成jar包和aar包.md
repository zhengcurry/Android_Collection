#### 打成jar包

`首先要确保module是library，才可以生成jar包`

1. 在build.gradle中添加代码块

```
apply plugin: 'com.android.library'

android {
   ...
}

def _BASENAME = "MakeJar"
def _VERSION = "_V1.1"
//生成jar包的位置
def _DestinationPath = "build"
//待打包文件位置--不同的studio版本，路径不一样(设置拷贝的文件)
def zipFile = file('build/intermediates/intermediate-jars/release/classes.jar')

//删除已存在的版本
task deleteBuild(type: Delete) {
    delete _DestinationPath + _BASENAME + _VERSION + ".jar"
}

task makeJar(type: Jar) {
    from zipTree(zipFile)
    from fileTree(dir: 'src/main', includes: ['assets/**']) //将assets目录打入jar包
    baseName = _BASENAME + _VERSION
    destinationDir = file(_DestinationPath)
}

makeJar.dependsOn(deleteBuild, build)
```

2. 配置完成，可以在命令行Terminal中执行gradlew makejar命令 或者
在Gradle中点击 :对应library名称-->Tasks-->other-->makeJar; 提示BUILD SUCCESSFUL，即打包成功；

个人理解：即便不设置代码，也可以生成jar包，目录跟androidstudio的版本有关
（目前版本3.2.1，执行makeJar之后会在build/intermediates/intermediate-jars/release中生成class.jar），
这个jar包跟我们需要的几乎一样，仅缺少MANIFEST.MF。所以gradle中的代码，主要起到 复制 和 自动命名配置 的作用


#### 二. 打成aar包

`首先仍要确保module是library，才可以生成aar包(否则执行assemble 只会生成apk)`

1. 执行 对应library名称-->build-->assemble;成功后会在build/outputs文件夹中生成aar文件夹，包含debug.aar和release.aar
2. 使用release.aar，放在主项目的lib中，build.gradle中添加如下代码
```
repositories {
    flatDir { dirs 'libs' }
}

dependencies {
    ...
    implementation(name: 'testaarlibrary-release', ext: 'aar')
}
```


#### 三. 注意：
当需要把主module打成jar包或者aar时，需要改成library，并且把applicationId 注释掉；然后执行上述步骤。
```
  //apply plugin: 'com.android.application'
  apply plugin: 'com.android.library'

  android {
    compileSdkVersion config.compileSdkVersion
    defaultConfig {
          //        applicationId "com.curry.android.android_collection"
          ...
  }
```
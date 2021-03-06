# [关于LitePal](https://github.com/LitePalFramework/LitePal "github地址")
    1. api 'org.litepal.android:java:3.0.0'
    2. 在assets中生成litepal.xml文件；具体内容查看litepal.xml
    3. application继承LitePalApplication  或者添加：LitePal.initialize(this);


- **数据库相关（`表关联`）：**
	- 一对一：数据表根据外键进行关联（哪个表添加外键都可以）；两个实体类相互持有对方的引用；
	- 多对一：数据表根据外键进行关联（多方添加外键）；通过集合类；
	- 多对多：添加中间表存放两表的id；通过集合的多持有;
- **对象关系映射的数据类型：** int、short、long、float、double、boolean、String和Date
- **建表：**
	- 新建实体类，即数据库表；
	- 根据对象关系映射，以上的数据类型都会映射到数据库表中;
	- 然后在litepal.xml中配置模型类的类名
	- 执行SQLiteDatabase db = Connector.getDatabase();操作数据后，即可生成；
- **升级表：** 仅需要添加需要新增的模型类，然后在xml中配置即可【版本号要加1】
- **增、删、改、差：** 都可以通过LitePal进行操作；或者对实体类进行操作，注意删除功能要对已经持久化过的实体类进行删除才起作用；可以通过isSaved判断实体类是否持久化。
- **聚合函数：**
    - litepal.count(testbean.class);//统计行数
	- litepal.sum(testbean.class, "test", int.class);//求和
	- litepal.average(testbean.class, "test");//求平均值
	- litepal.max(testbean.class, "test", int.class);//求最大
	- litepal.min(testbean.class, "test", int.class);//求最小


## ***其他：*** ##
SQLite不支持删除列的命令吗？那LitePal又是怎样做到的呢？

其实LitePal并没有删除任何一列，它只是先将comment表重命名成一个临时表，然后根据最新的Comment类的结构生成一个新的comment表，
再把临时表中除了publishdate之外的数据复制到新的表中，最后把临时表删掉。
因此，看上去的效果好像是做到了删除列的功能。


#### PS:

`在module中也可正常使用，xml放在module中的assets文件中，主工程是可以正常调用的。`
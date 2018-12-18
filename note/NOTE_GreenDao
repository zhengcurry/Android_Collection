# GreenDao
ORM 对象关系型数据库

## [用法（点我）](http://greenrobot.org/greendao/documentation/introduction/)

**1. 添加依赖**

        // In your root build.gradle file:
        buildscript {
            repositories {
                jcenter()
                mavenCentral() // add repository
            }
            dependencies {
                classpath 'com.android.tools.build:gradle:3.1.1'
                classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin
            }
        }

        //可以把以下部分放在module中
        // In your app projects build.gradle file:
        apply plugin: 'org.greenrobot.greendao' // apply plugin

        dependencies {
            implementation 'org.greenrobot:greendao:3.2.2' // add library
        }

**2. 配置greendao（`在添加依赖的module中`）**

        android {
            ...
        }

        greendao {
            //当前版本
            schemaVersion 1
            // set package of generated classes
            daoPackage "com.curry.db.dao"
            //自动生成的代码存储的路径，默认是 build/generated/source/greendao.
            targetGenDir 'src/main/java'
            //true的时候自动生成测试单元
            generateTests false
            //测试单元的生成目录默认是 src / androidTest / java
            //targetGenDirTests:
        }

**3. 构建实体类**

        @Entity(
                // If you have more than one schema, you can tell greenDAO
                // to which schema an entity belongs (pick any string as a name).
        //        schema = "myschema",//不常用

                // Flag to make an entity "active": Active entities have update,
                // delete, and refresh methods.
                active = true,//不常用--会自动生成更新、删除、刷新的代码

                // Specifies the name of the table in the database.
                // By default, the name is based on the entities class name.
                nameInDb = "AWESOME_USERS",//声明了该表数据库中的表名

                // Define indexes spanning multiple columns here.
                indexes = {
                        @Index(value = "name DESC", unique = true)
                },

                // Flag if the DAO should create the database table (default is true).
                // Set this to false, if you have multiple entities mapping to one table,
                // or the table creation is done outside of greenDAO.
                createInDb = true,//不常用

                // Whether an all properties constructor should be generated.
                // A no-args constructor is always required.
                generateConstructors = true,//不常用

                // Whether getters and setters for properties should be generated if missing.
                generateGettersSetters = true//不常用
        )
        public class User {
            @Id(autoincrement = true)
            private Long id; //推荐使用long型

            @Index(unique = true)
            private String index;//指定自己的索引；并表示唯一
        //    @Unique String index;

            @Property(nameInDb = "USERNAME")
            private String name;

            @NotNull
            private int repos;

            @Transient//表示不存储在数据库中
            private int tempUsageCount; // not persisted

**4. 执行make project**

    会在daoPackage对应配置的目录中自动生成3个文件：DaoMaster、DaoSession、[实体类名]Dao;
    如果在@Entity注解中配置了active = true，会自动生成升级、删除、刷新的方法

package com.curry.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.curry.db.dao.DaoSession;
import com.curry.db.dao.UserDao;

/**
 * @Desc : 实体类
 * @Author : curry
 * @Date : 2018/12/18
 * @Update : 2018/12/18
 * @Annotation :
 * the @Entity annotation marks a Java class as a presistable entity for greenDAO.
 * 注意：仅支持Java类。
 */

@Entity(
        // If you have more than one schema, you can tell greenDAO
        // to which schema an entity belongs (pick any string as a name).
//        schema = "myschema",//不常用

        // Flag to make an entity "active": Active entities have update,
        // delete, and refresh methods.
        active = true,//不常用

        // Specifies the name of the table in the database.
        // By default, the name is based on the entities class name.
        //声明了该表数据库中的表名
        nameInDb = "AWESOME_USERS",

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
    private Long id; //

    @Index(unique = true)
    private String index;//指定自己的索引；并表示唯一
//    @Unique String index;

    @Property(nameInDb = "USERNAME")
    private String name;

    @NotNull
    private int repos;

    @Transient//表示不存储在数据库中
    private int tempUsageCount; // not persisted

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    @Generated(hash = 166591091)
    public User(Long id, String index, String name, int repos) {
        this.id = id;
        this.index = index;
        this.name = name;
        this.repos = repos;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getRepos() {
        return this.repos;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}

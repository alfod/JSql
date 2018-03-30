package me.alfod.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author Yang Dong
 * @createTime 2017/10/31  11:25
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/10/31  11:25
 * @note
 */
public class AxxMongoTemplate extends MongoTemplate {


    /**
     * Constructor used for a basic template configuration
     *
     * @param mongo        must not be {@literal null}.
     * @param databaseName must not be {@literal null} or empty.
     */
    public AxxMongoTemplate(Mongo mongo, String databaseName) {
        super(mongo, databaseName);
    }

    /**
     * Constructor used for a template configuration with user credentials in the form of
     * {@link org.springframework.data.authentication.UserCredentials}
     *
     * @param mongo           must not be {@literal null}.
     * @param databaseName    must not be {@literal null} or empty.
     * @param userCredentials
     */
    public AxxMongoTemplate(Mongo mongo, String databaseName, UserCredentials userCredentials) {
        super(mongo, databaseName, userCredentials);
    }

    /**
     * Constructor used for a basic template configuration.
     *
     * @param mongoDbFactory must not be {@literal null}.
     */
    public AxxMongoTemplate(MongoDbFactory mongoDbFactory) {
        super(mongoDbFactory, null);
    }

    /**
     * Constructor used for a basic template configuration.
     *
     * @param mongoDbFactory must not be {@literal null}.
     * @param mongoConverter
     */
    public AxxMongoTemplate(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter) {
        super(mongoDbFactory, mongoConverter);
    }

    public WriteResult updateFirstNotNull(Query query, Object object, String collectionName) {
        return updateFirstNotNull(query, object, null, collectionName);
    }

    public WriteResult updateFirstNotNull(Query query, Object object, Class<?> entityClass) {
        return updateFirstNotNull(query, object, entityClass, null);
    }


    public WriteResult updateFirstNotNull(Query query, Object object, Class<?> entityClass, String collectionName) {
        return super.updateFirst(query, getUpdateNotNull(object, false), entityClass, collectionName);
    }

    public WriteResult upsertNotNull(Query query, Object object, String collectionName) {
        return upsertNotNull(query, object, null, collectionName);
    }

    public WriteResult upsertNotNull(Query query, Object object, Class<?> entityClass) {
        return upsertNotNull(query, object, entityClass, null);
    }
    public WriteResult upsert(Query query, Object object, Class<?> entityClass) {
        return upsert(query, object, entityClass, null);
    }
    public WriteResult upsert(Query query, Object object, String collectionName) {
        return upsert(query, object, null, collectionName);
    }

    private Update updateConvert(Object object, boolean nullable) {
        Update update = new Update();
        if (object instanceof Map) {
            Object value;
            for (Object key : (((Map) object).keySet())) {
                if (key == null || "_id".equals(key)) {
                    continue;
                }
                value = ((Map) object).get(key);
                if (value != null || nullable) {
                    update.set(key.toString(), value);
                }
            }
            return update;
        }
        Class clazz = object.getClass();
        Field[] fields;
        int modifier;
        Object value;
        try {
            while (clazz != Object.class) {
                fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    modifier = field.getModifiers();
                    if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) {
                        continue;
                    }
                    value = field.get(object);
                    if (value != null || nullable) {
                        update.set(field.getName(), value);
                    }
                }
                clazz=clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return update;
    }

    private Update getUpdateNotNull(Object object, boolean nullable) {
        Update update = new Update();
        DBObject dbObject = new BasicDBObject();
        super.getConverter().write(object, dbObject);
        Object value;
        for (String key : dbObject.keySet()) {
            if (key != null && !"_id".equals(key)) {
                value = dbObject.get(key);
                if (value != null || nullable) {
                    update.set(key, value);
                }
            }
        }
        return update;
    }


    public WriteResult upsertNotNull(Query query, Object object, Class<?> entityClass, String collectionName) {
        return super.upsert(query, getUpdateNotNull(object, false), entityClass, collectionName);
    }

    public WriteResult upsert(Query query, Object object, Class<?> entityClass, String collectionName) {
        return super.upsert(query, updateConvert(object,true), entityClass, collectionName);
    }
}

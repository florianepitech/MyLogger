package fr.florian.tracex.appenders;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.florian.tracex.TraceX;
import fr.florian.tracex.TraceListener;
import fr.florian.tracex.enums.Priority;
import fr.florian.tracex.objects.TraceMessage;
import org.bson.Document;
import org.json.JSONObject;
import org.w3c.dom.NodeList;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoAppender implements TraceListener {

    private static boolean start = false;
    private static String mongoUrl, databaseName, collectionName;
    private static int saveLevel = 0;

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static MongoCollection mongoCollection;

    public MongoAppender(TraceX traceX, String mongoUrl, String databaseName, String collectionName) {

    }

    public MongoAppender(TraceX traceX, NodeList nodeList) {

    }

    public static void setConfiguration() {
        MongoAppender.mongoUrl = mongoUrl;
        MongoAppender.databaseName = databaseName;
        MongoAppender.collectionName = collectionName;
    }

    public static void start() throws Exception {
        if (start) throw new Exception("your logger no sql has already started");
        if (mongoUrl == null || databaseName == null || collectionName == null)
            throw new Exception("you must configure the mongodb informations before launching the logger");
        disableMongoLogger();
        mongoClient = new MongoClient(new MongoClientURI(MongoAppender.mongoUrl));
        mongoDatabase = mongoClient.getDatabase(databaseName);
        mongoCollection = mongoDatabase.getCollection(collectionName);
        start = true;
    }

    public static void stop() throws Exception {
        if (!start) throw new Exception("logger no sql is already stopped");
        mongoClient.close();
        mongoDatabase = null;
        mongoCollection = null;
        start = false;
    }

    private static void disableMongoLogger() {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.OFF);
    }

    /*
     *      LOG
     */

    @Override
    public void onLogEvent(TraceMessage message) {
        if (message.getPriority().getLevel() < getSaveLevel()) return;
        Document document = new Document();
        document.append("type", message.getPriority().getName());
        document.append("pid", ProcessHandle.current().pid());
        document.append("date", Date.from(message.getZonedDateTime().toInstant()));
        if (message.getData() instanceof JSONObject) {
            document.append("message", Document.parse(message.toString()));
        } else {
            document.append("message", message.toString());
        }
        mongoCollection.insertOne(document);
    }

    public static void deleteAllLogs() {
        if (start) mongoCollection.deleteMany(new Document());
    }

    /*
     *      GETTER
     */

    public static boolean isStart() {
        return start;
    }

    public static String getMongoUrl() {
        return mongoUrl;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getCollectionName() {
        return collectionName;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public static MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public static int getSaveLevel() {
        return saveLevel;
    }

    public static void setSaveLevel(Priority logType) {
        MongoAppender.saveLevel = logType.getLevel();
    }

    public static void setSaveLevel(int saveLevel) {
        MongoAppender.saveLevel = saveLevel;
    }
}

package DIF

import com.mongodb.*
import com.mongodb.DBCursor
import com.mongodb.BasicDBObject
import com.mongodb.DBObject

class MongoDBController() : DatabaseController<Note> {

    private var collection: DBCollection

    init {
        val mongoClient = MongoClient(MongoClientURI("mongodb://admin:Password1234@ds237588.mlab.com:37588/notestore"))
        val database = mongoClient.getDB("notestore")
        collection = database.getCollection("notes")
    }

    override fun addEntity(entity: Note){
        val note = BasicDBObject("_id", entity.title)
                .append("content", entity.content)
        collection.insert(note)
    }

    override fun removeEntity(entity: Note) {
        removeEntity(entity.title)
    }

    override fun removeEntity(id: String) {
        val query = BasicDBObject("_id", id)
        collection.remove(query)
    }

    override fun getEntity(entity: Note): Note {
        return getEntity(entity.title)
    }

    override fun getEntity(id: String): Note {
        val query = BasicDBObject("_id", id)
        val cursor = collection.find(query)
        return Note(cursor.one().get("_id") as String, cursor.one().get("content") as String)
    }

    override fun getAll(): List<Note> {
        val cursor = collection.find()
        val list = ArrayList<Note>()
        for (dbObj in cursor) {
            list.add(Note(dbObj.get("_id") as String, dbObj.get("content") as String))
        }
        return list
    }
}
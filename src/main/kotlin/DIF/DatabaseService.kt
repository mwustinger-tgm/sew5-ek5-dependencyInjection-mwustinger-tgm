package DIF

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Autowired
annotation class AutoDatabaseConfig(val type: String)

class DatabaseService{

    @Value("\${database.type}")
    private val type: String? = null

    private var controller: DatabaseController<Note>? = null

    init {
        when (type) {
            "mongoDB" -> controller = MongoDBController()
            "couchDB" -> controller = CouchDBController()
        }
    }

    fun addEntity(entity: Note) {
        controller?.addEntity(entity)
    }

    fun removeEntity(entity: Note) {
        controller?.removeEntity(entity)
    }

    fun removeEntity(id: String) {
        controller?.removeEntity(id)
    }

    fun getEntity(entity: Note): Note? {
        return controller?.getEntity(entity)
    }

    fun getEntity(id: String): Note {
        return controller?.getEntity(id)!!
    }

    fun getAll(): List<Note> {
        return controller?.getAll()!!
    }
}
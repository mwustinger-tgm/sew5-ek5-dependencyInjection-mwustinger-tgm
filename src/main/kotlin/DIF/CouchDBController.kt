package DIF

import org.ektorp.CouchDbConnector
import org.ektorp.http.StdHttpClient
import org.ektorp.impl.StdCouchDbInstance

class CouchDBController() : DatabaseController<Note> {

    private var notes: NoteRepository

    init {
        val httpClient = StdHttpClient.Builder()
                .url("http://admin:Password1234@localhost:5984/")
                .build()

        val dbInstance = StdCouchDbInstance(httpClient)
        val db = dbInstance.createConnector("notestore", true)
        notes = NoteRepository(db)

    }
    override fun addEntity(entity: Note){
        notes.add(entity)
    }

    override fun removeEntity(entity: Note) {
        notes.remove(entity)
    }

    override fun removeEntity(id: String) {
        notes.remove(notes.findByTitle(id)[0])
    }

    override fun getEntity(entity: Note): Note {
        return getEntity(entity.title)
    }

    override fun getEntity(id: String): Note {
        return notes.findByTitle(id)[0]
    }

    override fun getAll(): List<Note> {
        return notes.all
    }

}
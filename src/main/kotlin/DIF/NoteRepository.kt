package DIF

import org.ektorp.CouchDbConnector
import org.ektorp.support.CouchDbRepositorySupport
import org.ektorp.support.GenerateView

class NoteRepository(db: CouchDbConnector) : CouchDbRepositorySupport<Note>(Note::class.java, db) {

    init {
        //The initStandardDesignDocument-method throws a NullPointerException when a view already exists in the database
        for (doc in db.allDocIds) {
            if (doc.contains("_design/"))
                db.delete(doc, db.getCurrentRevision(doc))
        }
        initStandardDesignDocument()
    }

    @GenerateView
    fun findByTitle(title: String?): List<Note> {
        return queryView("by_title", title)
    }

}
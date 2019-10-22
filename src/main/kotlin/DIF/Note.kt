package DIF

import org.ektorp.support.CouchDbDocument

class Note(var title: String, var content: String? = null): CouchDbDocument() {
}
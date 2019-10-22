package DIF

interface DatabaseController<T> {
    fun addEntity(entity: T)
    fun removeEntity(entity: T)
    fun removeEntity(id: String)
    fun getEntity(entity: T): T
    fun getEntity(id: String): T
    fun getAll(): List<T>
}
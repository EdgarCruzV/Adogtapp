/*package itesm.edu.adoptappv1.DataBase

import itesm.edu.adoptappv1.model.Paseadores.Paseadores
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PaseadorDao {
    @Query("SELECT *  FROM PaseadorFromDatabase")
    fun getAll():List<PaseadorFromDatabase>

    @Insert
    fun insertAll(vararg paseadores: PaseadorFromDatabase)
}
        */
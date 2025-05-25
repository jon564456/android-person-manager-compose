import android.content.Context
import androidx.room.Room
import com.jbrigido.creatinglists.Core.AppDatabase

object Database {

    @Volatile
    private var INSTACE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTACE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "people-register"
            ).build()
            INSTACE = instance
            instance
        }
    }

}
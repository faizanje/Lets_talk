package Room;
import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities= {RoomModelClass.class}, version = 1, exportSchema = false)
public abstract  class MyDatabase extends RoomDatabase {
    public abstract DAO dao();
}



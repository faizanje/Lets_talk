package Room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAO {
 @Insert
 public void  talkInsertion(List<RoomModelClass> obj);
 @Insert
 public void  singleInsertion(RoomModelClass obj);

 @Query("Select * from RoomModelClass")
 List<RoomModelClass> getTalkList();

 @Query("Delete from RoomModelClass where id=:btnID")
 void deleteBtn (int btnID);


 @Query("Delete from RoomModelClass where btnText=:btnID")
 void deleteBtnByText (String btnID);

 @Query("Delete from RoomModelClass")
 void deleteAllList ();
}

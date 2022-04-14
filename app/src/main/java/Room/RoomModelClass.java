package Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomModelClass {
 @PrimaryKey(autoGenerate = true)
 int id;
 int btnImage;
 String btnText;

    public RoomModelClass(int btnImage, String btnText) {
        this.btnImage = btnImage;
        this.btnText = btnText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBtnImage() {
        return btnImage;
    }

    public void setBtnImage(int btnImage) {
        this.btnImage = btnImage;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }
}

package Basic.API;

import android.graphics.Bitmap;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface getImage {
     void onSuccess(Bitmap bitmap);
    void onFailed(String reason);
}

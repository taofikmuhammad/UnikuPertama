package ac.id.uniku.projectsatu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBPengguna extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="project_baru.sqlite";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="pengguna";
    private static final String KEY_ID="id_pengguna";
    private static final String KEY_USERNAME="username";


    public DBPengguna(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public boolean isNull()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM "+TABLE_NAME+"";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        db.close();
        return icount <= 0;
    }

    public Pengguna findUser()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_USERNAME},null,null,null,null,null);

        Pengguna u=new Pengguna();
        if (cursor!=null && cursor.moveToFirst())
        {
            cursor.moveToFirst();
            u.setIdPengguna(cursor.getInt(cursor.getColumnIndex("id_pengguna")));
            u.setUsername(cursor.getString(cursor.getColumnIndex("username")));
        }else
        {
            u.setIdPengguna(0);
            u.setUsername("");
        }

        db.close();
        return u;
    }
}

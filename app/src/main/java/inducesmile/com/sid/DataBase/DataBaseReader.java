package inducesmile.com.sid.DataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by joao on 11/04/2018.
 */

public class DataBaseReader {

    SQLiteDatabase db;

    public DataBaseReader(DataBaseHandler dbHandler){
        db = dbHandler.getReadableDatabase();

    }

    // https://stackoverflow.com/questions/10600670/sqlitedatabase-query-method


    public Cursor ReadHumidadeTemperatura(String data){

        //ToDo
        if (data!=null){
            Log.d("dataString",data);
        }


        Cursor cursor = db.query(
                DataBaseConfig.HumidadeTemperatura.TABLE_NAME,   // Nome da tabela
                null,
                data,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readAlertas(String idCultura){

        String[] tableColumns = new String[] {
                DataBaseConfig.Alertas.COLUMN_NAME_IDALERTA
        };
        String where = DataBaseConfig.Alertas.COLUMN_NAME_AIDCultura+" = ?";
        String[] whereArgs = new String[] {idCultura};
        //Todo
        Cursor cursor = db.query(
                DataBaseConfig.Alertas.TABLE_NAME,   // Nome da tabela
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readCultura(String idCultura){
        //Todo

        String where = DataBaseConfig.Cultura.COLUMN_NAME_IDCULTURA+" = ?";
        String[] whereArgs = new String[] {idCultura};
        Cursor cursor = db.query(
                DataBaseConfig.Cultura.TABLE_NAME,   // Nome da tabela
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return cursor;
    }



}

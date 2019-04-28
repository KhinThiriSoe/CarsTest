package com.sevenpeakssoftware.khinthirisoe.data.db

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import org.greenrobot.greendao.database.Database

class DbOpenHelper(context: Context, name: String) : DaoMaster.OpenHelper(context, name) {

    override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
        Log.d(
            TAG,
            "Upgrading schema from version $oldVersion to $newVersion by dropping all tables"
        )
        dropAllTables(db, true)
        onCreate(db)
    }
}

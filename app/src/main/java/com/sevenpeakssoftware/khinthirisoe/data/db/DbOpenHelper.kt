package com.sevenpeakssoftware.khinthirisoe.data.db

import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.data.db.model.DaoMaster
import com.sevenpeakssoftware.khinthirisoe.data.db.model.DaoMaster.dropAllTables
import org.greenrobot.greendao.database.Database
import timber.log.Timber

class DbOpenHelper(context: Context, name: String) : DaoMaster.OpenHelper(context, name) {

    override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
        Timber.d(
            "Upgrading schema from version $oldVersion to $newVersion by dropping all tables"
        )
        dropAllTables(db, true)
        onCreate(db)
    }
}

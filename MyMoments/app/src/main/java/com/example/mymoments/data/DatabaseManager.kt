package com.example.mymoments.data

import android.provider.BaseColumns

object DatabaseManager {

    object MomentEntry: BaseColumns{

        const val TABLE_NAME = "moments"

        const val _ID = BaseColumns._ID

        const val COLUMN_DATE = "date"

        const val COLUMN_TITLE = "title"

        const val COLUMN_MOMENT = "moment"





    }

}
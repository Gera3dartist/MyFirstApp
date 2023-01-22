package com.example.myfirstapp

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class DbHandler (context: Context, name: String?,
                 factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_SENSORS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_SENSOR_NAME
                + " TEXT," + COLUMN_SENSOR_VALUE + " INTEGER" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSORS)
        onCreate(db)

    }

    fun addSensor(sensor: Sensor) {
        val values = ContentValues()
        values.put(COLUMN_SENSOR_NAME, sensor.name)
        values.put(COLUMN_SENSOR_VALUE, sensor.value)

        val db = this.writableDatabase

        db.insert(TABLE_SENSORS, null, values)
        db.close()
    }

    fun findSensor(sensorName: String): Sensor? {
        val query =
            "SELECT * FROM $TABLE_SENSORS WHERE $COLUMN_SENSOR_NAME =  \"$sensorName\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        var sensor: Sensor? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val value = Integer.parseInt(cursor.getString(2))
            sensor = Sensor(id, name, value)
            cursor.close()
        }

        db.close()
        return sensor
    }

    fun listSensors(limit: Int = 10, offset:Int = 0): Collection<Sensor> {
        val query =
            "SELECT * FROM $TABLE_SENSORS ORDER BY  \"$COLUMN_ID\" DESC LIMIT $limit OFFSET $offset"

        val db = this.readableDatabase
        val cursor = db.rawQuery( query, null)

        val sensors: MutableList<Sensor> = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                val sensor = Sensor(
                    cursor.getString(cursor.getColumnIndex(COLUMN_ID)).toInt(),
                    cursor.getString(cursor.getColumnIndex(COLUMN_SENSOR_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_SENSOR_VALUE)).toInt(),
                )
                sensors.add(sensor)
            } while (cursor.moveToNext())
        }
        return sensors
    }


    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "sensors.db"
        val TABLE_SENSORS = "sensor"

        val COLUMN_ID = "_id"
        val COLUMN_SENSOR_NAME = "name"
        val COLUMN_SENSOR_VALUE = "value"
    }
}
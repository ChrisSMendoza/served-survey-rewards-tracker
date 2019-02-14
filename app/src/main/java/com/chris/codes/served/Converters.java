package com.chris.codes.served;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

class Converters {
    @TypeConverter
    static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

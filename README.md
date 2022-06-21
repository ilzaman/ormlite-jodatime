# ormlite-jodatime

[![Build Status](https://travis-ci.org/GautierLevert/ormlite-jodatime.svg?branch=develop)](https://travis-ci.org/GautierLevert/ormlite-jodatime)

[![codebeat badge](https://codebeat.co/badges/00bb8dfd-d528-41df-967c-9d8ee466f0a0)](https://codebeat.co/projects/github-com-gautierlevert-ormlite-jodatime-develop)

## What is it?

A set of [ORMLite](http://ormlite.com/) serialiser/deserialisers for dealing with 
[Joda Time](http://www.joda.org/joda-time/) entities.

Every JodaTime concrete classes are supported. You can see here a table of all Types available and the type used in SQL format (String, Integer or Long)

|                 | String                                                                | Long or Integer                                                     |
|-----------------|-----------------------------------------------------------------------|---------------------------------------------------------------------|
| DateTimeZone    | org.mybop.ormlite.jodatime.datatype.timezone.DateTimeZoneType         |                                                                     |
| Duration        | org.mybop.ormlite.jodatime.datatype.duration.DurationStringType       | org.mybop.ormlite.jodatime.datatype.duration.DurationLongType       |
| Instant         | org.mybop.ormlite.jodatime.datatype.instant.InstantStringType         | org.mybop.ormlite.jodatime.datatype.instant.InstantLongType         |
| DateTime        | org.mybop.ormlite.jodatime.datatype.instant.DateTimeStringType        | org.mybop.ormlite.jodatime.datatype.instant.DateTimeLongType        |
| MutableDateTime | org.mybop.ormlite.jodatime.datatype.instant.MutableDateTimeStringType | org.mybop.ormlite.jodatime.datatype.instant.MutableDateTimeLongType |
| LocalDateTime   | org.mybop.ormlite.jodatime.datatype.partial.LocalDateTimeStringType   | org.mybop.ormlite.jodatime.datatype.partial.LocalDateTimeLongType   |
| LocalDalte      | org.mybop.ormlite.jodatime.datatype.partial.LocalDateType             |                                                                     |
| LocalTime       | org.mybop.ormlite.jodatime.datatype.partial.LocalTimeStringType       | org.mybop.ormlite.jodatime.datatype.partial.LocalTimeIntegerType    |
| MonthDay        | org.mybop.ormlite.jodatime.datatype.partial.MonthDayType              |                                                                     |
| YearMonth       | org.mybop.ormlite.jodatime.datatype.partial.YearMonthType             |                                                                     |
| Period          | org.mybop.ormlite.jodatime.datatype.period.PeriodType                 |                                                                     |
| MutablePeriod   | org.mybop.ormlite.jodatime.datatype.period.MutablePeriodType          |                                                                     |
| Years           |                                                                       | org.mybop.ormlite.jodatime.datatype.period.YearsType                |
| Months          |                                                                       | org.mybop.ormlite.jodatime.datatype.period.MonthsType               |
| Weeks           |                                                                       | org.mybop.ormlite.jodatime.datatype.period.WeeksType                |
| Days            |                                                                       | org.mybop.ormlite.jodatime.datatype.period.DaysType                 |
| Hours           |                                                                       | org.mybop.ormlite.jodatime.datatype.period.HoursType                |
| Minutes         |                                                                       | org.mybop.ormlite.jodatime.datatype.period.MinutesType              |
| Seconds         |                                                                       | org.mybop.ormlite.jodatime.datatype.period.SecondsType              |

### Note about DateTime and MutableDateTime

DateTime and MutableDateTime have a timezone property that is lost during persistence.

- In long format, information is completely lost and default timezone will be used for reading.
- In string format, only time offset will be retrieved and behaviour can be different (with DST among other things).

Anyway persisted and retrieved DateTime will represent the exact same instant.

## Using it

This types can be used by annotation on a single field: 

```java
@DatabaseField(dataType = DateTimeStringType.class)
private DateTime dateTime;
```

Or you can register desired types to be used by default for class :

```java
DataPersisterManager.registerDataPersisters(DateTimeStringType.getSingleton())
```

But the simplest way is to call:

```java
OrmliteJodatimeUtils.registerTypes();
```

This methods will register one data persister for each JodaTime classes.

*NB:* this method will always register numeric format instead of string format when available. 

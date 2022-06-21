package org.mybop.ormlite.jodatime.datatype;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import org.joda.time.DateTimeZone;

import java.sql.SQLException;

public abstract class BaseDataType<OBJ, SQL> extends com.j256.ormlite.field.types.BaseDataType {

    private final Class<OBJ> objClass;

    private final Class<SQL> sqlClass;

    /**
     * @param sqlClass Class of the SQL argument, must be a java representation of sqlType (must not be null).
     * @param objClass Class of the Java objects (must not be null).
     * @param sqlType  Type of the class as it is persisted in the databases.
     */
    protected BaseDataType(SqlType sqlType, Class<SQL> sqlClass, Class<OBJ> objClass) {
        super(sqlType, new Class[]{objClass});

        if (null == objClass) {
            throw new NullPointerException("objClass must not be null");
        }
        this.objClass = objClass;

        if (null == sqlClass) {
            throw new NullPointerException("sqlClass must not be null");
        }
        this.sqlClass = sqlClass;
    }

    /**
     * Return the class most associated with this persister or null if none.
     */
    @Override
    public final Class<?> getPrimaryClass() {
        return objClass;
    }

    /**
     * Convert a default string object and return the appropriate argument to a SQL insert or update statement.
     *
     * @param fieldType Entity field information (may be null)
     * @return Result object to insert if the field is not specified or null if none.
     * @throws SQLException If there is a problem parsing the default string
     */
    @Override
    public final SQL parseDefaultString(final FieldType fieldType, final String defaultStr) throws SQLException {
        if (null == defaultStr) {
            return null;
        }

        try {
            return objToSql(fieldType, parseString(fieldType, defaultStr));
        } catch (Exception e) {
            throw new SQLException(
                    String.format(
                            "Unable to parse `%s` or convert it in %s",
                            defaultStr,
                            sqlClass.getSimpleName()
                    ), e);
        }
    }

    /**
     * Convert a Java object and return the appropriate argument to a SQL insert or update statement.
     *
     * @param fieldType  Field information (may be null).
     * @param javaObject Field value to be converted in SQL arg (must not be null)
     * @return id of the given {@link DateTimeZone}
     * @throws SQLException If there are problems with the conversion.
     */
    @Override
    public final SQL javaToSqlArg(
            final FieldType fieldType,
            final Object javaObject
    ) throws SQLException {
        if (null == javaObject) {
            return null;
        }

        try {
            return objToSql(fieldType, objClass.cast(javaObject));
        } catch (ClassCastException e) {
            throw new SQLException(
                    String.format(
                            "Given argument `%s` must be an instance of %s",
                            javaObject,
                            objClass
                    ), e);
        } catch (Exception e) {
            throw new SQLException(
                    String.format(
                            "Unable to convert `%s` in SQL arg",
                            objClass
                    ), e);
        }
    }

    @Override
    public final OBJ sqlArgToJava(final FieldType fieldType, final Object sqlArg, final int columnPos) throws SQLException {
        if (null == sqlArg) {
            return null;
        }
        try {
            return sqlToObj(fieldType, sqlClass.cast(sqlArg));
        } catch (ClassCastException e) {
            throw new SQLException(
                    String.format(
                            "Given argument `%s` must be an instance of %s",
                            sqlArg,
                            sqlClass
                    ), e);
        } catch (Exception e) {
            throw new SQLException(String.format("Unable to parse %s from given value `%s`", objClass, sqlArg), e);
        }
    }

    /**
     * Parse given String into java object.
     *
     * @param fieldType Entity field information (may be null).
     * @param str       The string to parse.
     * @return Parsed Java object.
     */
    public abstract OBJ parseString(final FieldType fieldType, final String str);

    /**
     * Converts SQL argument into Java object.
     *
     * @param fieldType  Entity field information (may be null).
     * @param javaObject The Java object to be converted in SQL argument.
     * @return The SQL argument.
     */
    public abstract SQL objToSql(final FieldType fieldType, final OBJ javaObject);

    /**
     * Converts Java object into SQL argument.
     *
     * @param fieldType Entity field information (may be null).
     * @param sqlArg    The SQL argument to be converted in Java object.
     * @return The Java object.
     */
    public abstract OBJ sqlToObj(final FieldType fieldType, final SQL sqlArg);

}

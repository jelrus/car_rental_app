package test;

import persistence.entity.annotations.Column;
import persistence.entity.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class QueryGenerator {

    private QueryGenerator() {}

    private static List<Class<?>> getClasses(Class<?> cls) {
        List<Class<?>> superClasses = new ArrayList<>();
        Class<?> target = cls;

        while (!target.getSimpleName().equals(Object.class.getSimpleName())) {
            Class<?> a = target.getSuperclass();
            superClasses.add(a);
            target = a;
        }

        superClasses.remove(Object.class);
        superClasses.add(0, cls);

        return superClasses;
    }

    private static List<String> getAnnotatedFields(Class<?> cls) {
        List<String> annotatedFields = new ArrayList<>();

        for (int i = QueryGenerator.getClasses(cls).size() - 1; i >= 0; i--) {
            for (Field f : QueryGenerator.getClasses(cls).get(i).getDeclaredFields()) {
                if (f.isAnnotationPresent(Column.class)) {
                    annotatedFields.add(f.getAnnotation(Column.class).name());
                }
            }
        }

        return annotatedFields;
    }

    public static String createQuery(Class<?> cls) {
        StringBuilder createQueryBuilder = new StringBuilder();

        createQueryBuilder.append("INSERT INTO ")
                          .append(cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT))
                          .append(" VALUES(");

        for (int i = 0; i < getAnnotatedFields(cls).size(); i++) {
            if (i != 0 && i != getAnnotatedFields(cls).size() - 1) {
                createQueryBuilder.append("?, ");
            } else if (i == 0) {
                createQueryBuilder.append("default, ");
            } else {
                createQueryBuilder.append("?);");
            }
        }

        return createQueryBuilder.toString();
    }

    public static String updateQuery(Class<?> cls, String updateParameter, List<String> excludeParameters) {
        StringBuilder updateQueryBuilder = new StringBuilder();

        updateQueryBuilder.append("UPDATE ")
                          .append(cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT))
                          .append(" SET ");

        for (int i = 0; i < getAnnotatedFields(cls).size(); i++) {
            if (!getAnnotatedFields(cls).get(i).equals(updateParameter) &&
                !excludeParameters.contains(getAnnotatedFields(cls).get(i))) {
                if (i != getAnnotatedFields(cls).size() - 1) {
                    updateQueryBuilder.append(getAnnotatedFields(cls).get(i))
                                      .append("=?, ");
                } else {
                    updateQueryBuilder.append(getAnnotatedFields(cls).get(i))
                            .append("=? ");
                }
            }
        }

        updateQueryBuilder.append("WHERE ").append(updateParameter).append("=?;");

        return updateQueryBuilder.toString();
    }

    public static String deleteQuery(Class<?> cls, String deleteParameter) {
        return "DELETE FROM " + cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT) +
                " WHERE " + deleteParameter + "=?;";
    }

    public static String existBy(Class<?> cls, String existParameter) {
        return "SELECT COUNT(*) AS COUNT FROM " + cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT)  +
               " WHERE " + existParameter +"=?;";
    }

    public static String findBy(Class<?> cls, String findParameter) {
        return "SELECT * FROM " + cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT)  +
                " WHERE " + findParameter +"=?;";
    }

    public static String findAll(Class<?> cls) {
        return "SELECT * FROM " + cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT) + ";";
    }

    public static String count(Class<?> cls) {
        return "SELECT COUNT(*) AS COUNT FROM " + cls.getAnnotation(Table.class).tableName().toUpperCase(Locale.ROOT) +";";
    }
}

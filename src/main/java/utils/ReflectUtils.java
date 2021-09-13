package utils;

import data.Employee;
import data.Person;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ReflectUtils {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class lombok = Class.forName("lombok.Data");
        System.out.println(lombok);
    }

    public static void invokeMethodTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Employee employee = new Employee("小明", "18", "写代码", 1, "Java攻城狮", 100000);
        Method sayHello = employee.getClass().getMethod("sayHello");
        System.out.println(sayHello);   // 打印 sayHello 的方法信息
        sayHello.invoke(employee);      // 让 employee 执行 sayHello 方法

        double x = 3.0;
        Method square = ReflectUtils.class.getMethod("square", double.class);  // 获取 MethodTableTest 的square方法
        double y1 = (double) square.invoke(null, x);    // 调用类方法 square 求平方，方法参数 x
        System.out.printf("square    %-10.4f -> %10.4f%n", x, y1);

        Method sqrt = Math.class.getMethod("sqrt", double.class);   // 获取 Math 的 sqrt 方法
        double y2 = (double) sqrt.invoke(null, x);  // 调用类方法 sqrt 求根，方法参数 x
        System.out.printf("sqrt      %-10.4f -> %10.4f%n", x, y2);
    }

    // static静态方法 计算乘方
    public static double square(double x) {
        return x * x;
    }

    /**
     * 打印Class对象的所有构造方法
     */
    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");
            // 打印构造参数
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印Class的所有方法
     */
    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        //Method[] methods = cl.getMethods();
        for (Method m : methods) {
            Class retType = m.getReturnType();  // 返回类型
            System.out.print("  ");
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + m.getName() + "(");
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印Class的所有属性
     */
    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class type = f.getType();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + f.getName() + ";");
        }
    }

    private static ArrayList<Object> visited = new ArrayList<>();

    public static String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (visited.contains(obj)) {    // 如果该对象已经处理过，则不再处理
            return "...";
        }
        visited.add(obj);

        Class cl = obj.getClass(); // 获取Class对象
        if (cl == String.class) {   // 如果是String类型则直接转为String
            return (String) obj;
        }
        if (cl.isArray()) {        // 如果是数组
            String r = cl.getComponentType() + "[]{\n";     // 数组的元素的类型
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {   // 不是数组的第一个元素加逗号和换行，显示更加美观
                    r += ",\n";
                }
                r += "\t";
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) { // Class为8种基本类型的时候为 true，直接输出
                    r += val;
                } else {
                    r += toString(val); // 不是8中基本类型时，说明是类，递归调用toString
                }
            }
            return r + "\n}";
        }
        // 既不是String，也不是数组时，输出该对象的类型和属性值
        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();    // 获取该类自己定义的所有域，包括私有的，不包括父类的
            AccessibleObject.setAccessible(fields, true); // 访问私有的属性，需要打开这个设置，否则会报非法访问异常
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) { // 通过 Modifier 可获取该域的修饰符，这里判断是否为 static
                    if (!r.endsWith("[")) {
                        r += ",";
                    }
                    r += f.getName() + "=";     // 域名称
                    try {
                        Class t = f.getType();  // 域（属性）的类型
                        Object val = f.get(obj);   // 获取obj对象上该域的实际值
                        if (t.isPrimitive()) {     // 如果类型为8种基本类型，则直接输出
                            r += val;
                        } else {
                            r += toString(val);     // 不是8种基本类型，递归调用toString
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass(); // 继续打印超类的类信息
        } while (cl != null);
        return r;
    }

    public static void getClass3() throws ClassNotFoundException {
        Class c1 = Class.forName("data.Employee");   // 第1种，forName 方式获取Class对象
        Class c2 = Employee.class;      // 第2种，直接通过类获取Class对象
        Employee employee = new Employee("小明", "18", "写代码", 1, "Java攻城狮", 100000);
        Class c3 = employee.getClass();    // 第3种，通过调用对象的getClass()方法获取Class对象
        if (c1 == c2 && c1 == c3) {     // 可以通过 == 比较Class对象是否为同一个对象
            System.out.println("c1、c2、c3 为同一个对象");
            System.out.println(c1);     // class reflect.Employee
        }
    }

    public static void newInstance2() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<Person> c = Person.class;
        Person person = c.newInstance();

        System.out.println("person=" + person);
        Class c1 = Class.forName("data.Person");
        Constructor constructor = c1.getConstructor(String.class, String.class, String.class);
        Object object = constructor.newInstance("a", "b", "c");
        System.out.println(object);
    }
}

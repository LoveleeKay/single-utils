package utils;

import impl.UserServiceImpl;
import proxy.LogInterceptor;
import proxy.SelfLogHandler;
import proxy.UserServiceProxy;
import service.UserService;

import java.time.Instant;

/**
 * 代理工具类,JDK动态代理是实现接口的代理类（只能代理接口），CGLIB是继承的方式，JDK快，CGLIB适合单例
 */
public class SelfProxyUtils {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Instant instant1 = Instant.now();
        UserService userDaoProxy = (UserService) new SelfLogHandler(new UserServiceImpl()).CreatProxyedObj();
        Instant instant2 = Instant.now();
        System.out.println(instant2.toEpochMilli() - instant1.toEpochMilli());

        Instant instant3 = Instant.now();
        UserService userDao = (UserService) new LogInterceptor().CreatProxyedObj(UserServiceImpl.class);
        Instant instant4 = Instant.now();
        System.out.println(instant4.toEpochMilli() - instant3.toEpochMilli());


        userDao.select();
        userDao.update();
    }

    public static void staticProxy() {
        UserService userServiceImpl = new UserServiceImpl();
        UserService proxy = new UserServiceProxy(userServiceImpl);

        proxy.select();
        proxy.update();
    }

    /**
     * JDK动态代理
     */
    public static void dynamicJDKProxy() {
        // 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
        // System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1. 创建被代理的对象，UserService接口的实现类
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        // 2. 获取对应的 ClassLoader
//        ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();
//        // 3. 获取所有接口的Class，这里的UserServiceImpl只实现了一个接口UserService，
//        Class[] interfaces = userServiceImpl.getClass().getInterfaces();
        // 4. 创建一个将传给代理类的调用请求处理器，处理所有的代理对象上的方法调用
        //     这里创建的是一个自定义的日志处理器，须传入实际的执行对象 userServiceImpl
        SelfLogHandler logHandler = new SelfLogHandler(userServiceImpl);
        /*
		   5.根据上面提供的信息，创建代理对象 在这个过程中，
               a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
               b.然后根据相应的字节码转换成对应的class，
               c.然后调用newInstance()创建代理实例
		 */
        UserService proxy = (UserService) logHandler.CreatProxyedObj();
        // 调用代理的方法
        proxy.select();
        proxy.update();

        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        ProxyUtils.generateClassFile(userServiceImpl.getClass(), "UserServiceProxy");
    }

    public static void dynamicCglibProxy() {
        Instant instant3 = Instant.now();
        UserService userDao = (UserService) new LogInterceptor().CreatProxyedObj(UserServiceImpl.class);
        Instant instant4 = Instant.now();
        System.out.println(instant4.toEpochMilli() - instant3.toEpochMilli());


        userDao.select();
        userDao.update();
    }
}

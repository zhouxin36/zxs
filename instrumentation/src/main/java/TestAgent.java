import java.lang.instrument.Instrumentation;

/**
 * @author zhouxin
 * @since 2021/3/18
 */
public class TestAgent {
    public static void agentmain(String args, Instrumentation inst) {
        // 指定我们自己定义的 Transformer，在其中利用 Javassist 做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            // 重定义类并载入新的字节码
            inst.retransformClasses(Class.forName("com.zx.jdkanalysis.动态追踪技术.Base"));
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
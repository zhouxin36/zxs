package com.zx.codeanalysis.spring.springv2.context.support;

import com.zx.codeanalysis.spring.springv2.bean.ZXBeanDefinition;
import com.zx.codeanalysis.utils.SpringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author zhouxin
 * @date 2018-11-12
 */
public class ZXBeanDefinitionReader {

    private Map<String, Object> map;

    private List<String> classPaths = new ArrayList<>();

    public ZXBeanDefinitionReader(String[] classLocation) {
        Arrays.stream(classLocation).forEach(e -> {
            String str = e.split(":")[1];
            Yaml yaml = new Yaml();
            Map<String, Object> emap = yaml.load(this.getClass().getClassLoader().getResourceAsStream(str));
            if (this.map == null) {
                this.map = emap;
            } else {
                this.map.putAll(emap);
            }
        });
        doScanner(String.valueOf(map.get("scanPackage")));
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public List<String> getClassPaths() {
        return classPaths;
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader()
                .getResource(scanPackage.replaceAll("\\.", "/"));
        Optional.ofNullable(url).map(e -> {
            File file = new File(e.getFile());
            return file.listFiles();
        }).map(Arrays::stream).ifPresent(e ->
                e.forEach(o -> {
                    if (o.isDirectory()) {
                        doScanner(scanPackage + "." + o.getName());
                    } else {
                        if (o.getName().contains(".java")) {
                            return;
                        }
                        classPaths.add(scanPackage + "." + o.getName().replace(".class", ""));
                    }
                })
        );
    }

    public ZXBeanDefinition registerBean(String classPath) {
        if (!this.classPaths.contains(classPath)) {
            return null;
        }
        ZXBeanDefinition zxBeanDefinition = new ZXBeanDefinition();
        zxBeanDefinition.setClassName(classPath);
        zxBeanDefinition.setFactoryName(SpringUtils.lowerFirstCase(classPath.substring(classPath.lastIndexOf(".") + 1)));
        return zxBeanDefinition;
    }
}

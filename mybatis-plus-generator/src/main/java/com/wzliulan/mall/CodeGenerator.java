package com.wzliulan.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

// 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    // 生成的代码放到哪个工程中
    private static String PROJECT_NAME = "mybatis-plus-generator";

    // 数据库名称
    private static String DATABASE_NAME = "mall-site";

    // 子包名
    private static String MODULE_NAME = "consumer.dao";

    // 实体模型
    private static String ENTITY_NAME = "consumer.dao.model";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.1.201:3306/"+ DATABASE_NAME +"?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("LHLlhl@870104");
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/";
        gc.setOutputDir(projectPath + PROJECT_NAME +"/src/main/java");
        gc.setIdType(IdType.ASSIGN_ID); // 分布式id
        gc.setAuthor("li.");
        gc.setFileOverride(false); //覆盖现有的
        gc.setOpen(false); //是否生成后打开
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wzliulan.mall"); //父包名
        pc.setController(MODULE_NAME+".controller"); // com.wzliulan.blog.article.controller
        pc.setService(MODULE_NAME+".service");
        pc.setServiceImpl(MODULE_NAME+".service.impl");
        pc.setMapper(MODULE_NAME+".mapper");
        pc.setXml(MODULE_NAME+".mapper");
        pc.setEntity(ENTITY_NAME); // 实体类存储包名 com.wzliulan.blog.model
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); //使用lombok
        strategy.setEntitySerialVersionUID(true);// 实体类的实现接口Serializable
        strategy.setRestControllerStyle(true); // @RestController
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("m_"); // 去掉表前缀
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
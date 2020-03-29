import groovy.xml.MarkupBuilder
import org.apache.tools.ant.Project

//插件 Plugin 的创建与使用

//1. 创建插件工程

//2. 创建插件类
class GradleStudyPlugin implements Plugin<Project> {
    /**
     * 插件被引入时执行
     * @param project 引入当前插件的project
     */
    @Override
    void apply(Project project){
        project.extensions.create('imoocReleaseInfo', ReleaseInfoExtension)
    }
}

//3.声明别人如何使用这个插件
//  在xxx.properties文件里声明
//  其中com.imooc.gradle.study是包名，GradleStudyPlugin是类名
//implementation-class=com.imooc.gradle.study.GradleStudyPlugin

//4.在app.gradle里使用
apply plugin: 'com.imooc.gradle.study'

//5.创建与自定义插件进行参数传递
class ReleaseInfoExtension{
    String versionCode
    String versionName
    String versionInfo
    String fileName

    ReleaseInfoExtension(){
    }

    @Override
    String toString() {
        """| versionCode = ${versionCode}
           | versionName = ${versionName}
           | versionInfo = ${versionInfo}
           | fileName = ${fileName()}
        """.stripMargin()
    }
}

//6.在app.gradle里，为自定义插件传递参数
imoocReleaseInfo{
    versionCode = rootProject.ext.android.versionCode
    versionName = rootProject.ext.android.versionName
    versionInfo = '第8个版本'
    fileName = 'releases.xml'
}

//7.创建自定义task，实现维护版本信息功能
class ReleaseInfoTask extends DefaultTask{
    ReleaseInfoTask(){
        group = 'imooc'
        description = 'update the release info'
    }

    //被TaskAction注解的方法，会自动在task的执行阶段执行
    //doFirst会在doAction之前执行，doLast会在doAction之后执行
    @TaskAction
    void doAction(){
        updateInfo()
    }

    //将Extension类中的信息，写入指定文件中
    private void updateInfo(){
        //获取将要写入的信息
        String versionCode = project.extensions.imoocReleaseInfo.versionCode
        String versionName = project.extensions.imoocReleaseInfo.versionName
        String versionInfo = project.extensions.imoocReleaseInfo.versionInfo
        String fileName = project.extensions.imoocReleaseInfo.fileName
        //写入到xml文件中
        def file = project.file(fileName)
        def sw = new StringWriter()
        def XmlBuilder = new MarkupBuilder(sw)
        // ...
    }
}























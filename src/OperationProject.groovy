import org.apache.tools.ant.Project

//project 命令：
// ./gradlew projects
//   列出一个项目中所有的 project

//project 概念：
//1. 每个module就是一个project，对应一个build.gradle
//   有build.gradle的文件夹才是一个project，不然就是普通的文件夹
//2. project可以嵌套，通常只会嵌套一层，就是：
//   a. 每个项目会有一个根project，用来管理所有的子project
//   b. 每个子project用来对应一个输出
//3. build.gradle最终被编译为一个project字节码文件
//   在build.gradle中写代码就是在project类中写代码

//project API：
//1. gradle生命周期api
//2. project相关api
//3. task相关api
//4. 属性相关api
//5. file相关api
//6. 其他api

//输出所有project
//this.getAllprojects().eachWithIndex{ Project project, int index ->
//    if(index == 0){
//        println "Root project: ${project.name}"
//    }else{
//        println "+--- project: ${project.name}"
//    }
//}

//输出当前project下所有子project
//this.getSubprojects().eachWithIndex{ Project project, int index ->
//    println "+--- project: ${project.name}"
//}

//获取当前project的父project
//def parProjectName = this.getParent().name

//获取根project
//def rootProjectName = this.getRootProject().name

//根据路径，配置指定project
//参数一：project路径
//参数二：闭包
//project('app'){Project project ->
//    apply plugin: 'com.android.application' //指定当前project的输出
//    group 'com.xxx' //指定group
//    version '1.0.0-release' //指定版本号
//    dependencies{//指定依赖
//    }
//    android{
//    }
//}

//配置所有project的公共项
//allprojects{
//    group 'com.xxx' //指定group
//    version '1.0.0-release' //指定版本号
//}

//配置所有子project的公共项
//subprojects{Project project ->
//    if(project.plugins.hasPlugin('com.android.library')){
//        apply from: '../publishToMaven.gradle' //配置上传到maven的功能
//    }
//}















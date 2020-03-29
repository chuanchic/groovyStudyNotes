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

//给project添加扩展属性
//方式一：
//在根project上添加，子project会继承这些属性，可以直接访问到
//还可以进一步优化：将扩展属性定义在单独的xxx.gradle中
//然后在根project中引入xxx.gradle，apply from: this.file('xxx.gradle')
//ext{
//    compileSdkVersion = 25
//}
//方式二：
//在gradle.properties中添加，只能定义 key=value 这种形式
//isLoadTest=false
//如何使用？例如
//if(hasProperty('isLoadTest') ? isLoadTest.toBoolean() : false){
//    include ':Test'
//}

//文件相关操作
//getRootDir().absolutePath //获取当前项目根目录
//getBuildDir().absolutePath //获取当前project的build目录
//getProjectDir().absolutePath //获取当前project的目录

//file()的使用，在当前project下查找文件
//同理 files() 用来查找多个文件
//def getContent(String path){
//    try{
//        def file = file(path)
//        return file.text
//    }catch(Exception e){
//        e.printStackTrace()
//    }
//    return null;
//}

//文件拷贝
//接收一个闭包，将 文件 拷贝到 根目录 下的 build 文件夹下
//不仅可以拷贝文件，还可以拷贝文件夹
//copy{
//    from file('xxx.xxx')
//    into getRootProject().getBuildDir()
//    exclude{//用来忽略哪些文件
//    }
//    rename{//用来文件重命名
//    }
//}

//将 文件夹 映射成 文件树，然后遍历
//fileTree('xxx/xxx/'){FileTree fileTree ->
//    fileTree.visit{FileTreeElement element ->
//        println element.file.name
//    }
//}

//依赖配置相关
//buildscript{ScriptHandler scriptHandler ->
//    //配置工程的仓库地址，其中 repositoryHandler 可以省略不写
//    scriptHandler.repositories{RepositoryHandler repositoryHandler ->
//        repositoryHandler.jcenter()
//        repositoryHandler.mavenCentral()
//        repositoryHandler.mavenLocal()
//        repositoryHandler.maven{//私有maven仓库
//            name '仓库名'
//            url 'http://localhost...公司私有仓库地址'
//            credentials{
//                username = 'xxx'
//                password = 'xxx'
//            }
//        }
//        repositoryHandler.ivy{
//        }
//    }
//    //配置工程的'插件'依赖地址，就是 gradle 所依赖的第三方插件
//    scriptHandler.dependencies{
//        classpath 'com.android.tools.build:gradle:2.2.2'
//        classpath 'com.tencent.tinker-patch-gradle-plugin:1.7.7'
//    }
//}

//app.gradle里的依赖配置，就是 程序 所依赖的第三方库
//dependencies{
//    compile fileTree(include: ['*.jar'], dir: 'libs') //文件树、文件夹
//    compile files('...') //多个文件
//    compile file('...') //单个文件
//    compile '远程库' //远程库
//    compile project('...') //本地工程
//}
//可能会遇到依赖冲突，有重复的第三方依赖
//compile('...'){
//    exclude module: 'xxx' //将冲突的依赖移除
//    exclude group: 'xxx' //移除一组依赖
//    //工程A依赖工程B，工程B依赖工程C，这样工程A和工程C构成了传递依赖
//    //一般情况下禁止工程A直接访问工程C，只有工程B可以访问工程C
//    //因为很有可能工程B升级以后不再依赖工程C，就会导致工程A找不到工程C中的东西
//    transitive false //传递依赖，默认false，表示 禁止 传递依赖
//}

//compile和provided的使用场景
//1. 如果某些依赖项只在编译期使用，不在运行时使用，也就是运行期间不会使用到这些依赖，
//   就应该使用 provided，provided不会将依赖项打入输出文件中，可以减少输出文件的体积
//2. 当前工程如果是库工程，引入依赖比如okhttp，主工程也引入okhttp，
//   当前工程也应该使用 provided，保证编译期通过，运行期保证大家都使用主工程的okhttp
//3. 其他情况使用compile

//project如何使用外部命令？
//定义apk拷贝的任务
//task(name: 'apkcopy'){
//    //保证当前任务的执行在gradle的执行阶段，而不是配置阶段
//    doLast{
//        def sourcePath = this.buildDir.path + '/outputs/apk'
//        def desationPath = '打包机的地址'
//        //定义文件移动命令
//        def command = "mv -f ${sourcePath} ${desationPath}"
//        //通过project提供的exec，执行外部命令
//        exec {
//            try{
//                executable 'bash'
//                args '-c', command
//            }catch(Exception e){
//                e.printStackTrace()
//            }
//        }
//    }
//}























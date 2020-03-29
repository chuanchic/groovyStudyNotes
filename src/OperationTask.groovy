import groovy.xml.MarkupBuilder

//task命令：
//1. 查看当前工程所有的task
//   ./gradlew tasks

//定义task
//方式一：
//task函数，参数一：task名称，参数二：闭包
//同方式二，创建的task最终也会被加入到 TaskContainer 中
//创建task的同时添加配置项 group description
//task helloTask(group: 'xxx', description: 'xxx'){
//    doFirst{
//        //doFirst可以定义多个
//        //这里执行的代码，是在task执行阶段，不是在编译阶段
//        //在task之前执行
//    }
//    doFirst{
//    }
//    doLast{
//        //在task之后执行
//    }
//}
//还可以在外部定义，会先执行外部定义的，再执行内部定义的
//helloTask.doFirst{
//}
//方式二：
//通过 TaskContainer 创建
//this.tasks.create(name: 'helloTask2'){
//    setGroup('xxx')
//    setDescription('xxx')
//}

//小案例：统计 build 执行时长
//def startBuildTime, endBuildTime
////所有task解析配置完成
//this.afterEvaluate{Project project ->
//    //找出第一个执行的task
//    def preBuildTask = project.tasks.getByName('preBuild')
//    preBuildTask.doFirst{
//        startBuildTime = System.currentTimeMillis()
//        println('the start time is:' + startBuildTime)
//    }
//    //找出 build task
//    def buildTask = project.tasks.getByName('build')
//    buildTask.doLast{
//        endBuildTime = System.currentTimeMillis()
//        println "the build time is: ${endBuildTime - startBuildTime}"
//    }
//}

//指定task的执行方式和顺序
//方式一. dependsOn强依赖方式
//       静态添加：我们知道需要添加哪些依赖，在定义task的时候直接指定
//       动态添加：我们不知道具体有哪些依赖，需要动态指定
//方式二. 通过task指定 输入 和 输出
//方式三. 通过API指定执行顺序

//方式一：dependsOn强依赖方式
//task taskX {
//    doLast{
//        println('taskX')
//    }
//}
//task taskY {
//    doLast{
//        println('taskY')
//    }
//}
//task taskZ(dependsOn:[taskX, taskY]) {//静态 添加依赖
//    //动态 添加依赖
//    dependsOn this.tasks.findAll {task ->
//        return task.name.startWith('lib')
//    }
//    doLast{
//        println('taskZ')
//    }
//}
//taskZ.dependsOn(taskX, taskY)//还可以在外部 静态 添加依赖
//执行 taskZ： ./gradlew taskZ
//执行顺序是 taskX - taskY - taskZ
//这里taskX taskY 没有依赖关系，所以他俩的执行顺序是随机的

//task lib1 << {// << 追加符 代表 doLast
//    println('lib1')
//}
//task lib2 << {
//    println('lib2')
//}
//task lib3 << {
//    println('nolib')
//}

//小案例：自定义task，读取文件内容到指定目录
//文件内容例如：
//<releases>
//  <release>
//      <versionCode>100</versionCode>
//      <versionName>1.0.0</versionName>
//      <versionInfo>APP的第一个版本</versionInfo>
//  </release>
//  <release>
//      <versionCode>101</versionCode>
//      <versionName>1.1.0</versionName>
//      <versionInfo>APP的第二个版本</versionInfo>
//  </release>
//</releases>
//task handleReleaseFile{
//    def srcFile = file('release.xml')
//    def destDir = new File(this.buildDir, 'generated/release/')
//    doLast{
//        println('开始解析对应的xml文件...')
//        destDir.mkdir()
//        def releases = new XmlParser().parse(srcFile)
//        //遍历每个release节点
//        releases.release.each{releaseNode ->
//            //解析每个release节点的内容
//            def name = releaseNode.versionName.text()
//            def versionCode = releaseNode.versionCode.text()
//            def versionInfo = releaseNode.versionInfo.text()
//            //创建文件并写入节点数据
//            def destFile = new File(destDir, "release-${name}.text")
//            destFile.withWriter {writer ->
//                writer.write("${name} -> ${versionCode} -> ${versionInfo}")
//            }
//        }
//    }
//}
//task handleReleaseFileTest(dependsOn: handleReleaseFile){
//    def destDir = new File(this.buildDir, 'generated/release/')
//    doLast{
//        destDir.each{
//            println('the file name is: ' + it)
//        }
//        println('输出完成')
//    }
//}

//方式二：通过task指定 输入 和 输出
//在 app.gradle 里添加扩展属性
//ext{
//    versionName = '1.0.0'
//    versionCode = '100'
//    versionInfo = 'APP的第一个版本'
//    destFile = file('releases.xml')
//    //如果文件不存在就创建
//    if(destFile != null && !destFile.exists()){
//        destFile.createNewFile()
//    }
//}
//task writeTask{
//    //为task指定输入
//    inputs.property('versionName', this.versioinName)
//    inputs.property('versionCode', this.versionCode)
//    inputs.property('versionInfo', this.versionInfo)
//    //为task指定输出
//    outputs.file destFile
//    //将内容写入文件
//    doLast{
//        def data = inputs.getProperties()//输入数据
//        File file = outputs.getFiles().getSingleFile()//输出文件
//        def versionMsg = new VersionMsg(data)//将map转化为实体对象
//        def sw = new StringWriter()
//        def xmlBuilder = new MarkupBuilder(sw)
//        if(file != null && file.text.size() <= 0){//文件中没有内容
//            xmlBuilder.releases{
//                release{
//                    versionName(versionMsg.versionName)
//                    versionCode(versionMsg.versionCode)
//                    versionInfo(versionMsg.versionInfo)
//                }
//            }
//            file.withWriter {writer ->
//                writer.append(sw.toString())
//            }
//        }else{//文件中有内容
//            xmlBuilder.release{
//                versionName(versionMsg.versionName)
//                versionCode(versionMsg.versionCode)
//                versionInfo(versionMsg.versionInfo)
//            }
//            //读出原有的所有行
//            def lines = file.readLines()
//            def lengths = lines.size() - 1
//            file.withWriter {writer ->
//                lines.eachWithIndex { String line, int index ->
//                    if(index != lengths){//不是最后一行
//                        //写入原有信息
//                        writer.append(line + '\r\n')
//                    }else if(index == lengths{//是最后一行
//                        //追加新的信息
//                        writer.append(sw.toString() + '\r\n')
//                        writer.append(lines.get(lengths))
//                    }
//                }
//            }
//        }
//    }
//}
//task readTask{
//    inputs.file destFile
//    doLast{
//        def file = inputs.files.singleFile
//        println(file.text)
//    }
//}
//输入 ./gradlew taskTest，会先执行 writeTask 再执行 readTask
//因为 readTask 和 writeTask 通过 destFile 关联了起来
//task taskTest{
//    dependsOn readTask, writeTask
//    doLast{
//        println('输入输出任务结束')
//    }
//}

//指定task的执行顺序
//task taskX{
//    doLast{
//        println('taskX')
//    }
//}
//task taskY{
//    mustRunAfter [taskX]
//    doLast{
//        println('taskY')
//    }
//}
//task taskZ{
//    mustRunAfter taskY
//    doLast{
//        println('taskZ')
//    }
//}

//挂接自定义task到构建过程中
//this.project.afterEvaluate{project ->
//    def buildTask = project.tasks.getByName('build')
//    if(buildTask == null){
//        throw GradleException('the build task is not found')
//    }
//    buildTask.doLast{
//        writeTask.execute()
//    }
//}













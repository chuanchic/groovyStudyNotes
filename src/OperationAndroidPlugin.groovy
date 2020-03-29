
//获取Android下所有的变体
this.afterEvaluate{
    this.android.applicationVariants.all{variant ->
        //1.修改apk的文件名
//        def name = variant.name//驼峰命名法
//        def baseName = variant.baseName//中划线命名法
//        def output = variant.outputs.first()
//        def apkName = "app-${variant.baseName}" + "-${variant.versionName}.apk"
//        output.outputFile = new File(output.outputFile.parent, apkName)

        //2.在某个task上添加执行代码
        def task = variant.checkManifest
        task.doFirst{
        }
        task.doLast{
        }
    }
}
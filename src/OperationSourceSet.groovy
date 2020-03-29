
//SourceSet的使用

//场景一：
//修改main下面的jniLibs的 so 文件的存放位置
//sourceSets{
//    main{
//        jniLibs.srcDirs = ['libs']
//    }
//}

//场景二：
//将资源文件分包存放，例如定义 res res-ad res-player
//sourceSets{
//    main{
//        res.srcDirs = [
//                'src/main/res',
//                'src/main/res-ad',
//                'src/main/res-player'
//        ]
//    }
//}
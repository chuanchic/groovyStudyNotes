
//gradle 包括：
//1.groovy核心语法
//2.build script block
//3.gradle api

//gradle 命令：
//1. ./gradlew clean  (mac系统上需要 ./ )

//gradle生命周期：
//1. Initiallzation 初始化阶段
//   解析整个工程中所有Project，构建所有Project对应的project对象
//2. Configuration 配置阶段
//   解析所有project对象中的task，构建好所有task的拓扑图
//3. Execution 执行阶段
//   执行具体的task及其依赖task

//gradle生命周期监听
//1. this.beforeEvaluate{}
//   Initiallzation 和 Configuration 之间
//2. this.afterEvaluate{}
//   Configuration 和 Execution 之间
//3. this.gradle.buildFinished{}
//   gradle 生命周期执行完成之后
//4. this.gradle.beforeProject{}
//5. this.gradle.afterProject{}
//6. this.gradle.addListener{}

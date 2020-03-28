

//变量的类型
//基本数据类型最终会被转成 Object 类型 Integer
//int x = 10
//println(x.class)

//变量的定义：强类型定义、弱类型定义
//类内部使用考虑 弱类型定义，被其他类调用考虑 强类型定义
//def x = 10
//def name = "haha"
//println(x.class)
//println(name.class)

//字符串的类型 String GString
//方式一：一个单引号 String类型 没有格式
//def name1 = 'haha'
//方式二：三个单引号 String类型 有格式
//def name2 = '''
//haha
//'''
//方式三：一个双引号 String类型
//def name3 = "haha"
//变成 可扩展字符串 GString类型
//def name4 = "haha ${name3}"
//不仅可以放变量，还可以放表达式
//def name5 = "haha${1+2}"

//String 方法的来源：
//1.java.lang.String
//2.DefaultGroovyMethods
//3.StringGroovyMethods

//def str = "groovy hello"
//str.center(8, 'a');//以str为中心向两边填充
//str.padLeft(8, 'a');//填充在str的左边
//def str2 = "hello"
//println(str > str2)//比较大小
//println(str[1])//获取字符
//println(str[1..2])//获取字符
//println(str.minus(str2))//从str中减去str2
//println(str - str2)//同上：从str中减去str2
//println(str.reverse())//反转str
//println(str.capitalize())//首字母大写
//println(str.isNumber())//是不是数字类型的字符串
//println(str.toInteger())//转成int类型的字符串

//逻辑控制
//1.顺序逻辑  单步往下执行
//2.条件逻辑  if else  switch case
//3.循环逻辑  while  for

//switch case 可以是任意类型
//def x = 10
//def result
//switch (x){
//    case 'foo'://字符串
//        result = 'foo'
//        break;
//    case [1,2,'haha']://是不是列表里的某一项
//        result = 'list'
//        break;
//    case 12..30://范围
//        result = 'range'
//        break;
//    case Integer://int类型
//        reuslt = 'integer'
//        break
//}

//对 范围 for循环
//def sum = 0
//for(i in 0..9){
//    sum += i
//}

//对 list for循环
//for(i in [1,2,3,4,5,6]){
//    sum += i
//}

//对 map for循环
//for(i in ['key': 1, 'key2': 2, 'key3': 3]){
//    sum += i.value
//}









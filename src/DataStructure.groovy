
//定义 ArrayList
//def list = [1, 2, 3]

//定义 数组
//def array = [1, 2, 3] as int[]
//int[] array2 = [1, 2, 3]

//列表 排序
//def sortList = [5, -3, -6, 2, 8]
//Comparator comparator = {a, b ->
//    a == b ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1
//}
//Collections.sort(sortList, comparator)
//sortList.sort{a, b ->
//    a == b ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1
//}

//列表 查找
//def list = [-3, 1, 3, 5, 3]
//def result = list.find{
//    return it % 2 == 0
//}
//def result2 = list.findAll{
//    return it % 2 == 0
//}
//def result3 = list.any{
//    return it % 2 == 0
//}
//def result4 = list.every{
//    return it % 2 == 0
//}
//def result5 = list.min{
//    return Math.abs(it)
//}
//def result6 = list.max{
//    return Math.abs(it)
//}
//def result7 = list.count{
//    return it >= 10
//}

//定义 map
//注意点：1. key要是不可变 字符串 或 数字，如果没加单引号，groovy会帮我们加
//       2. 默认会使用 LinkedHashMap，可以通过 as 或 显示声明 的方式来改变
//def colors = [red: 'ff000000', green: '00ff00', blue: '0000ff']
//println(colors['red'])//获取元素
//println(colors.red)//获取元素
//colors.yellow = 'ffff00'//添加元素
//colors.complex = [a: 1, b: 2]//添加 不同类型 的元素

//遍历 map
//def students = [
//        1: [number: 1, name: '哈哈'],
//        2: [number: 2, name: 'hehe'],
//        3: [number: 3, name: '好的']
//]
//students.each{def student ->
//    println "${student.key}"
//    println "${student.value}"
//}
//students.eachWithIndex{def student, int index ->
//    println "${index}"
//}
//students.each{key, value ->
//    println "${key}"
//    println "${value}"
//}
//students.each{key, value, index ->
//    println "${index}"
//}
//def stu = students.find{def student ->
//    return student.value.number > 2
//}
//def stu2 = students.findAll{def student ->
//    return student.value.number > 2
//}.collect{
//    return it.value.name
//}
//def count = students.count{def student ->
//    return student.value.number > 2
//}
//def group = students.groupBy{def student ->
//    return student.value.number > 1 ? '第一组' : "第二组"
//}
//def sort = students.sort{def s1, def s2 ->
//    Number n1 = s1.value.number
//    Number n2 = s2.value.number
//    return n1 == n2 ? 0 : n1 < n2 ? -1 : 1
//}

//范围
//def range = 1..10
//println range[0]//1
//println range.from// 1
//println range.to// 10
//range.each{
//    println it
//}
//def getGrade(Number number){
//    def result
//    switch (number){
//        case 0..<60:
//            result = '不及格'
//            break
//        case 60..<80:
//            result = '及格'
//            break
//        case 80..100:
//            result = '优秀'
//            break
//    }
//    return result
//}














//对文件进行操作
def file = new File('../groovyStudyNotes.iml')

//遍历每一行
//file.eachLine {line ->
//    println line
//}

//读取所有内容
//方式一
//def result = file.getText()
//println result
//方式二
//def result2 = file.readLines()
//println result2

//读取部分内容
//def reader = file.withReader {reader ->
//    char[] buffer = new char[100]
//    reader.read(buffer)
//    return buffer
//}
//println reader

//实现文件拷贝
def copy(String sourcePath, String  desPath){
    try{
        //创建目标文件
        def desFile = new File(desPath)
        if(!desFile.exists()){
            desFile.createNewFile()
        }
        //开始拷贝
        new File(sourcePath).withReader {reader ->
            def lines = reader.readLines()
            desFile.withWriter {writer ->
                lines.each {line ->
                    writer.append(line + "\r\n")
                }
            }
        }
        return true
    }catch(Exception e){
        e.printStackTrace()
    }
    return false;
}

//实现存储对象到文件
def saveObject(Object object, String desPath){
    try{
        //创建目标文件
        def desFile = new File(desPath)
        if(!desFile.exists()){
            desFile.createNewFile()
        }
        //输出到文件
        desFile.withObjectOutputStream {out ->
            out.writeObject(object)
        }
        return true
    }catch(Exception e){
        e.printStackTrace()
    }
    return false
}

//实现读取文件到对象
def readObject(String sourcePath){
    def obj = null;
    try {
        def file = new File(sourcePath)
        if(file != null && file.exists()){
            file.withObjectInputStream {input ->
                obj = input.readObject()
            }
        }
    }catch(Exception e){
        e.printStackTrace()
    }
    return obj;
}

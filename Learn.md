# 关于gradle
>>  是一个基于jvm的构建工具




# 2. android项目的library module里不能使用资源ID作为switch语句的case值。
##  因为switch里的case值必须是常数，而在library module的R文件里ID的值不是final类型的，
    但是主module的R文件里的ID值是final类型的，所以主module里可以用资源ID作为case值而library module却不能。


<?php
$con = mysqli_connect("localhost","root","123");//链接数据库，注意该php版本的链接语句是mysqli_connect（）
 $id=$_POST['ID'];
 $name=$_POST['Name'];//通过post方式获取表单数据并存入到$name变量中
 $tel=$_POST['tel'];
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysqli_select_db( $con,"test"); //注意语法，mysqli_select_db(connection,dbname);
$sql="select * from student where 1 ";
if($id != "") $sql=$sql."and ID like '%$id%' ";
if($name != "") $sql .="and Name like '%$name%' ";
if($tel != "") $sql .="and Tel like '%$tel%' ";
$result=mysqli_query($con,$sql);
while(!!$row=mysqli_fetch_array($result)){
	$nn++;
	echo "第".$nn."的ID是：".$row["ID"]."<br/>";
	echo "第".$nn."姓名是：".$row["Name"]."<br/>";
	echo "第".$nn."电话是：".$row["Tel"]."<br/>";
}
if($nn==0) echo "查询结果：无"."<br/>";
mysqli_close($con);  //函数中要加入参数
?>

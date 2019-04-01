var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql  = require('mysql');  
var connection = mysql.createConnection({     
  host     : 'localhost',       //localhost代表本地主机
  user     : 'root',            //mysql登录时的用户名     
  password : '123456',          //mysql登录时的用户对应的密码
  port: '3306',                 //mysql的端口号
  database: 'mysql',            //登录mysql后使用的database
}); 
 

// 创建 application/x-www-form-urlencoded 编码解析
var urlencodedParser = bodyParser.urlencoded({ extended: false })
app.use(express.static('public'));
app.get('/login.html', function (req, res) {           //浏览器请求页面为login.html，则发送login.html
   res.sendFile( __dirname + "/" + "login.html" );
})

app.post('/process_post', urlencodedParser, function (req, res) {
connection.connect();                          //mysql的查询语句，查询相应用户名和密码的记录是否存在
var  sql = "select * from students where name ='"+req.body.name+"' and pwd = '"+req.body.pwd+"'";
connection.query(sql,function (err, result) {
        if(err){
          console.log('[SELECT ERROR] - ',err.message);
          return;
        }
 
       console.log('--------------------------SELECT----------------------------');
       if(result!='')
          res.redirect("/home.html");    //登录成功则进入到主页面
       else
          result='error input'; 
       console.log(result);
       console.log('------------------------------------------------------------\n\n');  
       var response = {
       "name":req.body.name,
   };
   console.log(response);
   res.end(JSON.stringify(result));

});
// connection.end();
})
 
var server1 = app.listen(8081, function () {    //服务器在本地主机8081端口监听此请求
  var host = server1.address().address
  var port = server1.address().port
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
 
})

app.get("/home.html",function(req,res){                    //浏览器请求页面为home.html，则发送home.html
   res.sendFile( __dirname + "/" + "home.html" );
});
app.post('/process3', urlencodedParser, function (req, res) {
// connection.connect();                      //mysql更新语句，更改相应的记录
var  sql = "update students set gender = '"+req.body.gender+ "'    where name ='"+req.body.name+"' and pwd = '"+req.body.pwd+"'";
connection.query(sql,function (err, result) {
        if(err){
          console.log('[SELECT ERROR] - ',err.message);
          return;
        }
       console.log('--------------------------SELECT----------------------------');
       if(result!='')
          result="update successfully";
       else
          result='error input'; 
       console.log(result);
       console.log('------------------------------------------------------------\n\n');  
       var response = {
       "name":req.body.name,
   };
   console.log(response);
   res.end(JSON.stringify(result));
});
// connection.end();
})
 
var server2 = app.listen(8080, function () {       //服务器在本地主机8080端口监听此请求
  var host = server2.address().address
  var port = server2.address().port
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
})


app.get("/register.html",function(req,res){           //浏览器请求页面为register.html，则发送register.html
   res.sendFile( __dirname + "/" + "register.html" );
});
app.post('/process2', urlencodedParser, function (req, res) {
// connection.connect();                      //mysql插入语句，向表中插入新纪录
var  sql = "insert into students values('"+req.body.regname+"','"+req.body.regpwd+"','"+req.body.gender+"')";
connection.query(sql,function (err, result) {
        if(err){
          console.log('[SELECT ERROR] - ',err.message);
          return;
        }
       console.log('--------------------------SELECT----------------------------');
       if(result!='')
          res.redirect("/home.html");             //登录成功则进入到主页面
       else
          result='error input'; 
       console.log(result);
       console.log('------------------------------------------------------------\n\n');  
       var response = {
       "name":req.body.name,
   };
   console.log(response);
   res.end(JSON.stringify(result));
});
// connection.end();
})
 
var server3 = app.listen(8082, function () {      //服务器在本地主机8082端口监听此请求
  var host = server3.address().address
  var port = server3.address().port
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
})



app.post('/process4', urlencodedParser, function (req, res) {
// connection.connect();                   //mysql删除语句，将该用户名和密码对应的记录删除
var  sql = "delete from students where name ='"+req.body.name+"' and pwd = '"+req.body.pwd+"'";
connection.query(sql,function (err, result) {
        if(err){
          console.log('[SELECT ERROR] - ',err.message);
          return;
        }
       console.log('--------------------------SELECT----------------------------');
       if(result!='')
          result='delete successfully';
       else
          result='error '; 
       console.log(result);
       console.log('------------------------------------------------------------\n\n');  
       var response = {
       "name":req.body.name,
   };
   console.log(response);
   res.end(JSON.stringify(result));
});
// connection.end();
})
 

var server4 = app.listen(8084, function () {       //服务器在8084端口监听此请求
  var host = server4.address().address
  var port = server4.address().port
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
})

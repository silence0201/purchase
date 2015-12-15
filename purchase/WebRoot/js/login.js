 //创建XMLHttpReques对象
       function createXMLHttpRequest() {
          if (window.XMLHttpRequest) {
               //Mozilla 浏览器 
               XMLHttpReq = new XMLHttpRequest();
            } else{
                // IE浏览器
                if (window.ActiveXObject) {
                  try {
                         XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
                      }catch (e) {
                         try {
                                XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                             }catch (e) { }
                      }
                 }
            }
        } 
       //处理服务器响应结果
       function handleResponse() {
        // 判断对象状态
        if (XMLHttpReq.readyState == 4) { 
            // 信息已经成功返回，开始处理信息
           if (XMLHttpReq.status == 200) { 
                var out = "";
                var res = XMLHttpReq.responseXML;
                var response= res.getElementsByTagName("response")[0].firstChild.nodeValue;
                //alert(response) ;
                if(response == "登录成功"){
                	document.forms[0].submit();
                }else{
                	alert("你输入的用户名或密码错误") ;
                	document.forms[0].reset() ;
                }
               }
         }
       }
      //发送客户端的请求
      function sendRequest(url) {
            createXMLHttpRequest();
            XMLHttpReq.open("GET", url, true);
            //指定响应函数
            XMLHttpReq.onreadystatechange = handleResponse;
            // 发送请求
            XMLHttpReq.send(null); 
       }
       //开始调用Ajax的功能
      function userCheck()
      {
         var name = document.getElementById("userID").value;
         var password = document.getElementById("password").value;
         //发送请求
         sendRequest("UserCheck?userID="+name+"&password="+password); 
         
	         if(document.getElementById("remember_me").checked == true){
	        // 默认保留七天的用户信息
	        var now = new Date();
	        CookieUtil.set("userID", name , new Date(now.getTime()+7*24*3600*1000) );   
	        CookieUtil.set("password", password , new Date(now.getTime()+7*24*3600*1000) );    
	      }
	         /*else{
	        CookieUtil.set("userID",name);
	        CookieUtil.set("password", password); 
	      }*/
      }
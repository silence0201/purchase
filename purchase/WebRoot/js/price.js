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
                price.value = response ;
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
       function getPrice()
       {
    	   var itemName = document.getElementById("itemName") ;
    	   var price = document.getElementById("price") ;
    	   sendRequest("GetPrice?itemName="+itemName.value); 
       }
       
       //自动添加总金额
       function setAccount()
       {
    	   var number = document.getElementById("number").value ;
    	   var account = document.getElementById("Account") ;
    	   var price = document.getElementById("price").value ;
    	   if(parseInt(number) == number){
    		   account.value = number * price ;
    	   }else{
    		   alert("你输入的不是整数，请重新输入") ;
    	   }
       }
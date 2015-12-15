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
                if(response == 0){
                	alert("你输入的订单ID有误")
                }else{
                	var s = response.split(",") ;
                	if(s[5] == "采购中"){
                    	itemID.value = s[0] ;
                    	price.value = s[1] ;
                    	number.value = s[2] ;
                    	account.value = s[3] ;
                    	orderManId.value = s[4] ;
                	}else{
                		alert("订单已经入库，请不要重复添加") ;
                	}

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
       function setInfo()
       {
    	   var orderID = document.getElementById("orderID") ;
    	   var orderManId = document.getElementById("orderManId") ;
    	   var itemID = document.getElementById("itemID") ;
    	   var price = document.getElementById("price") ;
    	   var number = document.getElementById("number") ;
    	   var account = document.getElementById("account") ;
    	   sendRequest("GetInfoByOrderID?orderID="+orderID.value); 
       }
       
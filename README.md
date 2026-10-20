# Online-Agricultural-Assistance-Platform-Based-on-SpringBoot
# 基于SpringBoot的网上助农平台
 代码文件在master分支<br>
 
 >管理员登陆账号：admin  密码：admin  <br> 
 
* 开发语言：Java <br>
* 框架：springboot+Vue  <br>
* JDK版本：JDK1.8 <br>
* 服务器：tomcat7 <br> 
* 数据库：mysql 5.7 <br>  
* 数据库工具：Navicat11 <br>
* 开发软件：idea <br>
* Maven包：Maven3.3.9 <br>
* 浏览器：谷歌浏览器<br>

# 功能结构图

<img width="849" height="265" alt="image" src="https://github.com/user-attachments/assets/46cb6af0-329a-4b44-87fe-1650b8764cf4" />


# 功能结构详细描述
## 一、农民端
1.1 农产品销售模块
1.	商品管理：支持农民上传农产品信息（名称、产地、单价、库存、图片、质检报告等都行），可修改农产品信息、下架农产品；   
2.	订单处理：查看消费者 / 采购商下单信息（订单号、商品、数量、收货地址、支付状态）、退款申请处理（需提交退款原因及凭证）；
1.2 农业原料采购模块<br>
1.	原料浏览：查看高校提供的农业原料，有种子、肥料、农药、农机配件这些，查看原料详情（适用作物、研发背景、价格、库存、高校资质）；
2.	采购操作：支持加入购物车、提交采购订单等
3.	采购评价：收到原料后可对原料质量、高校服务进行评分（1-5 星）及文字评价，评价内容对所有农民可见。
1.3 农业技术知识库模块<br>
1.	内容浏览：按 “种植技术、养殖技术、病虫害防治、农产品加工” 等标签检索高校上传的技术文档（PDF/Word）、视频教程、图文指南；
2.	搜索功能：支持关键词搜索（如 “小麦病虫害防治”“大棚蔬菜种植”）
1.4 高校专家一对一支持模块<br>
1.	专家预约：查看高校入驻专家列表（含专家研究领域、职称、预约费用），选择专家后提交预约需求（描述问题、期望沟通时间、附件材料如作物病害照片）；
2.	咨询记录：保存所有咨询对话内容，可以后续查看回顾。
1.5 个人中心<br>
1.	基础信息管理：编辑姓名、联系方式、身份证号、发货和收货地址；
2.	资金管理：查看销售收益、采购支出






## 二、消费者 / 采购商端
2.1 农产品购买模块<br>
1.	商品浏览：查看农民卖的农产品信息（蔬菜瓜果什么的），查看商品详情（含农民资质、质检报告、用户评价）；
2.	下单与支付：支持加入购物车、填写收货地址、选择支付方式、支付后生成订单，可查看订单状态（待发货 / 已发货 / 已完成）；
3.	批量采购：支持对单个农产品设置 “批量采购价”（采购量达到阈值后自动触发折扣，阈值由农民设置）；
2.2 个人中心<br>
1.	订单管理：查看所有订单记录
2.	地址管理：添加、编辑、删除收货地址，设置默认地址；
3.	评价管理：对已完成订单的商品进行评分及评价，可查看自己发布的评价及农民回复。
## 三 、高校端
3.1 农业技术知识库管理模块<br>              
1.	内容上传：可以上传技术文档（支持 PDF/Word 格式）、视频（支持 MP4 格式，单个文件不超过 500MB）、图文等
2.	内容管理：修改和删除已上线的技术内容
2.3.2 农业原料销售模块<br>
1.	原料管理：上传农业原料信息（名字价格库存等），编辑和删除原料信息
2.	订单处理：查看农民采购订单，、处理退款申请
2.3.3 专家管理模块<br>
1.	专家入驻：上传专家信息（姓名、职称、研究领域、身份证号、学历证书、工作证明）
2.	咨询管理：查看农民预约请求，接受 / 拒绝预约（拒绝需说明原因），回复农民的请求


## 四、管理员端
4.1 商品管理模块<br>
后台查看在售的商品，可以下架违规的商品

4.2 用户管理模块<br>
1.	农民管理：查看农民的信息，可以修改删除
2.	消费者 / 采购商管理：查看用户的信息，可以修改删除


# 运行截图

## IDEA截图

<img width="1918" height="1015" alt="image" src="https://github.com/user-attachments/assets/3607feb6-5c7c-46d6-a688-c29511cead5f" />

## 登陆/注册功能

<img width="1863" height="913" alt="image" src="https://github.com/user-attachments/assets/da0c4136-d8e2-43dc-87a1-a88a4a003665" />

<img width="1848" height="898" alt="image" src="https://github.com/user-attachments/assets/b743cb6d-adb2-4f6e-bed1-bf05c4bb51e3" />


<img width="1786" height="848" alt="image" src="https://github.com/user-attachments/assets/078865fa-aff4-43ac-8bbb-594a7c563807" />

## 管理员端

<img width="1841" height="913" alt="image" src="https://github.com/user-attachments/assets/4d2d6d8f-2f19-4085-bb46-f2f827bd9786" />

<img width="1867" height="920" alt="image" src="https://github.com/user-attachments/assets/3265069c-c563-408f-87c3-445436f3cf6a" />


<img width="1850" height="911" alt="image" src="https://github.com/user-attachments/assets/a423a1ec-a4df-4f2c-9bb7-12ab40e36881" />

<img width="1842" height="917" alt="image" src="https://github.com/user-attachments/assets/b95a25b7-ed82-4dd1-a01e-f7f94d958c40" />


<img width="1845" height="912" alt="image" src="https://github.com/user-attachments/assets/9bcd101c-adf1-4177-a0f3-d9a92b2f6e7f" />

<img width="1841" height="915" alt="image" src="https://github.com/user-attachments/assets/e209107e-8c75-4e23-a013-d5f821bb29dc" />

<img width="1858" height="912" alt="image" src="https://github.com/user-attachments/assets/e91ff46f-a601-413e-b677-1f1a22a8035d" />

<img width="1850" height="917" alt="image" src="https://github.com/user-attachments/assets/e86ff8e9-ae62-4c25-b29f-2d35a7d4545a" />

<img width="1847" height="918" alt="image" src="https://github.com/user-attachments/assets/bf3ecb29-0a1d-4d78-b405-7f8e61f41a9b" />


<img width="1852" height="913" alt="image" src="https://github.com/user-attachments/assets/e3652492-777c-4b2a-b55a-27f282061c95" />

<img width="1853" height="911" alt="image" src="https://github.com/user-attachments/assets/c8a2aed6-8f5f-46fd-bb74-ceb70663cb15" />


## 农民端

<img width="1845" height="911" alt="image" src="https://github.com/user-attachments/assets/79da9c57-1bd6-47a2-ac1f-a9056f9ec87b" />

<img width="1837" height="912" alt="image" src="https://github.com/user-attachments/assets/03d822ac-0e9c-4f67-977c-48ed2174166a" />

<img width="1862" height="915" alt="image" src="https://github.com/user-attachments/assets/c311d45f-ef26-45a1-978c-633d5a0cd13b" />

<img width="1847" height="906" alt="image" src="https://github.com/user-attachments/assets/b90bb612-887f-493e-be4e-e6079d4e7baa" />

<img width="1835" height="911" alt="image" src="https://github.com/user-attachments/assets/b83d8cab-f277-48c0-8c49-2f72c77a65e1" />

<img width="1851" height="901" alt="image" src="https://github.com/user-attachments/assets/17285697-7ef7-47e5-b5fc-cc08cb0ac59c" />

<img width="1853" height="911" alt="image" src="https://github.com/user-attachments/assets/276ddf4c-d29f-4b9f-8ea6-9e2f0ada4b9b" />



## 高校端

<img width="1888" height="927" alt="image" src="https://github.com/user-attachments/assets/80d2e8f8-697f-407a-aaf5-f4bef603022e" />

<img width="1851" height="915" alt="image" src="https://github.com/user-attachments/assets/074c4f3a-76b0-4c74-9141-567adbac85aa" />

<img width="1837" height="901" alt="image" src="https://github.com/user-attachments/assets/15285ec2-5080-419f-bc4b-09a323e6e64c" />

<img width="1837" height="903" alt="image" src="https://github.com/user-attachments/assets/825303f7-9523-4969-8489-187afeb8a1ef" />

<img width="1845" height="898" alt="image" src="https://github.com/user-attachments/assets/306cc7c2-9852-4627-952d-c704715cc14e" />



## 消费者端


<img width="1842" height="1024" alt="image" src="https://github.com/user-attachments/assets/6638e96e-fbee-49bf-bbf7-bcd030a5c309" />


<img width="1868" height="971" alt="image" src="https://github.com/user-attachments/assets/0129f684-52e6-403b-bebf-3cd8d6ecc949" />

<img width="1850" height="912" alt="image" src="https://github.com/user-attachments/assets/cf81d08f-f506-4148-8f37-090a118b4b3c" />

<img width="1848" height="908" alt="image" src="https://github.com/user-attachments/assets/d6f37b2a-fc3b-43f5-ba52-37d52f438263" />

<img width="1850" height="908" alt="image" src="https://github.com/user-attachments/assets/7af0a1a0-95f2-40d4-8d67-9a3043e177b1" />

<img width="1867" height="917" alt="image" src="https://github.com/user-attachments/assets/59c211e6-3e1a-450a-aabf-0b2a09f67bbe" />


<img width="1837" height="911" alt="image" src="https://github.com/user-attachments/assets/065faade-41e6-4f38-91b8-30ea2bfa625c" />

<img width="1851" height="913" alt="image" src="https://github.com/user-attachments/assets/55216311-81d1-4daa-93b4-ca9950d6d269" />

<img width="1862" height="918" alt="image" src="https://github.com/user-attachments/assets/f6eb7349-cc0a-4dbc-9ae1-c1e2439f3b5d" />


<img width="1857" height="915" alt="image" src="https://github.com/user-attachments/assets/6f5961f9-4be1-4145-91be-299d37f8e836" />


<img width="1867" height="908" alt="image" src="https://github.com/user-attachments/assets/cccf1e0c-27fd-49df-afac-7a4b3d616fc9" />








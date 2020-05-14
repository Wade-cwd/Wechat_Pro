layui.use(['element','jquery','table','layer'],function () {
        let $=layui.jquery,table = layui.table,element=layui.element,layer=layui.layer;
        //失物招领表头
        let  lostTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'article', title:'标题', edit: 'text',templet:'<div><span>{{d.article}}</span></div>'}
            ,{field:'feature', title:'特点', edit: 'text',templet:'<div><span>{{d.feature}}</span></div>'}
            ,{field:'lostdate', title:'丢失时间',edit: 'text',}
            ,{field:'lostplace', title:'丢失地点',  edit: 'text'}
            ,{field:'phone', title:'联系电话',  edit: 'text'}
            ,{field: 'image', title:'图片名称',edit: 'text',templet:'<div><span>{{d.image}}</span></div>'}
            ,{field: 'issuedate', title:'提问日期',edit: 'text'}
            ,{field: 'mark', title:'备注',edit: 'text',templet:'<div><span>{{d.mark}}</span></div>'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        //寻物启事表头
        let foundTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'article', title:'标题', edit: 'text',templet:'<div><span>{{d.article}}</span></div>'}
            ,{field:'feature', title:'特点', edit: 'text',templet:'<div><span>{{d.feature}}</span></div>'}
            ,{field:'founddate', title:'拾取时间',edit: 'text',}
            ,{field:'foundplace', title:'拾取地点',  edit: 'text'}
            ,{field:'phone', title:'联系电话',  edit: 'text'}
            ,{field: 'image', title:'图片名称',edit: 'text',templet:'<div><span>{{d.image}}</span></div>'}
            ,{field: 'issuedate', title:'提问日期',edit: 'text'}
            ,{field: 'mark', title:'备注',edit: 'text',templet:'<div><span>{{d.mark}}</span></div>'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        //工作兼职表头
        let jobTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'workName', title:'职位名称', edit: 'text',templet:'<div><span>{{d.workName}}</span></div>'}
            ,{field:'companyName', title:'公司名称', edit: 'text',templet:'<div><span>{{d.companyName}}</span></div>'}
            ,{field:'workPlace', title:'工作地点',edit: 'text',templet:'<div><span>{{d.workPlace}}</span></div>'}
            ,{field:'workContent', title:'工作内容',  edit: 'text'}
            ,{field:'contact', title:'联系方式',  edit: 'text'}
            ,{field: 'workTime', title:'工作时间',edit: 'text'}
            ,{field: 'salary', title:'薪资',edit: 'text'}
            ,{field: 'mark', title:'备注',edit: 'text',templet:'<div><span>{{d.mark}}</span></div>'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        //二手市场表头
        let marketTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'article', title:'标题', edit: 'text'}
            ,{field:'price', title:'价格', edit: 'text'}
            ,{field:'feature', title:'物品特点',edit: 'text',templet:'<div><span>{{d.feature}}</span></div>'}
            ,{field:'images', title:'图片地址',  edit: 'text',templet:'<div><span>{{d.images}}</span></div>'}
            ,{field:'contact', title:'联系方式',  edit: 'text'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        //待审核话题表头
        let auditingTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'article', title:'标题', edit: 'text'}
            ,{field:'content', title:'内容', edit: 'text',templet:'<div><span>{{d.content}}</span></div>'}
            ,{field:'type', title:'话题类型',  edit: 'text'}
            ,{field:'image', title:'图片地址',  edit: 'text',templet:'<div><span>{{d.image}}</span></div>'}
            ,{field:'isCheck', title:'是否审核通过',  edit: 'text',
                templet:function (d) {
                    switch (d.isCheck) {
                        case 1:
                            return '已审核';break;
                        case 0:
                            return '未审核';break;
                    }
                }
            }
            ,{field:'publicDateTime', title:'发布日期',  edit: 'text'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        /*已发布话题*/
        let topicsTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'article', title:'标题', edit: 'text'}
            ,{field:'content', title:'内容', edit: 'text',templet:'<div><span>{{d.content}}</span></div>'}
            ,{field:'type', title:'话题类型',  edit: 'text'}
            ,{field:'image', title:'图片地址',  edit: 'text',templet:'<div><span>{{d.image}}</span></div>'}
            ,{field:'isCheck', title:'是否审核通过',  edit: 'text',
                templet:function (d) {
                    switch (d.isCheck) {
                        case 1:
                            return '已审核';break;
                        case 0:
                            return '未审核';break;
                    }
                }
            }
            ,{field:'publicDateTime', title:'发布日期',  edit: 'text'}
            ,{field:'viewCount', title:'查看人数',  edit: 'text'}
            ,{field:'peopleSize', title:'参与人数',  edit: 'text'}
            ,{field:'thumbUp', title:'点赞数',  edit: 'text'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        /*评论表头*/
        let commentTitle=[
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识',templet:'<div><span>{{d.uid}}</span></div>'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'targetOpenid', title:'目标微信标识',templet:'<div><span>{{d.targetOpenid}}</span></div>'}
            ,{field:'targetUid', title:'目标唯一标识', templet:'<div><span>{{d.targetUid}}</span></div>'}
            ,{field:'commentDatetime', title:'评论日期'}
            ,{field:'content', title:'内容',  edit: 'text',templet:'<div><span>{{d.content}}</span></div>'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
        /*实名认证审核表头*/
        let certTitle=[
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
            ,{field:'uid', title:'唯一标识',templet:'<div><span>{{d.uid}}</span></div>'}
            ,{field:'nickName', title:'昵称', edit: 'text'}
            ,{field:'avatarUrl', title:'头像地址',templet:'<div><span>{{d.avatarUrl}}</span></div>'}
            ,{field:'idCard', title:'身份证号',templet:'<div><span>{{d.idCard}}</span></div>'}
            ,{field:'images', title:'图片地址', templet:'<div><span>{{d.targetUid}}</span></div>'}
            ,{field:'wechat', title:'微信号'}
            ,{field:'phone', title:'电话号码'}
            ,{field:'name', title:'姓名'}
            ,{field:'isCheck', title:'审核情况',
                templet:function (d) {
                    switch (d.isCheck) {
                        case 1:
                            return '已通过';break;
                        case 0:
                            return '未提交';break;
                        case 2:
                            return '审核中';break;
                        case -1:
                            return '未通过';break;
                    }
                },edit: 'text'
            }
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ];
    let map=new Map();
    map.set('lostTitle',lostTitle);
    map.set('foundTitle',foundTitle);
    map.set('jobTitle',jobTitle);
    map.set('marketTitle',marketTitle);
    map.set('auditingTitle',auditingTitle);
    map.set('topicsTitle',topicsTitle);
    map.set('commentTitle',commentTitle);
    map.set('certTitle',certTitle);
    map.forEach(function (value,key) {
        let elementId='',tableId='',url='',content=null,evenFilter='';
        switch (key) {
            /*失物招领数据*/
            case 'lostTitle':
                elementId='lostData',tableId='lostTable';
                url='http://localhost:8080/lost/getLostJson/1/10';
                content=value,evenFilter='lostEvent';
                break;
            /*寻物启事*/
            case 'foundTitle':
                elementId='foundData',tableId='foundTable';
                url='http://localhost:8080/found/getFound_LayUI/1/10';
                content=value,evenFilter='foundEvent';
                break;
            /*工作兼职*/
            case 'jobTitle':
                elementId='jobData',tableId='jobTable';
                url='http://localhost:8080/job/getJob_LayUI/1/10';
                content=value,evenFilter='jobEvent';
                break;
            /*二手市场*/
            case 'marketTitle':
                elementId='marketData',tableId='marketTable';
                url='http://localhost:8080/market/getMarket_LayUI/1/10';
                content=value,evenFilter='marketEvent';
                break;
            /*待审核话题*/
            case 'auditingTitle':
                elementId='auditingTopic',tableId='auditingTable';
                url='http://localhost:8080/topic/getAuditingTopics/1/10';
                content=value,evenFilter='auditingEvent';
                break;
            /*已发布话题*/
            case 'topicsTitle':
                elementId='publicTopics',tableId='publicTopicTable';
                url='http://localhost:8080/topic/getPublicTopics/1/10';
                content=value,evenFilter='topicEvent';
                break;
            /*评论*/
            case 'commentTitle':
                elementId='commentData',tableId='commentTable';
                url='http://localhost:8080/topic/getComments/1/10';
                content=value,evenFilter='commentEvent';
                break;
            /*身份认证*/
            case 'certTitle':
                elementId='certData',tableId='certTable';
                url='http://localhost:8080/common/getCerts/1/10';
                content=value,evenFilter='certEvent';
                break;
        }
        $('#'+elementId).on('click',function () {
            $('#table_div').empty();
            $('#table_div').append('<table class="layui-hide"  id="'+tableId+'" lay-filter="'+evenFilter+'"></table>');
            //表格数据显示
            table.render({
                elem: '#'+tableId
                ,url:url
                ,method:'POST'
                ,title: '用户数据表'
                ,text:'没有数据'
                ,cellMinWidth: 80
                ,cols: [content]
                ,page: true,
                error:function (res) {
                    layer.msg('数据请求出错:'+JSON.stringify(res))
                }
            });
        });
        /*编辑事件*/
        table.on('edit('+evenFilter+')',function (obj) {
            let json=JSON.parse(JSON.stringify(obj.data));
            let updateUrl='';
            switch (evenFilter) {
                case "lostEvent":
                    updateUrl='http://localhost:8080/lost/updateLostField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "foundEvent":
                    updateUrl='http://localhost:8080/found/updateFoundField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "jobEvent":
                    updateUrl='http://localhost:8080/job/updateJobField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "marketEvent":
                    updateUrl='http://localhost:8080/market/updateMarketField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "auditingEvent":
                    updateUrl='http://localhost:8080/topic/updateField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "topicEvent":
                    updateUrl='http://localhost:8080/topic/updateField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "commentEvent":
                    updateUrl='http://localhost:8080/topic/updateCommentField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;
                case "certEvent":
                    updateUrl='http://localhost:8080/common/updateCommentField/'+
                        obj.field+'/'+obj.value+'/'+json.uid+'/'+json.openid;
                    break;

            }
            /*提示*/
            layer.open({
                type: 1
                ,title:'修改提示'
                ,offset: 'auto'
                ,id: json.uid==''?json.openid:json.uid //防止重复弹出
                ,content: '<div style="padding: 20px 100px;">'+ '修改为：'+obj.value+'？' +'</div>'
                ,btn: ['确定','取消']
                ,btnAlign: 'c' //按钮居中
                ,shade: 0.3 //不显示遮罩
                ,anim:6
                ,yes: function(index,obj){//确定
                    $.ajax(updateUrl,{
                        type:'POST',
                        success:function () {
                            //重置表格
                            table.reload(tableId,{
                                page:{
                                    curr:1
                                }
                            });
                        }
                    });
                    layer.closeAll();
                }
                ,btn2:function (index,obj) {//取消
                        //重置表格
                        table.reload(tableId,{
                            page:{
                                curr:1
                            }
                        });
                    layer.closeAll();
                }
            });
        });
        /*删除事件*/
        table.on('tool('+evenFilter+')',function (obj) {
            let json=JSON.parse(JSON.stringify(obj.data));
            let event=obj.event,delUrl='';
            switch (evenFilter) {
                case "lostEvent":
                    delUrl='http://localhost:8080/lost/deleteOneLost/'+json.uid+'/'+json.openid;
                    break;
                case "foundEvent":
                    delUrl='http://localhost:8080/found/deleteOneFound/'+json.uid+'/'+json.openid;
                    break;
                case "jobEvent":
                    delUrl='http://localhost:8080/job/deleteOneJob/'+json.uid+'/'+json.openid;
                    break;
                case "marketEvent":
                    delUrl='http://localhost:8080/market/deleteOneMarket/'+json.uid+'/'+json.openid;
                    break;
                case "auditingEvent":
                    delUrl='http://localhost:8080/topic/deleteOneTopic/'+json.uid+'/'+json.openid;
                    break;
                case "topicEvent":
                    delUrl='http://localhost:8080/topic/deleteOneTopic/'+json.uid+'/'+json.openid;
                    break
                case "commentEvent":
                    delUrl='http://localhost:8080/topic/deleteOneComment/'+json.uid+'/'+json.openid;
                    break;
                case "certEvent":
                    delUrl='http://localhost:8080/topic/deleteOneComment/'+json.uid+'/'+json.openid;
                    break;
            }
           // 删除操作
           if(event==='del'){
               layer.confirm('确定删除这条记录?', function(index){
                   obj.del();
                   $.ajax(delUrl,{
                        type:'POST',
                       success:function (res) {
                           //重置表格
                           table.reload(tableId,{
                               page:{
                                   curr:1
                               }
                           });
                       }
                   });
                   layer.close(index);
               });
           }
        });

    });



})








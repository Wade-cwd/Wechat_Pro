layui.use(['element','jquery','table','layer'],function () {
        let $=layui.jquery,table = layui.table,element=layui.element,layer=layui.layer;
        //失物招领表头
        let  lostTitle=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
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
        let jobTable=[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID',  fixed: 'left', unresize: true, sort: true}
            ,{field:'openid', title:'微信标识'}
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
        //失物招领数据初始化
        $('#lostData').on('click',function () {
            $('#table_div').empty();
            $('#table_div').append('<table class="layui-hide"  id="lostTable"></table>');
            //表格数据显示
            table.render({
                elem: '#lostTable'
                ,url:'http://localhost:8080/lost/getLostJson/1/10'
                ,method:'POST'
                ,title: '用户数据表'
                ,text:'失物招领数据'
                ,cellMinWidth: 80
                ,cols: [lostTitle]
                ,page: true,
                error:function (res) {
                    layer.msg('数据请求出错:'+JSON.stringify(res))
                }
            });
        });
        $('#foundData').on('click',function () {
            $('#table_div').empty();
            $('#table_div').append('<table class="layui-hide"  id="foundTable"></table>');
            //表格数据显示
            table.render({
                elem: '#foundTable'
                ,url:'http://localhost:8080/found/getFound_LayUI/1/10'
                ,method:'POST'
                ,title: '用户数据表'
                ,text:'寻物启事数据'
                ,cellMinWidth: 80
                ,cols: [foundTitle]
                ,page: true,
                error:function (res) {
                    layer.msg('数据请求出错:'+JSON.stringify(res))
                }
            });
        });
    $('#jobData').on('click',function () {
        $('#table_div').empty();
        $('#table_div').append('<table class="layui-hide"  id="jobTable"></table>');
        //表格数据显示
        table.render({
            elem: '#jobTable'
            ,url:'http://localhost:8080/job/getJob_LayUI/1/10'
            ,method:'POST'
            ,title: '用户数据表'
            ,cellMinWidth: 80
            ,cols: [jobTable]
            ,page: true,
            error:function (res) {
                layer.msg('数据请求出错:'+JSON.stringify(res))
            }
        });

    });


})








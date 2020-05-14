<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <script src="/js/vue.min.js" type="application/javascript"></script>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/index.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">生活小助手后台管理</div>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div id="left_Nav" class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  >
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="JavaScript:;" >信息管理</a>
                    <dl class="layui-nav-child">
                        <dd id="lostData" ><a href="javascript:void(0)">失物招领</a></dd>
                        <dd id="foundData" ><a href="javascript:void(0)">寻物启事</a></dd>
                        <dd id="jobData"><a href="javascript:void(0)">工作兼职</a></dd>
                        <dd id="marketData"><a href="javascript:void(0)">二手市场</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">话题管理</a>
                    <dl class="layui-nav-child">
                        <dd id="auditingTopic"><a href="javascript:void(0);">待审核</a></dd>
                        <dd id="publicTopics"><a href="javascript:;">已发布</a></dd>
                        <dd id="commentData"><a href="javascript:;">所有评论</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd id="certData"><a href="javascript:;">实名认证审核</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->

        <div id="table_div"></div>

        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>


    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © www.chiwenda.xyz - 制作者池文达
    </div>
</div>


<script src="/layui/layui.all.js"></script>
<script src="/js/index.js"></script>
</body>
</html>

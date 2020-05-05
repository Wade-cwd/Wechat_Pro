<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<#--    ie浏览器兼容-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>管理员登录</title>
    <!-- Bootstrap -->
    <link href="http://www.chiwenda.xyz:81/static/bootstrap.min.css" rel="stylesheet">
    <link href="login.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->

    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
</head>
<body>
    <div class="login_box">
        <label class="login_title text-center">
            <span class="fa fa-user"></span>
            管理员登录
        </label>
        <div>
            <form>
                <section>
                    <div class="navbar-form text-center">
                        <div class="form-group ">
                            <input type="text" class="form-control" placeholder="请输入账号" autofocus required="用户名">
                        </div>
                        <div class="form-group customer_margin_top">
                            <input type="password" class="form-control" placeholder="请输入密码" required="密码必填">
                        </div>
                        <div class="form_submit customer_margin_top">
                            <input type="submit" class="btn btn-info submit_btn">
                        </div>
                    </div>
                </section>
            </form>
        </div>
    </div>

    <todo-item></todo-item>
</body>
<script type="application/javascript">

    Vue.component('todo-item',{
        template:'<div>组件</div>'
    })
    var app=new Vue();
</script>
<script src="http://www.chiwenda.xyz:81/static/jquery.min.js"></script>
<script src="http://www.chiwenda.xyz:81/static/bootstrap.min.js"></script>
</html>
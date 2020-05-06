
new Vue({
    el:'#form',
    data:{
        userName:"",
        password:""
    },
    methods:{
        submit:function (e) {
            $.ajax({
                type:"POST",
                data:{

                },
                url:'http://localhost:8080/admin/login/admin/admin',
                success:function (data) {
                    alert("请求成功"+data.toString())
                }
            })
        }
    }
})
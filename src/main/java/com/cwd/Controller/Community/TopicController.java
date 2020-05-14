package com.cwd.Controller.Community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.Comment;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Topic;
import com.cwd.Service.Community.TopicService;
import com.cwd.Utils.FileUtil;
import com.cwd.Utils.LayUI;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    GlobalConfig globalConfig;
    @Autowired
    FileUtil fileUtil;

    //申请话题
    @PostMapping("/postTopic")
    public void addOneTopicApply(HttpServletRequest request, @PathParam("file") MultipartFile file) {
        String jsonStr = request.getParameter("form_data");
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Topic topic = JSON.parseObject(jsonStr, Topic.class);
        String fileName = fileUtil.writeFileToDirectory(file);
        topic.setImage(fileName);//设置唯一文件名
        topic.setUid(UUID.randomUUID().toString());//设置话题唯一标识
        GlobalConfig.getLog(this.getClass()).info("话题:" + topic);
        topicService.addOneTopic(topic);//添加话题
        GlobalConfig.getLog(this.getClass()).info("添加话题成功......");
    }

    /*参加话题*/
    @PostMapping("/joinTopic/{openid}/{addedOpenid}/{addedUid}")
    public void joinTopic(@PathVariable("openid") String openid,@PathVariable("addedOpenid") String addedOpenid,
                          @PathVariable("addedUid") String addedUid){
        GlobalConfig.getLog(this.getClass()).info("处理参加话题请求");
        topicService.joinTopic(openid,addedOpenid,addedUid);
    }

    //获取话题数组
    @GetMapping("/getTopics/{openid}/{checkId}/{pageNo}/{pageSize}")
    public PageInfo<Topic> getUnCheckApplyTopic(@PathVariable("openid") String openid, @PathVariable("checkId") int checkId,
                                                @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        PageInfo<Topic> list = null;
        if (checkId == 3) {
            //加入的话题数据
            GlobalConfig.getLog(this.getClass()).info("已参加的话题");
            list = topicService.getAddedTopics(openid, pageNo, pageSize);
        } else {
            //非加入的话题数据
            list = topicService.getTopics(openid, checkId, pageNo, pageSize);
        }

        return list;
    }
    /*获取待审核话题,返回layui支持的json格式
    *后台
    * */
    @PostMapping("/getAuditingTopics/{pageNo}/{pageSize}")
    public Object getAuditingTopics(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        PageInfo<Topic> auditingTopics=topicService.getAuditingTopics(pageNo,pageSize);
        Integer count=topicService.getAuditingTopicsCount();
        return LayUI.getLayUIFormatData(auditingTopics,count);
    }
    /*全部已发布话题，Layui的json支持格式*/
    @PostMapping("/getPublicTopics/{pageNo}/{pageSize}")
    public Object getPublicTopics(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        PageInfo<Topic> publicTopics=topicService.getPublicTopics(pageNo,pageSize);
        Integer count=topicService.getPublicTopicsCount();
        return LayUI.getLayUIFormatData(publicTopics,count);
    }

    /*获取最热门话题
     * */
    @GetMapping("/getHotTopic")
    public Topic getHotTopic() {
        return topicService.getHotTopic();
    }

    /*获取话题*/
    @GetMapping("/getTopic/{openid}/{uid}/{isCheck}")
    public Topic getTopic(@PathVariable("openid") String openid, @PathVariable("uid") String uid,
                          @PathVariable("isCheck") int isCheck) {
        return topicService.getTopic(openid, uid, isCheck);
    }

    /*话题榜单*/
    @GetMapping("/getTopicList/{pageNo}/{pageSize}")
    public PageInfo<Topic> getListDesc(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return topicService.getTopicList(pageNo, pageSize);
    }

    /*添加评论*/
    @PostMapping("/addUserComment")
    public void addUserComment(@RequestBody Comment comment) {
        comment.setUid(UUID.randomUUID().toString());
        GlobalConfig.getLog(this.getClass()).info("开始添加评论:" + comment.toString());
        topicService.addUserComment(comment);
    }

    /*获取用户所有评论*/
    @GetMapping("/getAllComments/{openid}/{uid}/{pageNo}/{pageSize}")
    public PageInfo<Comment> getAllComments(@PathVariable("openid") String openid, @PathVariable("uid") String uid,
                                            @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return topicService.getAllComments(openid, uid, pageNo, pageSize);
    }
    /*获取所有评论,LayUI格式*/
    @PostMapping("/getComments/{pageNo}/{pageSize}")
    public Object getComments(@PathVariable("pageNo")int pageNo,@PathVariable("pageSize")int pageSize){
        PageInfo<Comment> conment=topicService.getComments(pageNo,pageSize);
        Integer count =topicService.getCommentCount();
        return LayUI.getLayUIFormatData(conment,count);
    }
    /*删除一条评论*/
    @PostMapping("/deleteOneComment/{uid}/{openid}")
    public Integer deleteOneComment(@PathVariable("uid") String uid,@PathVariable("openid") String openid){
        return topicService.delOneComment(uid,openid);
    }


    /*添加评论数*/
    @PutMapping("/addPeopleSize/{openid}/{uid}")
    public void addCommentPeopleSize(@PathVariable("openid") String openid, @PathVariable("uid") String uid) {
        topicService.addCommentPeopleSize(openid, uid);
        GlobalConfig.getLog(this.getClass()).info("添加评论数成功");
    }

    /*返回评论数*/
    @GetMapping("/getPeopleSize/{openid}/{uid}")
    public int getPeopleSize(@PathVariable("openid") String openid, @PathVariable("uid") String uid) {
        return topicService.getCommentPeopleSize(openid, uid);
    }

    /*添加评论查看人数*/
    @PutMapping("/addViewCount/{openid}/{uid}")
    public void addViewCount(@PathVariable("openid") String openid, @PathVariable("uid") String uid) {
        topicService.addViewCount(openid, uid);
    }

    /*返回查看人数*/
    @GetMapping("/getViewCount/{openid}/{uid}")
    public int getViewCount(@PathVariable("openid") String openid, @PathVariable("uid") String uid) {
        return topicService.getViewCount(openid, uid);
    }

    /*增加点赞数*/
    @PutMapping("/plusThumbUp/{openid}/{uid}/{userId}")
    public void plusThumbUp(@PathVariable("openid") String openid,@PathVariable("uid") String uid,
                            @PathVariable("userId") String userOpenid){
        topicService.plusThumbUp(openid,uid,userOpenid);
    }

    /*返回是否可点赞*/
    @GetMapping("/canPlusThumb/{openid}/{uid}/{userId}")
    public Boolean getCanPlusThumb(@PathVariable("openid") String openid,@PathVariable("uid") String uid,
                               @PathVariable("userId") String userOpenid){
        return topicService.canPlusThumb(userOpenid,openid,uid);
    }

    /*标记已点赞*/
    @PostMapping("/setHasPlusThumb/{openid}/{topicOpenid}/{topicUid}")
    public void setPlusThumb(@PathVariable("openid") String openid,@PathVariable("topicOpenid") String topicOpenid,
                             @PathVariable("topicUid") String topicUid){
        topicService.setHasPlusThumb(openid,topicOpenid,topicUid);
    }

    /*查询点赞人数*/
    @GetMapping("/getThumbUp/{openid}/{uid}")
    public  int getThumbUp(@PathVariable("openid") String openid,@PathVariable("uid") String uid){
        return topicService.getPlusThumbUp(openid,uid);
    }


    //下载图片
    @GetMapping("/downloadImg/{imgPath}")
    public void downloadImg(HttpServletResponse response, @PathVariable("imgPath") String imgPath) {
        GlobalConfig.getLog(this.getClass()).info("开始下载图片" + imgPath);
        File file = new File(globalConfig.getLocalFilePath() + imgPath);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);//文件读取流
                byte[] readBytes = new byte[1024];//一次读取1024字节
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                while ((bufferedInputStream.read(readBytes)) != -1) {
                    response.getOutputStream().write(readBytes);
                }
                response.getOutputStream().flush();
                bufferedInputStream.close();
                fileInputStream.close();
                GlobalConfig.getLog(this.getClass()).info("图片下载完成" + imgPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*更新话题字段内容*/
    @PostMapping("/updateField/{fieldName}/{value}/{uid}/{openid}")
    public Integer postUpdateField(@PathVariable("fieldName") String fieldName,@PathVariable("value")String value,
                                   @PathVariable("uid") String uid,@PathVariable("openid")String openid){
        return topicService.setTopicField(fieldName,value,uid,openid);
    }
    /*更新评论字段内容*/
    @PostMapping("/updateCommentField/{fieldName}/{value}/{uid}/{openid}")
    public Integer postUpdateCOmmentField(@PathVariable("fieldName") String fieldName,@PathVariable("value")Object value,
                                   @PathVariable("uid") String uid,@PathVariable("openid")String openid){
        return topicService.setCommentField(fieldName,value,uid,openid);
    }
    /*删除一条话题*/
    @PostMapping("/deleteOneTopic/{uid}/{openid}")
    public Integer postDeleteTopic(@PathVariable("uid")String uid,@PathVariable("openid")String openid){
        return topicService.delOneTopic(uid,openid);
    }

}

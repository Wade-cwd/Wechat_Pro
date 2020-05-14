package com.cwd.Service.Community;

import com.cwd.Entity.*;
import com.cwd.Mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class TopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private AddedApplications addedApplications;


    //申请话题
    public void addOneTopic(Topic topic) {
        //添加话题
        topicMapper.addTopic(topic);
        //添加到已参加话题
        addedApplications.setOpenid(topic.getOpenid());
        addedApplications.setAddedOpenid(topic.getOpenid());
        addedApplications.setUid(UUID.randomUUID().toString());
        addedApplications.setAddedUid(topic.getUid());
        topicMapper.postAddedApplication(addedApplications);
    }

    /*参加话题*/
    public void joinTopic(String openid, String addedOpenid, String addedUid) {
        addedApplications.setUid(UUID.randomUUID().toString());
        addedApplications.setOpenid(openid);
        addedApplications.setAddedOpenid(addedOpenid);
        addedApplications.setAddedUid(addedUid);
        if (isJoinedTopic(addedApplications)) {
            GlobalConfig.getLog(this.getClass()).info("已参加了话题");
        } else {
            topicMapper.postAddedApplication(addedApplications);
            GlobalConfig.getLog(this.getClass()).info("参加话题成功");
        }
    }

    /*是否已参加话题*/
    public boolean isJoinedTopic(AddedApplications addedApplications) {
        Integer count = topicMapper.isJoinedTopic(addedApplications);
        if (count != null) {
            if (count > 0) {
                return true;//已参加
            }
        }
        return false;//未参加
    }

    //获取话题数据数组

    public PageInfo<Topic> getTopics(String openid, int checkNo, int pageNo, int pageSize) {
        List<Topic> topicList = null;
        PageHelper.startPage(pageNo, pageSize);//分页
        if (openid != null) {
            topicList = topicMapper.getApplyingTopic(openid, checkNo);//指定用户
        } else {
            topicList = topicMapper.getAllTopics(checkNo);//所有用户
        }
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    /*获取已参加的所有话题
     *opnid：当前用户唯一标识
     * addedOpenid：参加的话题的拥有者唯一标识
     * addedUid：参加的话题的拥有者唯一标识对应的话题唯一标识
     * */
//    @Cacheable
    public PageInfo<Topic> getAddedTopics(String openid, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> topicList = new ArrayList<>();
        //先从已加入的话题列表找到对应openid
        List<AddedApplications> addedApplicationsList = topicMapper.getAddedApplications(openid);
        GlobalConfig.getLog(this.getClass()).info("已参加列表" + addedApplicationsList);
        if (addedApplicationsList.size() > 0) {
            for (AddedApplications addedList : addedApplicationsList) {
                List<Topic> topics = topicMapper.getAddedApplicationTopics(addedList.getAddedOpenid(), addedList.getAddedUid());
                for (Topic topic : topics) {
                    topicList.add(topic);
                }
            }
        }
        GlobalConfig.getLog(this.getClass()).info("已参加话题列表" + topicList);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }
    /*获取已发布的全部话题*/
    public PageInfo<Topic> getPublicTopics(int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> publicTopics=topicMapper.selectPublicTopics();
        PageInfo<Topic> publicTopicsPageInfo=new PageInfo<>(publicTopics);
        return publicTopicsPageInfo;
    }
    /*获取已发布话题数量*/
    public Integer getPublicTopicsCount(){
        Integer count=topicMapper.selectPublicTopicCount();
        if(count!=null&&count>0){
            return count;
        }else{
            return 0;
        }
    }

    /*获取待审核话题*/
    public PageInfo<Topic> getAuditingTopics(int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> auditingTopics=topicMapper.selectAuditingTopics();
        PageInfo<Topic> pageInfoTopics=new PageInfo<>(auditingTopics);
        return pageInfoTopics;
    }
    /*获取待审核话题数量*/
    public Integer getAuditingTopicsCount(){
        Integer count=topicMapper.selectAuditingTopicCount();
        if(count!=null&&count>0){
            return count;
        }else{
            return 0;
        }
    }

    /*获取参加人数最多的话题
     * */
//    @Cacheable
    public Topic getHotTopic() {
        return topicMapper.hotTopic();
    }

    /*获取话题*/
//    @Cacheable
    public Topic getTopic(String openid, String uid, int isCheck) {
        return topicMapper.getTopic(openid, uid, isCheck);
    }

    /*话题榜*/
//    @Cacheable
    public PageInfo<Topic> getTopicList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> topicList = topicMapper.topicListOrderByPeopleSize();
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topicList);
        return topicPageInfo;
    }


    /*添加评论*/
    public void addUserComment(Comment comment) {
        topicMapper.addComment(comment);
    }

    /*查询用户所有评论*/
//    @Cacheable
    public PageInfo<Comment> getAllComments(String openid, String uid, int pageNo, int pagSize) {
        PageHelper.startPage(pageNo, pagSize);
        List<Comment> comments = topicMapper.getAllComment(openid, uid);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        return commentPageInfo;
    }
    /*查询所有评论*/
    public  PageInfo<Comment> getComments(int pageNo, int pagSize){
        PageHelper.startPage(pageNo, pagSize);
        List<Comment> comments=topicMapper.getComments();
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        return commentPageInfo;
    }
    /*删除一条评论*/
    public Integer delOneComment(String uid,String openid){
        Integer resultCount=topicMapper.deleteComment(uid,openid);
        if(resultCount!=null&&resultCount>0){
            Logger.getGlobal().info("Admin:删除评论成功成功");
            return resultCount;
        }else {
            Logger.getGlobal().info("Admin:删除评论失败请检查");
            return -1;
        }
    }
    /*返回所有评论数量*/
    public Integer getCommentCount(){
        Integer count=topicMapper.selectCommentCount();
        if(count!=null&&count>0){
            return count;
        }else{
            return 0;
        }
    }

    /*添加评论人数*/
    public void addCommentPeopleSize(String openid, String uid) {
        //获取评论数
        int peopleSize = this.getCommentPeopleSize(openid, uid);
        ++peopleSize;
        topicMapper.addCommentPeopleSize(peopleSize, openid, uid);
        GlobalConfig.getLog(this.getClass()).info("添加评论人数成功");
    }

    /*获取评论数*/
    public int getCommentPeopleSize(String openid, String uid) {
        Integer count = topicMapper.getCommentPeopleSize(openid, uid);
        if (count != null) {
            return count;
        }
        return 0;
    }

    /*添加查看人数*/
    public void addViewCount(String openid, String uid) {
        //获取查看人数
        int viewCount = topicMapper.getCommentViewCount(openid, uid);
        ++viewCount;
        topicMapper.addCommentViewCount(viewCount, openid, uid);
    }

    /*获取查看人数*/
    public int getViewCount(String openid, String uid) {
        return topicMapper.getCommentViewCount(openid, uid);
    }

    /*查询点赞数*/
    public int getPlusThumbUp(String openid, String uid) {
        return topicMapper.getThumbUp(openid, uid);
    }

    /*增加点赞数*/
    public void plusThumbUp(String openid, String uid, String userOpenid) {
        if (this.canPlusThumb(userOpenid, openid, uid)) {
            int thumbUp = this.getPlusThumbUp(openid, uid);
            ++thumbUp;
            topicMapper.plusThumbUp(thumbUp, openid, uid);
        }
    }

    /*添加可点赞数据*/
    public void setHasPlusThumb(String openid, String topicOpenid, String topicUid) {
        PlusThumb plusThumb = new PlusThumb(openid, UUID.randomUUID().toString(),
                topicOpenid, topicUid, "", 0);
        if (this.getIsPlusThumb(openid, topicOpenid, topicUid) <= 0) {
            topicMapper.setThumbUp(plusThumb);
            GlobalConfig.getLog(this.getClass()).info("当前用户:" + openid + "已点赞用户：" + topicOpenid + "的话题:" + topicUid);
        }
        GlobalConfig.getLog(this.getClass()).info("已有点赞设置数据");
    }

    /*查询是否已存在设置点赞数据*/
    public Integer getIsPlusThumb(String openid, String topicOpenid, String topicUid) {
        PlusThumb plusThumb = new PlusThumb(openid, "", topicOpenid, topicUid, "", -1);
        Integer count = topicMapper.isExistThumbUp(plusThumb);
        if (count != null) {
            return count;
        }
        return -1;
    }

    /*是否可点赞*/
    public Boolean canPlusThumb(String openid, String topicOpenid, String topicUid) {
        PlusThumb plusThumb = new PlusThumb(openid, "", topicOpenid, topicUid, "", -1);
        PlusThumb result = topicMapper.getPlusThumb(plusThumb);
        //1为可点赞0为不可点赞
        if (result == null || result.getIsTopicPlusThumb() == 1) {
            GlobalConfig.getLog(this.getClass()).info("可点赞");
            return true;
        }
        GlobalConfig.getLog(this.getClass()).info("不可点赞");
        return false;
    }
    /*动态更新话题字段内容*/
    public Integer setTopicField(String fieldName,String value,String uid,String openid){
        Logger.getGlobal().info("更新字段......");
        Integer resultCount= topicMapper.updateTopic(fieldName,value,uid,openid);
        if(resultCount!=null&&resultCount>0){
            Logger.getGlobal().info("更新成功:，更新行数:"+resultCount);
            return resultCount;
        }else{
            Logger.getGlobal().info("更新失败");
            return -1;
        }
    }
    /*动态更新评论字段内容*/
    public  Integer  setCommentField(String fieldName,Object value,String uid,String openid){
        Integer resultCount= topicMapper.updateComment(fieldName,value,uid,openid);
        if(resultCount!=null&&resultCount>0){
            Logger.getGlobal().info("更新成功:，更新行数:"+resultCount);
            return resultCount;
        }else{
            Logger.getGlobal().info("更新失败");
            return -1;
        }
    }
    /*删除话题*/
    public Integer delOneTopic(String uid,String openid){
        Integer delResult=topicMapper.deleteTopic(uid,openid);
        if(delResult!=null&&delResult>0){
            return delResult;
        }else{
            return -1;
        }
    }


}

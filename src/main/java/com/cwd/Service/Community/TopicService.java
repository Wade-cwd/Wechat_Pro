package com.cwd.Service.Community;

import com.cwd.Entity.AddedApplications;
import com.cwd.Entity.Comment;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Topic;
import com.cwd.Mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public PageInfo<Topic> getAddedTopics(String openid, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> topicList = new ArrayList<>();
        //先从已加入的话题列表找到对应openid
        List<AddedApplications> addedApplicationsList = topicMapper.getAddedApplications(openid);
        if (addedApplicationsList.size() > 0) {
            for (AddedApplications addedList : addedApplicationsList) {
                List<Topic> topics = topicMapper.getAddedApplicationTopics(addedList.getAddedOpenid(), addedList.getAddedUid());
                for (Topic topic : topics) {
                    topicList.add(topic);
                }
            }
        }
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    /*获取参加人数最多的话题
     * */
    public Topic getHotTopic() {
        return topicMapper.hotTopic();
    }

    /*获取话题*/
    public Topic getTopic(String openid, String uid, int isCheck) {
        return topicMapper.getTopic(openid, uid, isCheck);
    }

    /*话题榜*/
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

    /*查询所有评论*/
    public PageInfo<Comment> getAllComments(String openid, String uid, int pageNo, int pagSize) {
        PageHelper.startPage(pageNo, pagSize);
        List<Comment> comments = topicMapper.getAllComment(openid, uid);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        return commentPageInfo;
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
    public  int getViewCount(String openid,String uid){
        return topicMapper.getCommentViewCount(openid,uid);
    }

    /*查询点赞数*/
    public int getPlusThumbUp(String openid,String uid){
        return topicMapper.getThumbUp(openid,uid);
    }

    /*增加点赞数*/
    public  void plusThumbUp(String openid,String uid){
        int thumbUp=this.getPlusThumbUp(openid,uid);
        ++thumbUp;
        topicMapper.plusThumbUp(thumbUp,openid,uid);
    }
}

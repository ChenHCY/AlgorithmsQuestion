/* Leetcode 355. Design Twitter

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 

Example 1:

Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 

Constraints:

1 <= userId, followerId, followeeId <= 500
0 <= tweetId <= 104
All the tweets have unique IDs.
At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
*/

 /* 思路： 此题是关于推特的功能OOD面向对象设计，
 
我们使用链表head来储存每一个user自己发的twitter, 使用Hashset来储存和去重每个user的关注list

==》然后通过整合链表来达成getNewsFeed()的不同用户查看前10条推特的要求
*/

class Twitter {
    //因为view the twitter 需要按时间排序，所以需要变量timeStamp来记录发帖时间
    private static int timeStamp = 0;

    //Tweet class(): the details of of twitter **************************************************************
    class Tweet{
        private int id; //every twitter have their own id
        private int time; //the post time of every twitter
        private Tweet next; //使用链表把每个用户发布的推特连接在一起

        public Tweet(int tweetId, int tweetTime){
            this.id = tweetId;
            this.time = tweetTime;
            this.next = null;
        }

        //get the id
        public int getId(){
            return id;
        }

        //get the time
        public int getTimestamp(){
            return time;
        }

        //get the head of twitter
        public Tweet getNext(){
            return next;
        }
    }

    //User Class(): the details of user， 并且user的功能：follow关注 unfollow取关 postTweet发推 ********************************
    class User{
        private int id; // every user have their own id
        private HashSet<Integer> followed; // the followed list of every user
        private Tweet head; // the posted twitter of every user
        
        public User(int userId){
            this.id = userId;
            this.head = null;
            followed = new HashSet<>(); //used the haseset remove duplicate the id
            follow(id); //关注自己的id
        }
        
        //提取当前用户的userId功能
        public int getUserId(){
            return id;
        }
        //提取关注列表功能
        public HashSet<Integer> getUserFollowedList(){
            return followed;
        }
        //提取发布的推特合集
        public Tweet getTweetList(){
            return head;
        }

        //user的功能：follow关注 unfollow取关 postTweet发推
        //follow关注其他用户
        public void follow(int userId){
            followed.add(userId); //把选取关注的id加入到hashset
        }

        //unfollow取关其他用户
        public void unfollow(int userId){
            //不能取关自己
            if(userId != this.id){
                followed.remove(userId);
            }
        }

        //post Twitter 发布一条推特
        public void post(int tweetId){
            Tweet tweet = new Tweet(tweetId, timeStamp); //创建一个新的twitter对象
            //使用当前的twitterId, 同时记录当前时间
            timeStamp++; //改变时间
            //因为推特的顺序要按照从近到远的时间顺序，所以加入到链表时，都要接在链表的头部
            //把新发布的 tweet 作为链表头节点
            tweet.next = head;
            head = tweet; //把新链表赋值给就链表
        }
    }
  
  //Main feature of Twitter -***********************************************************************************

    //建立userId 和 user() object 本身的映射
    //key = userId, value = User obejct()
    private HashMap<Integer, User> userMap; 

    //Initializes the object
    public Twitter() {
        userMap = new HashMap<>();
    }

    //Used the userId create tweetId when user post the tweet
    //build map with userId and tweetId
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)){
            //创建一个新的user映射关系
            userMap.put(userId, new User(userId));
        }
        //使用 User() object的post twitter function to post with this tweetId
        User currUser = userMap.get(userId); //提取userId对应的User object
        currUser.post(tweetId); //post the twitter through this user object
    }

    //check这个userId的前十条推文，每一条必须是由用户关注的或当前用户发送的，按时间顺序, 由近到远
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> viewList = new ArrayList<>();
        //check whether have the User with this UserId, 检查这个User是否存在
        if(!userMap.containsKey(userId)){
            return viewList; //如果不存在，直接输出空的list
        }
        //get the currUser
        User currUser = userMap.get(userId);

        //get the follow list of the curr User
        Set<Integer> followList = currUser.getUserFollowedList();

        //使用优先队列 对于 twitter 按时间顺序进行排序, 储存的是所有关注用户的推特
        PriorityQueue<Tweet> minHeap = new PriorityQueue<>(followList.size(), (a, b) -> {
            return b.time - a.time; //从近到远的降序排列
        });

        //把所有关注的user的twitter帖子整合在一起
        for(int followedUser : followList){
            Tweet posted = userMap.get(followedUser).getTweetList();
            //把每个关注用户的发表的twitter加入到优先队列中
            if(posted == null){
                continue;
            }
            minHeap.add(posted);
        }

        while(!minHeap.isEmpty()){
            //only can check 10 twitters
            if (viewList.size() == 10){
                break;
            }
            //每次提取最近的twitter（time最大的）
            Tweet twt = minHeap.poll();
            //System.out.println(twt.id);
            viewList.add(twt.id); //add the twitter id into res list
             // 将下一篇 twitter 插入进行排序
            if(twt.next != null){
                minHeap.add(twt.getNext());
            }
        }
        return viewList; //返回一个size为10的twitter id list
    }

    //followerId 开始关注 followeeId, 如果任何一个Id对应的user在HashMap中不存在，则create new user() 然后储存进hashMap
    public void follow(int followerId, int followeeId) {
        //如果followerId的用户不存在，则进行创建和储存到hashmap
        if(!userMap.containsKey(followerId)){
            User user = new User(followerId);
            userMap.put(followerId, user);
        }
        //如果followeeId的用户不存在，则进行创建和储存到hashmap
        if(!userMap.containsKey(followeeId)){
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        //使用User() 的 follow() function 功能，把 followeeId加入到followerId对应的User 关注列表中
        userMap.get(followerId).follow(followeeId); 
    }

    //followerId 不再关注 followeeId, 如果任何一个Id不存在，则什么都不做
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || !userMap.containsKey(followeeId)){
            return;
        }
         //使用User() 的 unfollow() function 功能，把followeeI 从 followerId对应的User 关注列表中删除
        User followUser = userMap.get(followerId);
        followUser.unfollow(followeeId); 
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

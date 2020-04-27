USE `coms363_proj3`;

-- 1 ----------------------------------------------------------------------------------------------
SELECT 
	tweets.retweet_count AS RetweetCount,
    tweets.textbody AS TweetText,
    user.screen_name AS UserName,
    user.category AS Category,
    user.sub_category AS SubCategory
FROM tweets 
INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE 
	month(posted) = 1 AND 
    year(posted) = 2016 
ORDER BY retweet_count DESC LIMIT 5;


-- 2 ----------------------------------------------------------------------------------------------
SELECT 
	tagged.hastagname as Hashtag, 
    count(DISTINCT user.ofstate) as NumStates, 
    group_concat(DISTINCT user.ofstate) as ListOfStates 
FROM tagged 
	INNER JOIN tweets ON (
	tagged.tid=tweets.tid
	)
	INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE year(tweets.posted)=2016
GROUP BY tagged.hastagname 
ORDER BY count(DISTINCT ofstate) DESC LIMIT 5;


-- 3 ----------------------------------------------------------------------------------------------
SELECT 
	user.screen_name as ScreenName, 
    user.ofstate as State
FROM tagged 
	INNER JOIN tweets ON (
	tagged.tid=tweets.tid
	)
	INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE tagged.hastagname IN ('HappyNewYear','NewYear','NewYears','NewYearsDay')
GROUP BY user.screen_name 
ORDER BY user.numFollowers DESC LIMIT 12;


-- 4 ----------------------------------------------------------------------------------------------
SELECT 
	user.screen_name as ScreenName, 
    user.sub_category as Party,
    user.numFollowers as NumFollowers
FROM user 
WHERE user.sub_category = 'GOP'
GROUP BY user.screen_name 
ORDER BY user.numFollowers DESC LIMIT 5;

SELECT 
	user.screen_name as ScreenName, 
    user.sub_category as Party,
    user.numFollowers as NumFollowers
FROM user 
WHERE user.sub_category = 'Democrat'
GROUP BY user.screen_name 
ORDER BY user.numFollowers DESC LIMIT 5;


-- 5 ----------------------------------------------------------------------------------------------
SELECT 
	tagged.hastagname as Hashtag,
    user.ofstate as State
FROM tagged 
	INNER JOIN tweets ON (
	tagged.tid=tweets.tid
	)
	INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE 
	user.ofstate IN ('Ohio','Alaska','Alabama') AND
    month(tweets.posted) = 1 AND
    year(tweets.posted) = 2016
GROUP BY tagged.hastagname
ORDER BY tagged.hastagname;


-- 6 ----------------------------------------------------------------------------------------------
SELECT 
	tweets.textbody as TweetText,
    tagged.hastagname as Hashtag,
    user.screen_name as ScreenName,
    user.sub_category as Party
FROM tagged 
	INNER JOIN tweets ON (
	tagged.tid=tweets.tid
	)
	INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE 
	tagged.hastagname = 'Ohio' AND
    (	user.sub_category = 'GOP' OR
        user.sub_category = 'democrat') AND
    user.ofstate ='Ohio' AND
    month(tweets.posted) = 1 AND
    year(tweets.posted) = 2016 
    LIMIT 5;
    
    
-- 7 ----------------------------------------------------------------------------------------------
SELECT 
	DISTINCT user.screen_name as ScreenName,
    user.ofstate as State,
    group_concat(urlused.url) as ListOfURLs
FROM urlused 
	INNER JOIN tweets ON (
	urlused.tid=tweets.tid
	)
	INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE
	user.sub_category = 'GOP' AND
    month(tweets.posted) = 1 AND
    year(tweets.posted) = 2016
GROUP BY user.screen_name;


-- 8 ----------------------------------------------------------------------------------------------
SELECT 
	mentioned.screen_name, 
	user.ofstate,
	group_concat(DISTINCT user.screen_name)
FROM user
	INNER JOIN tweets ON (
	user.screen_name=tweets.posting_user
	)
	INNER JOIN mentioned ON (
	mentioned.tid=tweets.tid
	)
WHERE
	user.sub_category = 'GOP' AND
	month(tweets.posted) = 1 AND
    year(tweets.posted) = 2016
GROUP BY mentioned.screen_name 
ORDER BY count(mentioned.screen_name) DESC LIMIT 5;


-- 9 ----------------------------------------------------------------------------------------------
SELECT 
	tagged.hastagname as Hashtag, 
    count(tagged.hastagname) as Count
FROM tagged 
	INNER JOIN tweets ON (
	tagged.tid=tweets.tid
	)
	INNER JOIN user ON (
	user.screen_name=tweets.posting_user
	)
WHERE 
	user.sub_category='GOP' AND
	year(tweets.posted)=2016 AND
	month(tweets.posted) IN ('1','2','3')
GROUP BY tagged.hastagname 
ORDER BY count(tagged.hastagname) DESC LIMIT 5;





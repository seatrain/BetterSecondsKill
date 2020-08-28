--
-- Created by IntelliJ IDEA.
-- User: Administrator
-- Date: 2020/08/25
-- Time: 11:07
-- To change this template use File | Settings | File Templates.
-- 限流：若对应的key不存在，则创建并将其值设置为1，并设置过期时间，单位为秒.若存在则将其加1。返回该key的值

local keyName = KEYS[1]
local expireSeconds = KEYS[2]
local keyValue = redis.call("get", keyName)

keyValue = redis.call("incr", keyName)

if keyValue == 1
then
    redis.call("expire", keyName, expireSeconds)
end

return keyValue

